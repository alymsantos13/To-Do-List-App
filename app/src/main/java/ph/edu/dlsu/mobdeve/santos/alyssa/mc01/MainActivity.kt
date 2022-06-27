package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ActivityMainBinding
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.util.StoragePreferences

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: StoragePreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = StoragePreferences(this)
        val str_login_status = sharedPreferences!!.getStringPreferences("login_status")
        if(str_login_status != "") {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
            finish()
        }
        else {
            Handler(Looper.myLooper()!!).postDelayed({
                val goToLogin = Intent(this, LoginActivity::class.java)
                startActivity(goToLogin)
            }, 3000)
        }
    }
}