package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.myLooper()!!).postDelayed({val goToLogin = Intent(this, LoginActivity::class.java)
                                startActivity(goToLogin)
                              }, 3000)
    }
}