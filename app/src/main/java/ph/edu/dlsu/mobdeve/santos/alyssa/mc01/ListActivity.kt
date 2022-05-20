package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.adapter.TaskAdapter
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.callback.SwipeCallback
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.dao.TasksDAO
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.dao.TasksDAOArrayImpl
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ActivityListBinding
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.model.Task

class ListActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityListBinding
    private lateinit var taskAdapter : TaskAdapter
    private lateinit var taskArrayList : ArrayList<Task>
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

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

        binding.btnAdd.setOnClickListener{
            var task = Task()
            task.name = binding.etTask.text.toString()

            taskAdapter.addTask(task)
        }

       /* binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        var title = intent.getStringExtra("title")
        if(title != null) {
            var task = Task()
            task.name = "$title"

            taskAdapter.addTask(task)
            println("$taskAdapter")
        }*/

    }

    private fun init()
    {
        var dao : TasksDAO = TasksDAOArrayImpl()

        var task = Task()
        task.name = "Water the plants"
        dao.addTask(task)

        task = Task()
        task.name = "Do the laundry"
        dao.addTask(task)

        task = Task()
        task.name = "Wash the dishes"
        dao.addTask(task)



        taskArrayList = dao.getTask()
    }


    override fun onClick(view: View?) {
        when(view!!.id)
        {
            R.id.btn_todo -> {
                var goToListActivity = Intent(this, ListActivity::class.java)
                startActivity(goToListActivity)
                finish()
            }
            R.id.btn_timer -> {
                var goToTimerActivity = Intent(this, TimerActivity::class.java)
                startActivity(goToTimerActivity)
                finish()
            }
            R.id.btn_logout -> {
                var goToLoginActivity = Intent(this, LoginActivity::class.java)
                startActivity(goToLoginActivity)
                finish()
            }
        }
    }
}
