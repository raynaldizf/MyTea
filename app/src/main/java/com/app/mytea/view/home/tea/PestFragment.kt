package com.app.mytea.view.home.tea

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.app.mytea.R
import com.app.mytea.data.model.response.DataXX
import com.app.mytea.databinding.FragmentPestBinding
import com.app.mytea.view.home.tea.adapter.PestAdapter

class PestFragment : Fragment() {
    lateinit var binding: FragmentPestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemList = listOf(
            DataXX(
                createdAt = "2023-09-08",
                description = "Description for Item 1",
                detail = "Details for Item 1",
                howToControll = "Control instructions for Item 1",
                id = 1,
                image = "URL_or_Path_for_Item_1_Image",
                name = "Item 1",
                updatedAt = "2023-09-08"
            ),
            DataXX(
                createdAt = "2023-09-09",
                description = "Description for Item 2",
                detail = "Details for Item 2",
                howToControll = "Control instructions for Item 2",
                id = 2,
                image = "URL_or_Path_for_Item_2_Image",
                name = "Item 2",
                updatedAt = "2023-09-09"
            ),
        )

        val adapter = PestAdapter(itemList)
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.listItemRecyclerView.layoutManager = gridLayoutManager
        binding.listItemRecyclerView.adapter = adapter
    }
}
