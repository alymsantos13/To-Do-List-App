package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ActivityTimerBinding
import kotlin.math.roundToInt

class TimerActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityTimerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTodo.setOnClickListener(this)
        binding.btnTimer.setOnClickListener(this)
        binding.btnLogout.setOnClickListener(this)
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