package ph.edu.dlsu.mobdeve.santos.alyssa.mc01.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ItemTaskBinding
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.model.Task

class TaskAdapter :  RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private var taskArrayList = ArrayList<Task>()
    private lateinit var context: Context

    constructor(context: Context, taskArrayList: ArrayList<Task>) {
        this.context = context
        this.taskArrayList = taskArrayList
    }

    //Function to add an entry in the recycler view
    fun addAccount(task: Task)
    {
        taskArrayList.add(0, task)
        notifyItemInserted(0)
        notifyDataSetChanged()
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

        var task = Task()

        init {
            itemView.setOnClickListener(this)
        }

        fun bindTask(task: Task) {
            this.task = task
            itemBinding.tvName.text = task.name
        }

        override fun onClick(v: View?) {
//            var goToProduct = Intent(context, ProductActivity::class.java)
//
//            var bundle = Bundle()
//            bundle.putString("name", product.name)
//            bundle.putString("category", product.category)
//            bundle.putDouble("price", product.price)
//            bundle.putInt("img", product.img)
//            bundle.putInt("img1", product.img1)
//            bundle.putString("details", product.details)
//
//            goToProduct.putExtras(bundle)
//            goToProduct.putExtra("source", "CAME FROM ADAPTER")
//
//            goToProduct.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            context.startActivity(goToProduct)
            println("Testing")
        }
    }
}
