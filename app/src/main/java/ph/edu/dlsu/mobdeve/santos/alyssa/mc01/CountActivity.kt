package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ActivityCountBinding

class CountActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.myLooper()!!).postDelayed({val goToListActivity = Intent(this, ListActivity::class.java)
            startActivity(goToListActivity)
        }, 3000)
    }
}