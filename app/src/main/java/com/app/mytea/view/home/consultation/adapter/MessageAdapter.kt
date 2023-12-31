package com.app.mytea.view.home.consultation.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app.mytea.data.model.response.DataXXX
import com.app.mytea.databinding.ListExpertBinding
import com.bumptech.glide.Glide

class MessageAdapter(private val itemList: List<DataXXX>) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {
    class ViewHolder(val binding : ListExpertBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListExpertBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        Glide.with(holder.itemView.context)
//            .load(itemList[position].image)
//            .into(holder.binding.ivPhoto)
        holder.binding.tvName.text = itemList[position].name
        holder.binding.cardView.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("id", itemList[position].id.toString())
            Navigation.findNavController(it).navigate(com.app.mytea.R.id.action_messageFragment_to_detailExpertFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
