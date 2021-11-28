package com.example.bengkel.ui.api

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bengkel.R
import com.example.bengkel.databinding.ActivityApiCheckBinding
import com.example.bengkel.utils.UrlPreference
import org.koin.android.viewmodel.ext.android.viewModel

class ApiCheckActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApiCheckBinding
    private val viewModel: ApiCheckViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_check)
        binding = ActivityApiCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        with(binding){
            viewModel.healthCheck.observe(this@ApiCheckActivity, {
                tvInfo.text = it.toString()
                btnChange.text = "Check"
            })

            val urlPreference = UrlPreference(this@ApiCheckActivity)
            val host = urlPreference.getUrl()
            tfUrl.editText?.setText(host)

            btnChange.setOnClickListener { _ ->
                btnChange.text = "Loading..."
                btnChange.isEnabled = false
                urlPreference.setUrl(binding.tfUrl.editText?.text.toString())
                Handler(Looper.getMainLooper()).postDelayed({

                    val intent = Intent(this@ApiCheckActivity, ApiCheckActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Runtime.getRuntime().exit(0)
                }, 3000)
            }
        }
    }
}