package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.adapter.TaskAdapter
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ActivityDetailsBinding
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.model.Task
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.util.StoragePreferences
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.util.formatDate
import java.util.*

class DetailsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityDetailsBinding
    private val date: Calendar = Calendar.getInstance()
    var sharedPreferences : StoragePreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = StoragePreferences(this)

        binding.btnTodo.setOnClickListener(this)
        binding.btnTimer.setOnClickListener(this)
        binding.btnLogout.setOnClickListener(this)

        val task = intent.getParcelableExtra<Task>(TaskAdapter.TASK)

        binding.tvTitle.text = task?.name
        binding.tvDescription.text = task?.description
        binding.tvDue.text = formatDate(task?.dueDate)
        Log.d("TASK", task.toString())

        if (task != null) {
            binding.cbRepeat.isChecked = task.repeat == true
        }
        Log.d("NAKCHECK BA", "${task?.completed}")

    }

    override fun onClick(view: View?) {
        when(view!!.id)
        {
            R.id.btn_todo -> {
                var goToListActivity = Intent(this, ListActivity::class.java)
                startActivity(goToListActivity)

            }
            R.id.btn_timer -> {
                var goToTimerActivity = Intent(this, TimerActivity::class.java)
                startActivity(goToTimerActivity)

            }
            R.id.btn_logout -> {
                sharedPreferences!!.saveStringPreferences("login_status", "")
                Toast.makeText(this, "Successfully Logged out", Toast.LENGTH_SHORT).show()
                var goToLoginActivity = Intent(this, LoginActivity::class.java)
                startActivity(goToLoginActivity)
                finish()
            }
        }
    }

}