package com.quar.uniedu.ui.login

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.quar.uniedu.databinding.ActivitySignupBinding
import com.quar.uniedu.network.Resource
import com.quar.uniedu.ui.home.HomeActivity
import com.quar.uniedu.utils.Prefs
import com.quar.uniedu.utils.message
import com.quar.uniedu.utils.openActivity
import com.quar.uniedu.utils.username

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        val pref = Prefs(this)

        val loader = ProgressDialog(this)
        loader.setCancelable(false)
        loader.setMessage("Loading...")

        binding.apply {
            button.setOnClickListener {
                loader.show()
                with(this) {
                    viewModel.signup(
                        etFirstname.text.toString(),
                        etLastname.text.toString(),
                        etEmail.text.toString(),
                        etEmail.text.toString().username(),
                        etPassword.text.toString(),
                    )
                }
            }
        }

        viewModel.signup.observe(this) {
            when (it) {
                is Resource.Loading -> ""
                is Resource.Failure -> message(it.errorCode.toString())
                is Resource.Success -> {
                    pref.isLogin = true
                    message(it.value.message)
                    openActivity(HomeActivity::class.java)
                    finishAffinity()
                }
            }
            loader.hide()

        }
    }


}