package com.quar.uniedu.ui.introduction

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.quar.uniedu.databinding.ActivityIntroBinding
import com.quar.uniedu.ui.home.HomeActivity
import com.quar.uniedu.ui.login.LoginActivity
import com.quar.uniedu.ui.login.SignupActivity
import com.quar.uniedu.utils.openActivity
import com.quar.uniedu.utils.Prefs

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val p = Prefs(this)
        if (p.isLogin) {
            openActivity(HomeActivity::class.java)
            finishAffinity()
        }

        binding.btnSignup.setOnClickListener {
            openActivity(SignupActivity::class.java)
        }

        binding.btnLogin.setOnClickListener {
            openActivity(LoginActivity::class.java)
        }
    }


}