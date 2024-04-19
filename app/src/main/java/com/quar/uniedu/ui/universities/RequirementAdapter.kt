package com.quar.uniedu.ui.universities

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quar.uniedu.databinding.RvRequirementBinding
import com.quar.uniedu.databinding.RvUniversityItemBinding
import com.quar.uniedu.model.University
import com.quar.uniedu.utils.Constants
import com.squareup.picasso.Picasso

class RequirementAdapter(private var list: List<String> = listOf()) :
    RecyclerView.Adapter<RequirementAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RvRequirementBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RvRequirementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            with(list[position]) {
                tvTitle.text = this
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<String>) {
        list = data
        notifyDataSetChanged()
    }
}