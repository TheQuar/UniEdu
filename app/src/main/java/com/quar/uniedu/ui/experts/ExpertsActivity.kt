package com.quar.uniedu.ui.experts

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.quar.uniedu.R
import com.quar.uniedu.databinding.ActivityExpertsBinding
import com.quar.uniedu.ui.OnOptionSelected
import com.quar.uniedu.ui.ToolbarEvent
import com.quar.uniedu.ui.coming_soon.ComingSoonActivity
import com.quar.uniedu.ui.home.HomeActivity
import com.quar.uniedu.utils.logout
import com.quar.uniedu.utils.openActivity

class ExpertsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExpertsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityExpertsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.uniEduToolBar.setListener(object : OnOptionSelected {
            override fun onSelected(event: ToolbarEvent) {
                when (event) {
                    ToolbarEvent.HOME -> onBackPressed()
                    ToolbarEvent.LANG -> openActivity(ComingSoonActivity::class.java)
                    ToolbarEvent.CONFIG -> openActivity(ComingSoonActivity::class.java)
                    ToolbarEvent.EXIT -> logout()
                }
            }
        })

        setupViewPager()
    }

    private fun setupViewPager() {
        val tabItemName = resources.getStringArray(R.array.my_action_tab_item)
        binding.viewPager2.apply {
            adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position -> }.attach()


        binding.tabLayout.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(p0: TabLayout.Tab?) {
                binding.textView2.text = tabItemName[p0?.position!!]
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }
        })

    }
}