package com.app.mytea.view.basket.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mytea.data.model.response.DataXX
import com.app.mytea.data.model.response.NotCheckout
import com.app.mytea.databinding.ItemListBinding
import com.app.mytea.databinding.ShopItemListBinding
import com.app.mytea.view.home.tea.pestview.adapter.PestAdapter
import com.bumptech.glide.Glide

class BasketAdapter (private val itemList: List<NotCheckout>) : RecyclerView.Adapter<BasketAdapter.ViewHolder>() {
    class ViewHolder(val binding: ShopItemListBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ShopItemListBinding.inflate(
            android.view.LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvProductName.text = itemList[position].product!!.name
//        Glide.with(holder.itemView.context)
//            .load(itemList[position].product!!.image)
//            .into(holder.binding.ivProductImage)
    }
}