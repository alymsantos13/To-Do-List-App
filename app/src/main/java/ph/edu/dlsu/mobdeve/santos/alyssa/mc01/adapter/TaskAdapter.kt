package ph.edu.dlsu.mobdeve.santos.alyssa.mc01.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.AlarmReceiver
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.DetailsActivity
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.dao.TasksDAO
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.dao.TasksDAOSQLImpl
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ItemTaskBinding
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.model.Task
import java.util.*
import kotlin.collections.ArrayList

class TaskAdapter(
    private val context: Context,
    private val taskArrayList : ArrayList<Task>
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskComparator()) {

    private val dao: TasksDAO = TasksDAOSQLImpl(context)
    private val alarmReceiver = AlarmReceiver()
    //private var taskArrayList = ArrayList<Task>()

    fun addTask(task: Task){
        taskArrayList.add(task)
        notifyItemInserted(taskArrayList.size -1)
    }
    //Allows to remove the task from the list, db,and cancels the alarm
    fun removeTask(position: Int) {
        alarmReceiver.cancelAlarm(context, taskArrayList[position])
        dao.deleteTask(taskArrayList.removeAt(position)._id)
        notifyItemRemoved(position)
    }
    //Allows to update the task from the list, db
    fun updateTask(task: Task, position: Int) {
        dao.updateCompleted(task)
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskAdapter.TaskViewHolder {
        val itemBinding = ItemTaskBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return TaskViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        holder.bindTask(taskArrayList[position]) //edited
    }

    inner class TaskViewHolder(private val itemBinding: ItemTaskBinding) :
        RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        private lateinit var task: Task

        init {
            itemView.setOnClickListener(this)
        }
        //To update if the task is marked as completed
        fun bindTask(task: Task) {
            this.task = task
            itemBinding.tvName.text = task.name
            itemBinding.cbCheck.apply {
                isChecked = task.completed
                setOnClickListener {
                    task.completed = !task.completed
                    updateTask(task, layoutPosition)
                }
            }
        }

        override fun onClick(view: View?) {
            Intent(context, DetailsActivity::class.java).apply {
                putExtra(TASK, task)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(this)
            }
        }

    }

    class TaskComparator : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem._id == newItem._id
        override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    }

    companion object {
        const val TASK = "TASK"
    }

}
