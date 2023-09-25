package com.app.mytea.view.home.tea.pestview.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.mytea.view.home.tea.pestview.ControlPestVpFragment
import com.app.mytea.view.home.tea.pestview.DetailPestVpFragment

class PestPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle,private var bundle : Bundle) : FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> {
                val fragment = DetailPestVpFragment()
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                val fragment = ControlPestVpFragment()
                fragment.arguments = bundle
                return fragment
            }
            else -> {
                val fragment = DetailPestVpFragment()
                fragment.arguments = bundle
                return fragment
            }
        }
    }

}