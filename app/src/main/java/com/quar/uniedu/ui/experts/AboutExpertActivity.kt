package com.quar.uniedu.ui.experts

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.quar.uniedu.R
import com.quar.uniedu.ui.OnOptionSelected
import com.quar.uniedu.ui.ToolbarEvent
import com.quar.uniedu.ui.ToolbarEvent.*
import com.quar.uniedu.ui.UniEduToolBar
import com.quar.uniedu.ui.coming_soon.ComingSoonActivity
import com.quar.uniedu.ui.home.HomeActivity
import com.quar.uniedu.utils.logout
import com.quar.uniedu.utils.openActivity

class AboutExpertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about_expert)

        val toolbar = findViewById<UniEduToolBar>(R.id.uniEduToolBar3)

        toolbar.setListener(object: OnOptionSelected {
            override fun onSelected(event: ToolbarEvent) {
                when (event) {
                    HOME -> {
                        openActivity(HomeActivity::class.java)
                        finishAffinity()
                    }
                    LANG -> openActivity(ComingSoonActivity::class.java)
                    CONFIG -> openActivity(ComingSoonActivity::class.java)
                    EXIT -> logout()
                }
            }
        })
    }
}