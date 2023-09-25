package com.app.mytea.view.home.shop.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.mytea.R
import com.app.mytea.data.model.response.DataX
import com.app.mytea.databinding.ShopItemListBinding
import com.bumptech.glide.Glide

class ListShopItemAdapter(var shopItems: List<DataX>) : RecyclerView.Adapter<ListShopItemAdapter.ViewHolder>() {

    class ViewHolder(val binding: ShopItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ShopItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shopItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(shopItems[position].image)
            .into(holder.binding.ivProductImage)
        holder.binding.tvProductName.text = shopItems[position].name
        holder.binding.idCard.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("id", shopItems[position].id.toString())
            findNavController(it).navigate(R.id.action_shopFragment_to_shopDetailFragment, bundle)
        }
    }
}
