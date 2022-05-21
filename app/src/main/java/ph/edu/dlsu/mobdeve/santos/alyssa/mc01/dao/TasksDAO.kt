package ph.edu.dlsu.mobdeve.santos.alyssa.mc01.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
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

class TasksDAOSQLImpl(var context: Context): TasksDAO{


    override fun addTask(task: Task) {
        var databaseHandler:DatabaseHandler = DatabaseHandler(context) //naset up na db
        val db = databaseHandler.writableDatabase //important na writable
        val contentValues = ContentValues() //data na issave sa db
        contentValues.put(DatabaseHandler.KEYNAME, task.name)
        contentValues.put(DatabaseHandler.KEYDESCRIPTION, task.description)
        contentValues.put(DatabaseHandler.KEYPASSWORD, task.password)
        contentValues.put(DatabaseHandler.KEYDATE, task.dueDate.toString())

        val success = db.insert(DatabaseHandler.TABLETASKS, null, contentValues) //pag ipapasok mo na yung data sa db
        db.close() //do not forget to close db
    }

    override fun getTask(): ArrayList<Task> {
        val taskList:ArrayList<Task> = ArrayList<Task>()
        val selectQuery = "SELECT ${DatabaseHandler.KEYNAME}, " +
                "${DatabaseHandler.KEYDESCRIPTION}, " +
                "${DatabaseHandler.KEYPASSWORD}, " +
                "${DatabaseHandler.KEYDATE} "
                "FROM ${DatabaseHandler.TABLETASKS}"
        var databaseHandler: DatabaseHandler = DatabaseHandler(context)
        val db = databaseHandler.readableDatabase
        var cursor: Cursor? = null

        try{
            cursor = db.rawQuery(selectQuery, null) //responsible for retrieving data
        }catch (e: SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var task = Task()
        if(cursor.moveToFirst()){ //may data move to first
            do{
                task = Task()
                task.name = cursor.getString(0)
                task.description = cursor.getString(1)
                task.password = cursor.getString(2)
                //task.dueDate = cursor.getString(3)
                taskList.add(task)
            } while(cursor.moveToNext())
        }
        return taskList  }

}