package com.quar.uniedu.ui.universities

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quar.uniedu.databinding.RvUniversityItemBinding
import com.quar.uniedu.model.University
import com.quar.uniedu.utils.Constants
import com.squareup.picasso.Picasso
import android.widget.Filter
import android.widget.Filterable

class UniversityAdapter(private var universityList: ArrayList<University> = arrayListOf()) :
    RecyclerView.Adapter<UniversityAdapter.ViewHolder>() , Filterable {

    private var universityListFiltered: ArrayList<University> = ArrayList()

    private var onItemClickListener: ((University) -> Unit)? = null

    fun setOnItemClickListener(listener: (University) -> Unit) {
        onItemClickListener = listener
    }

    inner class ViewHolder(val binding: RvUniversityItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() = universityListFiltered.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RvUniversityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            with(universityListFiltered[position]) {

                Picasso.get()
                    .load(Constants.BASE_URL + img)
                    .into(ivPhoto)

                tvTitle.text = full_name
                tvLocation.text = "Location: $location"
                tvAcceptance.text = "Acceptance rate: " + acceptance_rate
                tvWur.text = "World University Ranking: #" + rank
            }


        }

        holder.binding.root.setOnClickListener {

            onItemClickListener?.invoke(universityListFiltered[position])
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) universityListFiltered = universityList else {
                    val filteredList = ArrayList<University>()
                    universityList
                        .filter {
                            (it.full_name.lowercase().startsWith(constraint!!.toString().lowercase()))

                        }
                        .forEach { filteredList.add(it) }
                    universityListFiltered = filteredList

                }
                return FilterResults().apply { values = universityListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                universityListFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<University>
                notifyDataSetChanged()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<University>) {
        universityList = data as ArrayList<University>
        universityListFiltered = data as ArrayList<University>
        notifyDataSetChanged()
    }
}