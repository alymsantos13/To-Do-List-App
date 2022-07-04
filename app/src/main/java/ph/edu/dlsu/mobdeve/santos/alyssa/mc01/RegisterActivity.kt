package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ActivityRegisterBinding
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.util.StoragePreferences

class RegisterActivity : AppCompatActivity(){
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var sharedPreferences: StoragePreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = StoragePreferences(this)

        binding.btnConfirm.setOnClickListener{
            val strRegPassword1 = binding.etPassword1.text.toString()
            val strRegPassword2 = binding.etPassword2.text.toString()

            if(strRegPassword1.equals("") || strRegPassword2.equals("")) {
                Toast.makeText(this, "Please enter complete details", Toast.LENGTH_SHORT).show()
            }
            else {
                if(strRegPassword1 != strRegPassword2){
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
                else {
                    sharedPreferences.saveStringPreferences("password",strRegPassword1)
                    Toast.makeText(this, "Password Created Succesfully", Toast.LENGTH_SHORT).show()

                    var goToLoginActivity = Intent(this, LoginActivity::class.java)
                    startActivity(goToLoginActivity)
                    finish()

                }
            }

        }


        //binding.btnConfirm.setOnClickListener(this)
    }

   /* override fun onClick(view: View?) {
        when(view!!.id){
            R.id.btn_confirm -> {
                var goToLoginActivity = Intent(this, LoginActivity::class.java)
                startActivity(goToLoginActivity)
                finish()
            }
        }
    }*/
}