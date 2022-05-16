package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ActivityTimerBinding
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.util.PrefUtil
import kotlin.math.roundToInt

class TimerActivity : AppCompatActivity(), View.OnClickListener {

    enum class TimerState
    {
        Stopped, Paused, Running
    }

    private lateinit var timer : CountDownTimer
    private var timerLengthSeconds: Long = 0L
    private var timerState = TimerState.Stopped
    private var secondsRemaining = 0L


    private lateinit var binding : ActivityTimerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Start
        binding.btnStart.setOnClickListener{v ->
            startTimer()
            timerState = TimerState.Running
            updateButtons()
        }

        //Pause
        binding.btnPause.setOnClickListener { v ->
            timer.cancel()
            timerState = TimerState.Paused
            updateButtons()
        }

        //Reset
        binding.btnReset.setOnClickListener { v ->
            timer.cancel()
            onTimerFinished()
        }

        binding.btnTodo.setOnClickListener(this)
        binding.btnTimer.setOnClickListener(this)
        binding.btnLogout.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()

        initTimer()
    }

    override fun onPause() {
        super.onPause()

        if (timerState == TimerState.Running)
        {
            timer.cancel()
        }
        else if (timerState == TimerState.Paused)
        {
            //TODO : Show notification
        }

        PrefUtil.setPreviousTimerLengthSeconds(timerLengthSeconds, this)
        PrefUtil.setSecondsRemaining(secondsRemaining, this)
        PrefUtil.setTimerState(timerState, this)
    }

    private fun initTimer()
    {
        timerState = PrefUtil.getTimerState(this)

        if (timerState == TimerState.Stopped)
            setNewTimerLength()
        else
            setPreviousTimerLength()

        secondsRemaining = if (timerState == TimerState.Running || timerState == TimerState.Paused)
            PrefUtil.getSecondsRemaining(this)
        else
            timerLengthSeconds

        if (timerState == TimerState.Running)
            startTimer()

        updateButtons()
        updateCountdownUI()
    }

    private fun onTimerFinished()
    {
        timerState = TimerState.Stopped
        setNewTimerLength()


        PrefUtil.setSecondsRemaining(timerLengthSeconds, this)
        secondsRemaining = timerLengthSeconds

        updateButtons()
        updateCountdownUI()
    }

    private fun startTimer()
    {
        timerState = TimerState.Running

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000){
            override fun onFinish() = onTimerFinished()

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountdownUI()
            }
        }.start()
    }

    private fun setNewTimerLength()
    {
        val lengthInMinutes = PrefUtil.getTimerLength(this)
        timerLengthSeconds = (lengthInMinutes * 60L)
    }

    private fun setPreviousTimerLength()
    {
        timerLengthSeconds = PrefUtil.getPreviousTimerLengthSeconds(this)
    }

    private fun updateCountdownUI()
    {
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsInMinuteUntilFinished.toString()
        binding.tvCountdown.text = "$minutesUntilFinished:${
            if(secondsStr.length == 2) secondsStr
            else "0$secondsStr"}"
    }

    private fun updateButtons()
    {
        when(timerState){
            TimerState.Running -> {
                binding.btnStart.isEnabled = false
                binding.btnPause.isEnabled = true
                binding.btnReset.isEnabled = true
            }

            TimerState.Stopped -> {
                binding.btnStart.isEnabled = true
                binding.btnPause.isEnabled = false
                binding.btnReset.isEnabled = false
            }

            TimerState.Paused -> {
                binding.btnStart.isEnabled = true
                binding.btnPause.isEnabled = false
                binding.btnReset.isEnabled = true
            }
        }
    }


    override fun onClick(view: View?) {
        when(view!!.id)
        {
            R.id.btn_todo -> {
                var goToListActivity = Intent(this, ListActivity::class.java)
                startActivity(goToListActivity)
                finish()
            }
            R.id.btn_timer -> {
                var goToTimerActivity = Intent(this, TimerActivity::class.java)
                startActivity(goToTimerActivity)
                finish()
            }
            R.id.btn_logout -> {
                var goToLoginActivity = Intent(this, LoginActivity::class.java)
                startActivity(goToLoginActivity)
                finish()
            }
        }
    }
}