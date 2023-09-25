package com.app.mytea.view.home.tea.fertilizerview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.app.mytea.R
import com.app.mytea.data.datastore.SharedPref
import com.app.mytea.databinding.FragmentDetailFertilizerBinding
import com.app.mytea.view.home.tea.TeaViewModel
import com.app.mytea.view.home.tea.fertilizerview.adapter.FertilizerPagerAdapter
import com.app.mytea.view.home.tea.pestview.adapter.PestPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailFertilizerFragment : Fragment() {
   lateinit var binding : FragmentDetailFertilizerBinding
   private lateinit var viewModel : TeaViewModel
   lateinit var sharedPref: SharedPref


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailFertilizerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(TeaViewModel::class.java)
        sharedPref = SharedPref(requireContext())

        val fertilizerId = arguments?.getString("id")
        Log.d("Test ID", "onViewCreated: $fertilizerId")

        viewModel.allLiveDataDetailFertilizer().observe(viewLifecycleOwner) {
            if (it != null) {
                binding.idName.text = it.name
                binding.idDescription.text = it.description
//                Glide.with(requireActivity()).load(it.image).into(binding.images)
            }
        }

        var tokens = ""
        GlobalScope.launch(Dispatchers.Main) {
            sharedPref.getToken.asLiveData().observe(viewLifecycleOwner) { token ->
                when (token) {
                    "Undefined" -> Log.d("Token Gagal", "Undefined token $token")
                    else -> {
                        Log.d("Token Berhasil", "Get token $token")
                        tokens = token

                        // After getting the token, you can pass it as part of a bundle
                        val bundle = Bundle()
                        bundle.putString("id", fertilizerId)
                        bundle.putString("token", tokens)

                        val pagerAdapter = FertilizerPagerAdapter(childFragmentManager, lifecycle, bundle)
                        binding.viewPager.adapter = pagerAdapter

                        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                            when (position) {
                                0 -> tab.text = "Detail"
                                1 -> tab.text = "How to Control"
                            }
                        }.attach()
                    }
                }
            }
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}