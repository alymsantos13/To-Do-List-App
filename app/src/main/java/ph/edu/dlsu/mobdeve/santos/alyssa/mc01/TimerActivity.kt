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
    private var timerStarted = false
    private lateinit var serviceIntent:Intent
    private var time = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartStop.setOnClickListener{ startStopTimer()}
        binding.btnReset.setOnClickListener{ resetTimer()}

        serviceIntent = Intent(applicationContext, TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
    }

    private fun resetTimer() {
        stopTimer()
        time = 0.0
        binding.tvCountdown.text = getTimeStringFromDouble(time)
    }

    private fun startStopTimer() {
        if(timerStarted)
            stopTimer()
        else
            startTimer()
    }

    private fun startTimer() {
        serviceIntent.putExtra(TimerService.TIME_EXTRA, time)
        startService(serviceIntent)
        binding.btnStartStop.text = "Stop"
        binding.btnStartStop.icon = getDrawable(R.drawable.pause)
        timerStarted = true
    }

    private fun stopTimer() {
        stopService(serviceIntent)
        binding.btnStartStop.text = "Start"
        binding.btnStartStop.icon = getDrawable(R.drawable.play)
        timerStarted = false
    }

    private val updateTime : BroadcastReceiver = object : BroadcastReceiver()
    {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null) {
                time = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            }
            binding.tvCountdown.text = getTimeStringFromDouble(time)
        }


    }

    private fun getTimeStringFromDouble(time: Double): String
    {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hour : Int, min: Int, sec: Int): String = String.format("%02d:%02d:%02d", hour, min, sec)


}