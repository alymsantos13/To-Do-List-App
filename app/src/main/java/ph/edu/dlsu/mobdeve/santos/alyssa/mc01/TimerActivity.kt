package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ActivityTimerBinding
import kotlin.math.roundToInt

class TimerActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTimerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}