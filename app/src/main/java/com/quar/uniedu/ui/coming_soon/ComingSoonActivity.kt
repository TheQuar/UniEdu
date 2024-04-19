package com.quar.uniedu.ui.coming_soon

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.quar.uniedu.R
import com.quar.uniedu.ui.OnOptionSelected
import com.quar.uniedu.ui.ToolbarEvent
import com.quar.uniedu.ui.UniEduToolBar
import com.quar.uniedu.ui.home.HomeActivity
import com.quar.uniedu.utils.logout
import com.quar.uniedu.utils.openActivity

class ComingSoonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coming_soon)

        findViewById<TextView>(R.id.btn_back).setOnClickListener {
            onBackPressed()
        }

        findViewById<UniEduToolBar>(R.id.uniEduToolBar).setListener(object : OnOptionSelected {
            override fun onSelected(event: ToolbarEvent) {
                when (event) {
                    ToolbarEvent.HOME -> {
                        openActivity(HomeActivity::class.java)
                        finishAffinity()
                    }

                    ToolbarEvent.LANG -> ""
                    ToolbarEvent.CONFIG -> ""
                    ToolbarEvent.EXIT -> logout()
                }
            }
        })
    }
}