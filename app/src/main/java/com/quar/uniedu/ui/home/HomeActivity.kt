package com.quar.uniedu.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.quar.uniedu.R
import com.quar.uniedu.databinding.ActivityHomeBinding
import com.quar.uniedu.ui.OnOptionSelected
import com.quar.uniedu.ui.ToolbarEvent
import com.quar.uniedu.ui.ToolbarEvent.*
import com.quar.uniedu.ui.coming_soon.ComingSoonActivity
import com.quar.uniedu.ui.experts.ExpertsActivity
import com.quar.uniedu.ui.universities.UCSBActivity
import com.quar.uniedu.ui.universities.UniversitiesActivity
import com.quar.uniedu.utils.logout
import com.quar.uniedu.utils.message
import com.quar.uniedu.utils.openActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            uniEduToolBar.setListener(object:OnOptionSelected{
                override fun onSelected(event: ToolbarEvent) {
                    when (event) {
                        EXIT -> logout()
                        HOME -> " "
                        else -> openActivity(ComingSoonActivity::class.java)
                    }
                }
            })

            btnExpert.setOnClickListener {
                openActivity(ExpertsActivity::class.java)
            }

            btnUniversities.setOnClickListener {
                openActivity(UniversitiesActivity::class.java)
            }

            btnLearnS.setOnClickListener {
                openActivity(ComingSoonActivity::class.java)
            }
        }
    }


}