package ph.edu.dlsu.mobdeve.santos.alyssa.mc01.callback

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.AddActivity
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.adapter.TaskAdapter
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.model.Task

class SwipeCallback(dragDirs: Int, swipeDirs: Int, private val taskAdapter: TaskAdapter) :
    ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
    var background: ColorDrawable? = ColorDrawable(Color.BLACK)

    override fun onChildDraw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(
            canvas, recyclerView, viewHolder, dX,
            dY, actionState, isCurrentlyActive
        )
        val itemView = viewHolder.itemView
        if (dX > 0) { //Swiping to the right
            background = ColorDrawable(Color.RED)
            background!!.setBounds(
                itemView.left, itemView.top,
                itemView.left + dX.toInt(),
                itemView.bottom
            )
        } else if (dX < 0) {//Swiping to the left
            background = ColorDrawable(Color.RED)
            background!!.setBounds(
                itemView.right + dX.toInt(),
                itemView.top, itemView.right, itemView.bottom
            )
        } else { //view is unSwiped
            background!!.setBounds(0, 0, 0, 0)
        }
        background!!.draw(canvas)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        taskAdapter.removeTask(position)

    }


}