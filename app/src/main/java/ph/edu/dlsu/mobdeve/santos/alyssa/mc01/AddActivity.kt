package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ActivityAddBinding
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.model.Task
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.util.formatDate
import java.util.*

class AddActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddBinding
    private val date: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        overridePendingTransition(0,0)
        setContentView(binding.root)

        val dpd = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            date.set(year, month, dayOfMonth)
            binding.tvDate.text = formatDate(date)
        }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH))

        binding.btnDate.setOnClickListener{
            dpd.show()
        }
        binding.popupWindowButton.setOnClickListener {
            setResult(RESULT_OK, Intent(this, ListActivity::class.java).apply {
                putExtra(TASK, Task(
                    null,
                    binding.etTitle1.text.toString(),
                    binding.etDescription.text.toString(),
                    Date(date.timeInMillis),
                    binding.cbRepeat1.isChecked
                ))
            })
            finish()
        }
    }

    companion object {
        const val TASK = "TASK"
    }
}