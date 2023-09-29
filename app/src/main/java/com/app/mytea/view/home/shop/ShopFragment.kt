package com.app.mytea.view.home.shop

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mytea.R
import com.app.mytea.data.datastore.SharedPref
import com.app.mytea.data.model.response.DataX
import com.app.mytea.data.model.response.DataXXXXXXXXXXX
import com.app.mytea.data.network.ApiClient
import com.app.mytea.databinding.FragmentShopBinding
import com.app.mytea.view.home.shop.adapter.CategoryAdapter
import com.app.mytea.view.home.shop.adapter.HeadAdapter
import com.app.mytea.view.home.shop.adapter.ListShopItemAdapter
import com.app.mytea.view.home.tea.pestview.adapter.PestAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

//        categoryAdapter = CategoryAdapter(ArrayList(), this)
        val categories = listOf("Organik", "Non Organik", "Pembunuh Hama", "Penyubur Tanaman")
        categoryAdapter = CategoryAdapter(categories, this)

        setupCategoryRecyclerView()

        viewModelShop.allLiveDataProduct().observe(viewLifecycleOwner){
            if(it != null){
                binding.listItemRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                val adapter = ListShopItemAdapter(it)
                binding.listItemRecyclerView.adapter = adapter
            }else{
                binding.listItemRecyclerView.visibility = View.GONE
            }
        }

//        viewModelShop.allLiveDataCategories().observe(viewLifecycleOwner){
//            if(it != null){
//                binding.categoryRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
//                categoryAdapter = CategoryAdapter(it,this)
//                binding.categoryRecyclerView.adapter = categoryAdapter
//            }else{
//                binding.categoryRecyclerView.visibility = View.GONE
//            }
//        }


        sharedPref.getToken.asLiveData().observe(viewLifecycleOwner) { token ->
            when (token) {
                "Undefined" -> Log.d("Token Gagal", "Undefined token $token")
                else -> {
                    Log.d("Token Berhasil", "Get token $token")
                    tokens = token

                }
            }
            viewModelShop.getProduct(tokens)

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

    override fun onItemClick(categoryName: String) {
        Toast.makeText(requireContext(), categoryName, Toast.LENGTH_SHORT).show()
    }

    private fun setupCategoryRecyclerView() {
        binding.categoryRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
            addItemDecoration(CategoryItemDecoration())
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
