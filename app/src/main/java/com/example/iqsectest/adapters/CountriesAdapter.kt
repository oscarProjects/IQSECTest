package com.example.iqsectest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iqsectest.data.Country
import com.example.iqsectest.databinding.ItemListBinding
import com.example.iqsectest.listener.ClickListener

class CountriesAdapter(private val items: List<Country>,
                       private val listener: ClickListener
): RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

        holder.itemView.setOnClickListener {
            listener.onClickCard(items[position])
        }
    }

    inner class ViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Country) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

}