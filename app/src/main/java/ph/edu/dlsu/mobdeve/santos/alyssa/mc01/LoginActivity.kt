package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ActivityLoginBinding
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.util.StoragePreferences

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var sharedPreferences: StoragePreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = StoragePreferences(this)
        var edt_password = binding.etPassword

        Log.d("LOGIN ACTIVITY", "PASOK")

        Log.d("LOGIN ACTIVITY", "PASOK2")
        binding.btnRegister.setOnClickListener{
            var goToRegisterActivity = Intent(this, RegisterActivity::class.java)
            startActivity(goToRegisterActivity)
        }

        binding.btnLogin.setOnClickListener{
            val strPassword = edt_password.text.toString()

            if(strPassword.equals("")) {
                Toast.makeText(this, "ERROR: Please Enter Your Password", Toast.LENGTH_SHORT).show()
            }
            else {
                val password = sharedPreferences!!.getStringPreferences("password")

                if(password.equals(strPassword)) {
                    sharedPreferences!!.saveStringPreferences("login_status", "1")

                    val goToCountActivity = Intent(this, CountActivity::class.java)
                    startActivity(goToCountActivity)
                    finish()

                }
                else {
                    Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show()
                }
            }
        }


        /*binding.btnRegister.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)*/
    }
}