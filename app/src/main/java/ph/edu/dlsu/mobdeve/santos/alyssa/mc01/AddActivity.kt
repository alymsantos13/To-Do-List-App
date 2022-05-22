package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ActivityAddBinding
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.model.Task
import android.text.format.DateFormat
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

       /* val bundle = intent.extras
        var name = bundle?.getString("title", "Title") ?: ""
        var description = bundle?.getString("popuptext", "Text") ?: ""
        var save = bundle?.getString("save", "Button") ?: ""
        var darkStatusBar = bundle?.getBoolean("darkstatusbar", false) ?: false*/

        /*// Fade animation for the background of Popup Window
        val alpha = 100 //between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.TRANSPARENT, alphaColor)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            binding.popupWindowBackground.setBackgroundColor(animator.animatedValue as Int)
        }
        colorAnimation.start()

        // Fade animation for the Popup Window
        binding.cvPopup.alpha = 0f
        binding.cvPopup.animate().alpha(1f).setDuration(500).setInterpolator(
            DecelerateInterpolator()
        ).start()

        // Fade animation for the Popup Window
        binding.cvPopup.alpha = 0f
        binding.cvPopup.animate().alpha(1f).setDuration(500).setInterpolator(
            DecelerateInterpolator()
        ).start()*/

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
                    binding.etTitle1.text.toString(),
                    binding.etDescription.text.toString(),
                    Date(date.timeInMillis),
                    true
                ))
            })
            finish()
        }
    }

    companion object {
        const val TASK = "TASK"
    }

    /*override fun onBackPressed() {
        // Fade animation for the background of Popup Window when you press the back button
        val alpha = 100 // between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), alphaColor, Color.TRANSPARENT)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            binding.popupWindowBackground.setBackgroundColor(
                animator.animatedValue as Int
            )
        }

        // Fade animation for the Popup Window when you press the back button
        binding.popupWindowViewWithBorder.animate().alpha(0f).setDuration(500).setInterpolator(
            DecelerateInterpolator()
        ).start()

        // After animation finish, close the Activity
        colorAnimation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                finish()
                overridePendingTransition(0, 0)
            }
        })
        colorAnimation.start()
    }*/
}