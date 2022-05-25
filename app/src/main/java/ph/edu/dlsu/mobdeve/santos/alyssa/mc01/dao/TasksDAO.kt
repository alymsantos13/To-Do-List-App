package ph.edu.dlsu.mobdeve.santos.alyssa.mc01.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.util.Log
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.model.Task
import java.util.*
import kotlin.collections.ArrayList

interface TasksDAO {
    fun addTask(task: Task)
    fun getTask(): ArrayList<Task>
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

class TasksDAOSQLImpl(var context: Context) : TasksDAO {


    override fun addTask(task: Task) {
        var databaseHandler: DatabaseHandler = DatabaseHandler(context) //naset up na db
        val db = databaseHandler.writableDatabase //important na writable
        val contentValues = ContentValues() //data na issave sa db
        contentValues.put(DatabaseHandler.KEYNAME, task.name)
        contentValues.put(DatabaseHandler.KEYDESCRIPTION, task.description)
        // contentValues.put(DatabaseHandler.KEYPASSWORD, task.password)
        contentValues.put(DatabaseHandler.KEYDATE, task.dueDate!!.time)
        contentValues.put(DatabaseHandler.KEYREPEAT, task.repeat)

        val success = db.insert(
            DatabaseHandler.TABLETASKS,
            null,
            contentValues
        ) //pag ipapasok mo na yung data sa db
        db.close() //do not forget to close db
    }

    @SuppressLint("Range")
    override fun getTask(): ArrayList<Task> {
        val taskList: ArrayList<Task> = ArrayList<Task>()
        val selectQuery = "SELECT * FROM ${DatabaseHandler.TABLETASKS}"

        var databaseHandler: DatabaseHandler = DatabaseHandler(context)
        val db = databaseHandler.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null) //responsible for retrieving data
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) { //may data move to first
            with(cursor) {
                do {
                    taskList.add(
                        Task(
                            getString(getColumnIndex(DatabaseHandler.KEYNAME)),
                            getString(getColumnIndex(DatabaseHandler.KEYDESCRIPTION)),
                            Date(getLong(getColumnIndex((DatabaseHandler.KEYDATE)))),
                            getInt(getColumnIndex(DatabaseHandler.KEYREPEAT)) == 1
                        )
                    )
                } while (moveToNext())
            }
        }
        return taskList
    }

}