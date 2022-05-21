package ph.edu.dlsu.mobdeve.santos.alyssa.mc01.util

import android.content.Context
import android.content.SharedPreferences

class StoragePreferences {
    private var appPreferences : SharedPreferences? = null
    private val PREFS = "appPreferences"

    constructor(context: Context) {
        appPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
    }

    fun saveStringPreferences(key : String?, value : String?) {
        val prefsEditor = appPreferences!!.edit()
        prefsEditor.putString(key, value)
        prefsEditor.commit()
    }
    fun getStringPreferences(key: String?): String? {
        //meaning wala pa nasssave sa preference na yun
        return appPreferences!!.getString(key, "Nothing Saved")
    }
}