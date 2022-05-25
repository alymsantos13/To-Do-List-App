package ph.edu.dlsu.mobdeve.santos.alyssa.mc01.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHandler (context: Context): SQLiteOpenHelper(context, DATABASENAME,null,
    DATABASEVERSION){
    companion object{
        private val DATABASEVERSION = 1 //version need (nagadd ng table, columns) dapat nagbabago version?
        private val DATABASENAME = "ToDoDatabase" //filename ng database

        val TABLETASKS = "TasksTable" //tables
        val KEYID = "id" //columns
        val KEYDESCRIPTION = "description"//columns
        val KEYNAME = "name"//columns
        /*val KEYPASSWORD = "password"//columns*/
        val KEYDATE = "dueDate"//columns
    }
    override fun onCreate(db: SQLiteDatabase) {

        val CREATETASKSTABLE = "CREATE TABLE $TABLETASKS " +
                "($KEYID INTEGER PRIMARY KEY AUTOINCREMENT, " + //dapat may space before " - AUTOINCREMENT (AUTOGENERATE ID)
                "$KEYNAME TEXT, " +
                "$KEYDESCRIPTION TEXT, " +
              /*  "$KEYPASSWORD TEXT, " +*/
                "$KEYDATE DATE)"
        db?.execSQL(CREATETASKSTABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLETASKS)
        onCreate(db)
    }
}