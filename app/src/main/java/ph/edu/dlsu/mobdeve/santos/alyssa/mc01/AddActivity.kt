package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.DecelerateInterpolator
import androidx.core.graphics.ColorUtils
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.adapter.TaskAdapter
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ActivityAddBinding
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.model.Task
import java.util.*

class AddActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddBinding

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

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        binding.btnDate.setOnClickListener{
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
                binding.tvDate.setText("" + month + "/" + day + "/" + year)
            }, year, month, day)
            dpd.show()
        }
        binding.popupWindowButton.setOnClickListener {
            val goToListActivity = Intent(this, ListActivity::class.java)
            goToListActivity.putExtra("title", binding.etTitle1.text.toString())
            goToListActivity.putExtra("desc", binding.etDescription.text.toString())
            goToListActivity.putExtra("date", binding.tvDate.text)
            binding.etTitle1.text?.clear()

            goToListActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(goToListActivity)
            finish()
        }
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