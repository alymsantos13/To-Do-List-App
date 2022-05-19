package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
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

        // Fade animation for the background of Popup Window
        val alpha = 100 //between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.TRANSPARENT, alphaColor)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            binding.popupWindowBackground.setBackgroundColor(animator.animatedValue as Int)
        }
        colorAnimation.start()

        // Fade animation for the Popup Window
        binding.popupWindowViewWithBorder.alpha = 0f
        binding.popupWindowViewWithBorder.animate().alpha(1f).setDuration(500).setInterpolator(
            DecelerateInterpolator()
        ).start()

        // Fade animation for the Popup Window
        binding.popupWindowViewWithBorder.alpha = 0f
        binding.popupWindowViewWithBorder.animate().alpha(1f).setDuration(500).setInterpolator(
            DecelerateInterpolator()
        ).start()

        binding.popupWindowButton.setOnClickListener {
            val goToListActivity = Intent(this, ListActivity::class.java)
            goToListActivity.putExtra("title", binding.tvTitle1.text.toString())

            goToListActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(goToListActivity)
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