package com.app.mytea.view.home.tea.harvestview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.mytea.databinding.FragmentTeaDetailBinding
import com.app.mytea.view.home.tea.harvestview.adapter.MyPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class TeaDetailFragment : Fragment() {
    lateinit var binding : FragmentTeaDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTeaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener{
            findNavController().navigateUp()
        }

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val pagerAdapter = MyPagerAdapter(this)
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            // Set tab titles here
            when (position) {
                0 -> tab.text = "Criteria"
                1 -> tab.text = "Prosedure"
            }
        }.attach()
    }
}