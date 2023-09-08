package com.app.mytea.view.home.tea

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.mytea.R
import com.app.mytea.databinding.FragmentDetailPestBinding
import com.app.mytea.view.home.tea.adapter.MyPagerAdapter
import com.app.mytea.view.home.tea.adapter.PestPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class DetailPestFragment : Fragment() {
    lateinit var binding : FragmentDetailPestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailPestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val pagerAdapter = PestPagerAdapter(this)
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Detail"
                1 -> tab.text = "How to Use"
            }
        }.attach()
    }

}