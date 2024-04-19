package com.quar.uniedu.ui.login

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.quar.uniedu.databinding.ActivityLoginBinding
import com.quar.uniedu.network.Resource
import com.quar.uniedu.ui.home.HomeActivity
import com.quar.uniedu.utils.Prefs
import com.quar.uniedu.utils.openActivity
import com.quar.uniedu.utils.username

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = Prefs(this)
        val viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        val loader = ProgressDialog(this)
        loader.setCancelable(false)
        loader.setMessage("Loading...")

        binding.apply {
            button.setOnClickListener {
                loader.show()
                viewModel.login(
                    this.edEmail.text.toString().username(),
                    this.edPassword.text.toString()
                )
            }
        }

        viewModel.login.observe(this) {
            when (it) {
                is Resource.Loading -> ""
                is Resource.Failure -> message(it.errorCode.toString())
                is Resource.Success -> {
                    pref.isLogin = true
                    openActivity(HomeActivity::class.java)
                    finishAffinity()
                }
            }
            loader.hide()

        }
    }

    private fun message(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}