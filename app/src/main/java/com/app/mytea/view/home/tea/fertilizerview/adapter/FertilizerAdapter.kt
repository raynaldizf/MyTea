package com.app.mytea.view.home.tea.fertilizerview.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app.mytea.data.model.response.DataXX
import com.app.mytea.data.model.response.DataXXXXXXXXX
import com.app.mytea.databinding.ItemListBinding
import com.app.mytea.view.home.tea.pestview.adapter.PestAdapter

class FertilizerAdapter(private val itemList: List<DataXXXXXXXXX>) : RecyclerView.Adapter<FertilizerAdapter.ViewHolder>() {
    class ViewHolder(val binding : ItemListBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        Glide.with(holder.itemView.context)
//            .load(itemList[position].image)
//            .into(holder.binding.ivProductImage)
        holder.binding.tvProductName.text = itemList[position].name
        val maxWords = 2
        val description = itemList[position].description
        val words = description!!.split(" ")
        val truncatedDescription = if (words.size > maxWords) {
            words.take(maxWords).joinToString(" ") + " ..."
        } else {
            description
        }
        holder.binding.tvProductDescription.text = truncatedDescription
        holder.binding.cardViewPest.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("id", itemList[position].id.toString())
            Navigation.findNavController(it).navigate(com.app.mytea.R.id.action_fertilizerFragment_to_detailFertilizerFragment, bundle)
        }
    }
}