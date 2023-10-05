package com.app.mytea.view.home.tea.teaview.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.mytea.view.home.tea.fertilizerview.ControlFertilizerVpFragment
import com.app.mytea.view.home.tea.fertilizerview.DetailFertilizerVpFragment

class TeaPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private var bundle : Bundle) : FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> {
                val fragment = DetailFertilizerVpFragment()
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                val fragment = ControlFertilizerVpFragment()
                fragment.arguments = bundle
                return fragment
            }
            else -> {
                val fragment = DetailFertilizerVpFragment()
                fragment.arguments = bundle
                return fragment
            }
        }
    }

}