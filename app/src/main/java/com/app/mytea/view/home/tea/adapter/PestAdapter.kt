package com.app.mytea.view.home.tea.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app.mytea.data.model.response.DataXX
import com.app.mytea.databinding.ItemCategoryBinding
import com.app.mytea.databinding.ItemListBinding

class PestAdapter(private val itemList: List<DataXX>) : RecyclerView.Adapter<PestAdapter.ViewHolder>() {
    class ViewHolder(val binding : ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.tvProductName.text = itemList[position].name
        holder.binding.tvProductDescription.text = itemList[position].description
        holder.binding.cardViewPest.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("name", itemList[position].name)
            bundle.putString("description", itemList[position].description)
            Navigation.findNavController(it).navigate(com.app.mytea.R.id.action_pestFragment_to_detailPestFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
