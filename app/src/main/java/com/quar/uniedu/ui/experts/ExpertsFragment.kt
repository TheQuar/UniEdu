package com.quar.uniedu.ui.experts

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quar.uniedu.databinding.FragmentExpertsBinding

class ExpertsFragment : Fragment() {

    private var _expertsBinding: FragmentExpertsBinding? = null
    private val expertsBinding get() = _expertsBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _expertsBinding = FragmentExpertsBinding.inflate(inflater, container, false)
        return expertsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        expertsBinding.experts.btnExpert.setOnClickListener {
            startActivity(Intent(context, AboutExpertActivity::class.java))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _expertsBinding = null
    }


}