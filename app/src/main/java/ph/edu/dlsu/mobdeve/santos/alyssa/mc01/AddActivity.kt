package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.app.*
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ActivityAddBinding
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.model.Task
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.util.formatDate
import java.util.*

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private val date: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        overridePendingTransition(0, 0)
        setContentView(binding.root)

        //To allow selecting a due date for the task
        val dpd = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            date.set(year, month, dayOfMonth)
            binding.tvDate.text = formatDate(date)
        }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH))

        //To access the Date Picker Dialog
        binding.btnDate.setOnClickListener {
            dpd.show()
        }

        //To save the newly added task
        binding.popupWindowButton.setOnClickListener {
            if(binding.etTitle1.text!!.isNotEmpty() && binding.etDescription.text!!.isNotEmpty() && binding.tvDate.text.isNotEmpty()){
                setResult(RESULT_OK, Intent(this, ListActivity::class.java).apply {
                    putExtra(
                        TASK, Task(
                            null,
                            binding.etTitle1.text.toString(),
                            binding.etDescription.text.toString(),
                            Date(date.timeInMillis),
                            binding.cbRepeat1.isChecked,
                            false
                        )
                    )
                })

                finish()
            }
            else {
                Toast.makeText(this, "Missing Input", Toast.LENGTH_SHORT).show()
            }

        }
    }


    companion object {
        const val TASK = "TASK"
        const val ID_KEY = "ID_KEY"
        const val TITLE_KEY = "TITLE_KEY"
    }
}

