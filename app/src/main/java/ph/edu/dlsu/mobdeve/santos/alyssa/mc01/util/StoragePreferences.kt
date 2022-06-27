package ph.edu.dlsu.mobdeve.santos.alyssa.mc01.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class StoragePreferences (val context: Context) {
    private val PREFS = "appPreferences" //pref name
    val storage_pref: SharedPreferences? = context.getSharedPreferences(PREFS, MODE_PRIVATE)



    fun saveStringPreferences(key : String, value : String) {
        val prefsEditor = storage_pref!!.edit()
        prefsEditor.putString(key, value)
        prefsEditor.commit()
    }
    fun getStringPreferences(key: String?): String? {
        //meaning wala pa nasssave sa preference na yun
        return storage_pref!!.getString(key, "")
    }

    //retrieve
    fun clearStringPreferences(){
        //(key, defValue)
        val prefsEditor = storage_pref!!.edit()
        prefsEditor.clear()
        prefsEditor.apply()
    }
}