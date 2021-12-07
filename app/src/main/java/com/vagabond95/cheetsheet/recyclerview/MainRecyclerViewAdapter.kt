package com.vagabond95.cheetsheet.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vagabond95.cheetsheet.databinding.ViewholderMainRecyclerviewBinding

class MainRecyclerViewAdapter : RecyclerView.Adapter<MainViewHolder>() {
    val items = arrayListOf<MainModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ViewholderMainRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return items.size
    }
}

data class MainModel(val id: Int = 0)

class MainViewHolder(binding: ViewholderMainRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {

}