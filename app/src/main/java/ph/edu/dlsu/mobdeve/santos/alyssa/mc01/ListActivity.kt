package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.adapter.TaskAdapter
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.callback.SwipeCallback
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.dao.TasksDAO
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.dao.TasksDAOSQLImpl
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ActivityListBinding
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.model.Task
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.util.StoragePreferences
import java.util.*

class ListActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityListBinding
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskArrayList: ArrayList<Task>
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var dao: TasksDAO
    var sharedPreferences: StoragePreferences? = null
    private val alarmReceiver = AlarmReceiver()

    private val addResultLauncher =
        registerForActivityResult(StartActivityForResult()) { result ->
            // a new item as passed
            result.data?.getParcelableExtra<Task>(AddActivity.TASK)?.let {
                Log.d("TASK", it.toString())
              //  taskArrayList.add(it)
                taskAdapter.addTask(it)
                dao.addTask(it)
                alarmReceiver.setAlarm(applicationContext, it) //added
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = StoragePreferences(this)

        dao = TasksDAOSQLImpl(applicationContext)
        taskArrayList = dao.getTask()
        //To allow the filtering of tasks
        binding.cbCheckbox.setOnClickListener {
            taskAdapter.submitList( if (binding.cbCheckbox.isChecked) taskArrayList.filter { it.completed } else taskArrayList )
        }

        binding.rvList.layoutManager = LinearLayoutManager(applicationContext)
        taskAdapter = TaskAdapter(applicationContext,taskArrayList).apply { submitList(taskArrayList) }
        binding.rvList.adapter = taskAdapter


        val swipeCallback =
            SwipeCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT, taskAdapter)
        itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvList)

        //Navigation bar
        binding.btnTodo.setOnClickListener(this)
        binding.btnTimer.setOnClickListener(this)
        binding.btnLogout.setOnClickListener(this)

        binding.btnAdd.setOnClickListener {
            addResultLauncher.launch(Intent(this, AddActivity::class.java))
        }
    }

    //To search
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        var searchItem: MenuItem = menu!!.findItem(R.id.actionSearch);

        val searchView: SearchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }
        })

        return true;
    }
    //For search purposes
    private fun filter(text: String) {
        val filteredlist: ArrayList<Task> = ArrayList()

        for (item in taskArrayList) {
            if (item.name.lowercase().contains(text.lowercase(Locale.getDefault()))) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            taskAdapter.submitList(if (filteredlist.size == taskArrayList.size) taskArrayList else filteredlist)
        }
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btn_todo -> {
                var goToListActivity = Intent(this, ListActivity::class.java)
                startActivity(goToListActivity)

            }
            R.id.btn_timer -> {
                var goToTimerActivity = Intent(this, TimerActivity::class.java)
                startActivity(goToTimerActivity)

            }
            R.id.btn_logout -> {
                //sharedPreferences!!.clearStringPreferences()
                sharedPreferences!!.saveStringPreferences("login_status", "")
                Toast.makeText(this, "Successfully Logged out", Toast.LENGTH_SHORT).show()
                var goToLoginActivity = Intent(this, LoginActivity::class.java)
                startActivity(goToLoginActivity)
                finish()
            }
        }
    }
}
