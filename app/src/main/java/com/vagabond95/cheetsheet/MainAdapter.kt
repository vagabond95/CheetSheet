package com.vagabond95.cheetsheet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vagabond95.cheetsheet.databinding.ViewholderMainBinding

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private val items: MutableList<MainItem> = arrayListOf()

    fun setItems(items: List<MainItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ViewholderMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}

class MainViewHolder(private val binding: ViewholderMainBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MainItem) {
        binding.item = item
    }
}

data class MainItem(val text: String, val onClick: () -> Unit)
