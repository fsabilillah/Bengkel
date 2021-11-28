package com.example.bengkel.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.bengkel.R
import com.example.bengkel.data.Resource
import com.example.bengkel.databinding.ActivityApiCheckBinding
import com.example.bengkel.databinding.ActivityLoginBinding
import com.example.bengkel.ui.api.ApiCheckActivity
import com.example.bengkel.ui.api.ApiCheckViewModel
import com.example.bengkel.ui.main.MainActivity
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        with(binding){
            viewModel.isLogin.observe(this@LoginActivity, {
                if (it == 1){
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    lyLogin.visibility = View.VISIBLE
                }
            })

            btnHealthCheck.setOnClickListener {
                startActivity(Intent(this@LoginActivity, ApiCheckActivity::class.java))
            }

            btnLogin.setOnClickListener {
                if (checkAllField()){
                    val username = binding.tfUsername.editText?.text.toString()
                    val password = binding.tfPassword.editText?.text.toString()
                    viewModel.login(username, password).observe(this@LoginActivity, {
                        when(it){
                            is Resource.Loading -> {
                                btnLogin.isEnabled = false
                                btnLogin.text = "Loading..."
                            }
                            is Resource.Success -> {
                                FancyToast.makeText(this@LoginActivity, "login has successful", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show()
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                finish()
                            }
                            is Resource.Error -> {
                                btnLogin.isEnabled = true
                                btnLogin.text = "Login"
                                FancyToast.makeText(this@LoginActivity, "login has failed", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
                            }
                        }
                    })
                }
            }
        }
    }

    private fun checkAllField() : Boolean {
        if (binding.tfUsername.editText?.text?.isEmpty() == true) {
            binding.tfUsername.error = "This field is required"
            return false
        }
        if (binding.tfPassword.editText?.text?.isEmpty() == true) {
            binding.tfPassword.error = "This field is required"
            return false
        }
        return true
    }
}