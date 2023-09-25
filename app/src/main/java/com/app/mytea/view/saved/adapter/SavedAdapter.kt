package com.app.mytea.view.saved.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.app.mytea.data.model.response.DataXXXXXXX
import com.app.mytea.databinding.SavedItemBinding
import com.app.mytea.view.saved.ViewModelSaved
import com.bumptech.glide.Glide

class SavedAdapter(var shopItems: ArrayList<DataXXXXXXX>,
                   private val viewModel: ViewModelSaved,
                   var token : String
) : RecyclerView.Adapter<SavedAdapter.ViewHolder>() {
    class ViewHolder(val binding: SavedItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SavedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shopItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(shopItems[position].product!!.image)
            .into(holder.binding.imageProduct)
        holder.binding.textProductName.text = shopItems[position].product!!.name
        holder.binding.textProductPrice.text = "Rp.${shopItems[position].product!!.price}"

        holder.binding.imageButtonAction.setOnClickListener {
            viewModel.allLiveDataDelete().observeForever { deleted ->
                if (deleted != null) {
                    viewModel.getDataSaved(token)
                } else {
                    Toast.makeText(holder.itemView.context, "Gagal dihapus", Toast.LENGTH_SHORT).show()
                }
            }
            viewModel.deleteSaved(token, shopItems[position].id.toString())
            Toast.makeText(holder.itemView.context, "Berhasil dihapus", Toast.LENGTH_SHORT).show()
        }
    }


}