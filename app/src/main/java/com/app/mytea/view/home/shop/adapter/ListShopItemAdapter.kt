package com.app.mytea.view.home.shop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mytea.R
import com.app.mytea.data.model.response.DataX
import com.app.mytea.databinding.ShopItemListBinding

class ListShopItemAdapter(private val shopItems: List<DataX>) :
    RecyclerView.Adapter<ListShopItemAdapter.ViewHolder>() {

    class ViewHolder(val binding: ShopItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ShopItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return shopItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shopItem = shopItems[position]
        holder.binding.tvProductName.text  = shopItem.name
        holder.binding.ivProductImage.setImageResource(R.drawable.pupuk)
    }
}
