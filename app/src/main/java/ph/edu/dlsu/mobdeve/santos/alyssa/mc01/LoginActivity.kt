package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view!!.id)
        {
            R.id.btn_register -> {
                var goToRegisterActivity = Intent(this, RegisterActivity::class.java)
                startActivity(goToRegisterActivity)
                finish()
            }
        }
    }
}