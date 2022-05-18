package ph.edu.dlsu.mobdeve.santos.alyssa.mc01.dao

import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.model.Task

interface TasksDAO {
    fun addTask(task: Task)
    fun getTask():ArrayList<Task>
}

class TasksDAOArrayImpl : TasksDAO {

    private var arrayListTasks = ArrayList<Task>()

    override fun addTask(task: Task) {
        arrayListTasks.add(0, task)
    }

    override fun getTask(): ArrayList<Task> {
        return arrayListTasks
    }
}