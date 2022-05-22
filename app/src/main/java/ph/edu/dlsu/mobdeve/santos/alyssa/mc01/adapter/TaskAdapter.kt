package ph.edu.dlsu.mobdeve.santos.alyssa.mc01.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.DetailsActivity
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ItemTaskBinding
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.model.Task

class TaskAdapter :  RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private var taskArrayList = ArrayList<Task>()
    private var context: Context

    constructor(context: Context, taskArrayList: ArrayList<Task>) {
        this.context = context
        this.taskArrayList = taskArrayList
    }

    //Function to add an entry in the recycler view
    fun addTask(task: Task)
    {
        taskArrayList.add(task)
        notifyItemInserted(taskArrayList.size - 1)
    }

    fun removeTask(position: Int) {
       taskArrayList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {
        return taskArrayList.size
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
        holder.bindTask(taskArrayList[position])
    }

    inner class TaskViewHolder(private val itemBinding: ItemTaskBinding) :
        RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener{

        private lateinit var task: Task

        init {
            itemView.setOnClickListener(this)
        }

        fun bindTask(task: Task) {
            this.task = task
            itemBinding.tvName.text = task.name
        }

        override fun onClick(view: View?) {
            var goToDetailsActivity = Intent(context, DetailsActivity::class.java)

            goToDetailsActivity.putExtra(TASK, task)
            goToDetailsActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(goToDetailsActivity)
        }
    }
    companion object {
        const val TASK = "TASK"
    }
}
