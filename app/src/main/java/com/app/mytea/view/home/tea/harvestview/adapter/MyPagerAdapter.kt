package com.app.mytea.view.home.tea.harvestview.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.mytea.view.home.tea.harvestview.TeaCriteriaFragment
import com.app.mytea.view.home.tea.harvestview.TeaProcedureFragment

class MyPagerAdapter(fragment : Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return TeaCriteriaFragment()
            1 -> return TeaProcedureFragment()
            else -> return TeaCriteriaFragment()
        }
    }
}
