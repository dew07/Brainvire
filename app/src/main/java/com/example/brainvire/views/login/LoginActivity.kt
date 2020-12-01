package com.example.brainvire.views.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.brainvire.R
import com.example.brainvire.databinding.ActivityLoginBinding
import com.example.brainvire.views.listing.ListingActivity

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.loginBtn.setOnClickListener {

            val emailTxt = binding.emailTxt.text.toString()
            val passwordTxt = binding.passwordTxt.text.toString()

            if(emailTxt == "test@android.com" && passwordTxt == "123456"){
                val intent = Intent(this@LoginActivity,ListingActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this@LoginActivity,"Invalid Credentials. Please try again!",
                    Toast.LENGTH_SHORT).show()
            }
        }

    }
}