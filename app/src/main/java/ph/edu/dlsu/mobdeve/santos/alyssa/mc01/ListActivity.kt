package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
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
import kotlin.collections.ArrayList

class ListActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityListBinding
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskArrayList: ArrayList<Task>
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var dao: TasksDAO
    var sharedPreferences : StoragePreferences? = null

    private val addResultLauncher =
        registerForActivityResult(StartActivityForResult()) { result ->
            // a new item as passed
            result.data?.getParcelableExtra<Task>(AddActivity.TASK)?.let {
                Log.d("TASK", it.toString())
                taskAdapter.addTask(it)
                dao.addTask(it)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
  //      init()
        Log.d(
            "LIST ACTIVITY", "PASOK"
        )
        sharedPreferences = StoragePreferences(this)

        dao = TasksDAOSQLImpl(applicationContext)
        taskArrayList = dao.getTask()

        //Checkbox filter
        binding.cbCheckbox.setOnClickListener{
            if(binding.cbCheckbox.isChecked)
            {
                Log.d("CHECKBOX", "IT IS CHECKED")
                taskArrayList = dao.getCompletedTask()
                binding.rvList.setLayoutManager(LinearLayoutManager(applicationContext))
                taskAdapter = TaskAdapter(applicationContext, taskArrayList)
                binding.rvList.setAdapter(taskAdapter)
            }
            else
            {
                Log.d("NOTCHECKBOX", "IT IS NOT CHECKED")
                taskArrayList = dao.getTask()
                binding.rvList.setLayoutManager(LinearLayoutManager(applicationContext))
                taskAdapter = TaskAdapter(applicationContext, taskArrayList)
                binding.rvList.setAdapter(taskAdapter)
            }
        }

        binding.rvList.setLayoutManager(LinearLayoutManager(applicationContext))
        taskAdapter = TaskAdapter(applicationContext, taskArrayList)
        binding.rvList.setAdapter(taskAdapter)

        var swipeCallback = SwipeCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        swipeCallback.taskAdapter = taskAdapter
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

   /* private fun init() {
        var dao: TasksDAO = TasksDAOArrayImpl()

        var task = Task(0,"Water the plants", "nice", Date(122, 4, 22), false)
        dao.addTask(task)
        taskArrayList = dao.getTask()

    }*/

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
                    Toast.makeText(this, "Successfully Logged out",Toast.LENGTH_SHORT).show()
                    var goToLoginActivity = Intent(this, LoginActivity::class.java)
                    startActivity(goToLoginActivity)
                    finish()
                }
            }
        }
}
