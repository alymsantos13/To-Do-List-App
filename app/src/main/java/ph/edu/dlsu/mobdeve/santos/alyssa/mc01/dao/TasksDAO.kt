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
    fun deleteTask(id: Long?)
    fun getTaskCount() : String
}

class TasksDAOSQLImpl(var context: Context) : TasksDAO {


    override fun addTask(task: Task) {
        var databaseHandler: DatabaseHandler = DatabaseHandler(context) //naset up na db
        val db = databaseHandler.writableDatabase //important na writable
        val contentValues = ContentValues() //data na issave sa db

        contentValues.put(DatabaseHandler.KEYNAME, task.name)
        contentValues.put(DatabaseHandler.KEYDESCRIPTION, task.description)
        contentValues.put(DatabaseHandler.KEYDATE, task.dueDate!!.time)
        contentValues.put(DatabaseHandler.KEYREPEAT, task.repeat)

        task._id = db.insert(
            DatabaseHandler.TABLETASKS,
            null,
            contentValues
        )
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
                            getLong(cursor.getColumnIndex(DatabaseHandler.KEYID)),
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

    override fun deleteTask(id: Long?){
        var databaseHandler:DatabaseHandler = DatabaseHandler(context) //naset up na db
        val db = databaseHandler.writableDatabase //important na writable
        val success = db.delete(DatabaseHandler.TABLETASKS, DatabaseHandler.KEYID + "=" + id , null)

        db.close()
    }

    //Counting the tasks
    override fun getTaskCount(): String {
        val selectQuery = "SELECT COUNT(*) FROM ${DatabaseHandler.TABLETASKS}"
        var databaseHandler: DatabaseHandler = DatabaseHandler(context)
        val db = databaseHandler.readableDatabase
        var cursor: Cursor? = null

        cursor = db.rawQuery(selectQuery, null)


        var count : Int = if(cursor.moveToFirst()) { cursor.getInt(0) } else { 0 }
        db.close()
        return count.toString()
    }
}