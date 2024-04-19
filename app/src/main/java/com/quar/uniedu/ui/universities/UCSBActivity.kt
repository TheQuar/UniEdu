package com.quar.uniedu.ui.universities

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.quar.uniedu.R
import com.quar.uniedu.databinding.ActivityUcsbactivityBinding
import com.quar.uniedu.model.University
import com.quar.uniedu.ui.OnOptionSelected
import com.quar.uniedu.ui.ToolbarEvent
import com.quar.uniedu.ui.coming_soon.ComingSoonActivity
import com.quar.uniedu.ui.home.HomeActivity
import com.quar.uniedu.utils.Constants
import com.quar.uniedu.utils.logout
import com.quar.uniedu.utils.openActivity
import com.squareup.picasso.Picasso

class UCSBActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUcsbactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUcsbactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myIntent = intent
        val data = myIntent.getSerializableExtra("data") as University


        binding.apply {
            with(data) {
                tvTitle.text = full_name
                tvDetails.text =
                    "Location: $location\nAcceptance rate: $acceptance_rate\nWorld University Ranking: #$rank"
                Picasso.get()
                    .load(Constants.BASE_URL + img)
                    .into(ivPhoto)
                tvDescription.text = description

                tvWebsite.text =
                    "Official website:\n" + Html.fromHtml("<a href=\"$website\"> $website</a>")
                rvRequirement.adapter = RequirementAdapter(requerments.split(","))
                rvRequirement.layoutManager = GridLayoutManager(this@UCSBActivity, 2)

                tvBack.setOnClickListener {
                    onBackPressed()
                }
            }
            uniEduToolBar2.setListener(object : OnOptionSelected {
                override fun onSelected(event: ToolbarEvent) {
                    when (event) {
                        ToolbarEvent.HOME -> {
                            openActivity(HomeActivity::class.java)
                            finishAffinity()
                        }

                        ToolbarEvent.LANG -> openActivity(ComingSoonActivity::class.java)
                        ToolbarEvent.CONFIG -> openActivity(ComingSoonActivity::class.java)
                        ToolbarEvent.EXIT -> logout()
                    }
                }
            })

        }
    }

}