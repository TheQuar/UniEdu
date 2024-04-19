package com.quar.uniedu.ui.universities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.quar.uniedu.databinding.ActivityUniversitiesBinding
import com.quar.uniedu.network.Resource
import com.quar.uniedu.ui.OnOptionSelected
import com.quar.uniedu.ui.ToolbarEvent
import com.quar.uniedu.ui.coming_soon.ComingSoonActivity
import com.quar.uniedu.ui.home.HomeActivity
import com.quar.uniedu.utils.logout
import com.quar.uniedu.utils.message
import com.quar.uniedu.utils.openActivity

class UniversitiesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUniversitiesBinding
    private val adapter = UniversityAdapter()
    private var isOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUniversitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loader = ProgressDialog(this)
        loader.setCancelable(false)
        loader.setMessage("Loading...")

        val viewModel = ViewModelProvider(this)[UniversityViewModel::class.java]

        adapter.setOnItemClickListener {
            val a = Intent(this, UCSBActivity::class.java)
            a.putExtra("data", it)
            startActivity(a)
        }
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@UniversitiesActivity)

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

            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    adapter.filter.filter(p0.toString())
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })
        }

        viewModel.university.observe(this) {
            when (it) {
                is Resource.Failure -> message(it.errorCode.toString())
                is Resource.Success -> adapter.update(it.value.universities)
                else -> ""
            }

            loader.hide()

        }

        if (!isOpen) {
            loader.show()
            viewModel.getUniversity()
        }
    }

    override fun onResume() {
        super.onResume()
        isOpen = true
    }
}