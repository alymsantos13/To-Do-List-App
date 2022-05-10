package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConfirm.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.btn_confirm -> {
                var goToLoginActivity = Intent(this, LoginActivity::class.java)
                startActivity(goToLoginActivity)
                finish()
            }
        }
    }
}