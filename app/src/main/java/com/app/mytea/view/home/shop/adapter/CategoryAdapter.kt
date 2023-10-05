package com.app.mytea.view.home.shop.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mytea.data.model.response.DataXXXXXXXXXXX
import com.app.mytea.databinding.ItemCategoryBinding

class CategoryAdapter(
    val categories: List<DataXXXXXXXXXXX>,
    val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    interface OnItemClickListener {
        fun onItemClick(categoryId: String)
    }

    class ViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.binding.categoryNameButton.text = category.name

        holder.binding.categoryNameButton.setOnClickListener {
            onItemClickListener.onItemClick(category.id.toString()) // Pass the category ID to the listener
        }
        if (position == selectedPosition) {
            holder.binding.categoryNameButton.setTextColor(Color.parseColor("#A2AA7B"))
        } else {
            holder.binding.categoryNameButton.setTextColor(Color.parseColor("#AFAFAF"))
        }
    }

}
