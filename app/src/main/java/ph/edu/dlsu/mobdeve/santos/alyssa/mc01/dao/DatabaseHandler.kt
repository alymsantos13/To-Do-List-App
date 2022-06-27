package ph.edu.dlsu.mobdeve.santos.alyssa.mc01.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

class DatabaseHandler(context: Context) : SQLiteOpenHelper(
    context, DATABASENAME, null,
    DATABASEVERSION
) {
    companion object {
        private val DATABASEVERSION =
            1 //version need (nagadd ng table, columns) dapat nagbabago version?
        private val DATABASENAME = "ToDoDatabase" //filename ng database

        const val TABLETASKS = "TasksTable" //tables
        const val KEYID = "_id" //columns
        const val KEYDESCRIPTION = "description"//columns
        const val KEYNAME = "name"//columns

        /*val KEYPASSWORD = "password"//columns*/
        const val KEYDATE = "dueDate"//columns
        const val KEYREPEAT = "repeat"

        const val KEYCOMPLETED = "completed"
    }

    override fun onCreate(db: SQLiteDatabase) {

        val CREATETASKSTABLE = "CREATE TABLE $TABLETASKS " +
                "($KEYID INTEGER PRIMARY KEY AUTOINCREMENT, " + //dapat may space before " - AUTOINCREMENT (AUTOGENERATE ID)
                "$KEYNAME TEXT, " +
                "$KEYDESCRIPTION TEXT, " +
                "$KEYDATE INTEGER, " +
                "$KEYREPEAT BOOLEAN, " +
                "$KEYCOMPLETED BOOLEAN)"
        db.execSQL(CREATETASKSTABLE)

        /*with(ContentValues()) {
            put(KEYNAME, "Note 1")
            put(KEYDESCRIPTION, "My Description")
            put(KEYDATE, Calendar.getInstance().timeInMillis)
            put(KEYREPEAT, false)
            put(KEYCOMPLETED, false)
            db.insert(TABLETASKS, null, this)

            put(KEYNAME, "Note 2")
            put(KEYDESCRIPTION, "My Other Description")
            put(KEYREPEAT, true)
            put(KEYCOMPLETED, false)
            db.insert(TABLETASKS, null, this)
        }*/
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLETASKS")
        onCreate(db)
    }
}