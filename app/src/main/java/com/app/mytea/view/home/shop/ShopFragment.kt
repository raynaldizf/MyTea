package com.app.mytea.view.home.shop

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mytea.R
import com.app.mytea.data.datastore.SharedPref
import com.app.mytea.databinding.FragmentShopBinding
import com.app.mytea.view.home.shop.adapter.CategoryAdapter
import com.app.mytea.view.home.shop.adapter.HeadAdapter
import com.app.mytea.view.home.shop.adapter.ListShopItemAdapter
import com.app.mytea.view.home.shop.adapter.ProductByCategory

class ShopFragment : Fragment(), CategoryAdapter.OnItemClickListener {
    private lateinit var binding: FragmentShopBinding
    private lateinit var sharedPref: SharedPref
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var headAdapter: HeadAdapter
    private lateinit var shopItemList: ListShopItemAdapter
    private lateinit var viewModelShop: ViewModelShop
    private var tokens = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPref(requireContext())
        viewModelShop = ViewModelProvider(requireActivity()).get(ViewModelShop::class.java)
        categoryAdapter = CategoryAdapter(listOf(), this)


        viewModelShop.allLiveDataProduct().observe(viewLifecycleOwner){
            if(it != null){
                binding.listItemRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                val adapter = ListShopItemAdapter(it)
                binding.listItemRecyclerView.adapter = adapter
            }else{
                binding.listItemRecyclerView.visibility = View.GONE
            }
        }

        viewModelShop.allLiveDataCategories().observe(viewLifecycleOwner){
            if(it != null){


                binding.categoryRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                val adapter = CategoryAdapter(it,this)
                binding.categoryRecyclerView.adapter = adapter
                binding.categoryRecyclerView.apply {
                    addItemDecoration(CategoryItemDecoration())
                }
            }else{
                binding.categoryRecyclerView.visibility = View.GONE
            }
        }

        sharedPref.getToken.asLiveData().observe(viewLifecycleOwner) { token ->
            when (token) {
                "Undefined" -> Log.d("Token Gagal", "Undefined token $token")
                else -> {
                    Log.d("Token Berhasil", "Get token $token")
                    tokens = token

                }
            }
            viewModelShop.getProduct(tokens)
            viewModelShop.getCategory(tokens)

        }


        binding.searchProduct.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                val filteredPests = viewModelShop.searchProduct(query.orEmpty())
                val adapter = ListShopItemAdapter(filteredPests!!)
                binding.listItemRecyclerView.adapter = adapter
                return true
            }
        })

    }

    override fun onItemClick(categoryId: String) {
        viewModelShop.allLiveDataCategoriesDetail().observe(viewLifecycleOwner) { categoryDetails ->
            if (categoryDetails != null) {
                binding.listItemRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                val adapter = ProductByCategory(categoryDetails)
                binding.listItemRecyclerView.adapter = adapter
            } else {
                binding.listItemRecyclerView.visibility = View.GONE
            }
        }

        sharedPref.getToken.asLiveData().observe(viewLifecycleOwner) { token ->
            when (token) {
                "Undefined" -> Log.d("Token Gagal", "Undefined token $token")
                else -> {
                    Log.d("Token Berhasil", "Get token $token")
                    tokens = token
                }
            }
            viewModelShop.getDetailCategory(tokens, categoryId)
        }
    }

    inner class CategoryItemDecoration : RecyclerView.ItemDecoration() {
        private val spacing = resources.getDimensionPixelSize(R.dimen.category_item_spacing)
        private val edgeSpacing = spacing / 2

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view)
            val itemCount = parent.adapter?.itemCount ?: 0

            if (position > 0) {
                outRect.left = spacing
            }

            if (position < itemCount - 1) {
                outRect.right = spacing
            }

            if (position == 0) {
                outRect.left = edgeSpacing
            }
            if (position == itemCount - 1) {
                outRect.right = edgeSpacing
            }
        }
    }

}
