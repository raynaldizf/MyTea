package com.app.mytea.view.home.shop

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mytea.R
import com.app.mytea.data.model.response.Category
import com.app.mytea.data.model.response.DataX
import com.app.mytea.databinding.FragmentShopBinding
import com.app.mytea.view.home.shop.adapter.CategoryAdapter
import com.app.mytea.view.home.shop.adapter.HeadAdapter
import com.app.mytea.view.home.shop.adapter.ListShopItemAdapter

class ShopFragment : Fragment(), CategoryAdapter.OnItemClickListener {
    lateinit var binding: FragmentShopBinding
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var headAdapter: HeadAdapter
    lateinit var shopItemList: ListShopItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categories = listOf("Organik", "Non Organik", "Pembunuh Hama", "Penyubur Tanaman")
        categoryAdapter = CategoryAdapter(categories, this)

        binding.categoryRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
            addItemDecoration(CategoryItemDecoration())


            val headItems = listOf("Head Item 1", "Head Item 2", "Head Item 3")
            headAdapter = HeadAdapter(headItems)

            binding.headRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = headAdapter
                addItemDecoration(HeadItemDecoration())
            }
        }

        val shopItems = createSampleShopItems()
        shopItemList = ListShopItemAdapter(shopItems)

        binding.listItemRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = shopItemList
        }
    }

    // Example function to create sample shop items
    private fun createSampleShopItems(): List<DataX> {
        return listOf(
            DataX(
                category = Category("Category 1", 1, "Jamal","2023-08-23"),
                categoryId = 1,
                createdAt = "2023-08-23",
                description = "Sample description for item 1",
                id = 1,
                image = "image_url_1",
                name = "Item 1",
                price = 1000,
                qty = 10,
                updatedAt = "2023-08-23"
            ),
            DataX(
                category = Category("Category 2", 2, "Jago","2023-08-23"),
                categoryId = 2,
                createdAt = "2023-08-23",
                description = "Sample description for item 2",
                id = 2,
                image = "image_url_2",
                name = "Item 2",
                price = 1500,
                qty = 15,
                updatedAt = "2023-08-23"
            ),
        )
    }

    override fun onItemClick(categoryName: String) {
        val selectedCategoryIndex = categoryAdapter.categories.indexOf(categoryName)
        categoryAdapter.setSelectedPosition(selectedCategoryIndex)
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

    inner class HeadItemDecoration : RecyclerView.ItemDecoration() {
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
