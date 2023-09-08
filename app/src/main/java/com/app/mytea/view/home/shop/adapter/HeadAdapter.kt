package com.app.mytea.view.home.shop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mytea.databinding.HeadItemBinding

class HeadAdapter(private val headItems: List<String>) : RecyclerView.Adapter<HeadAdapter.ViewHolder>() {

    class ViewHolder(val binding: HeadItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HeadItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return headItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}
