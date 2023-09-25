package com.app.mytea.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.mytea.R
import com.app.mytea.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        viewModel.allLiveDataUser().observe(viewLifecycleOwner) { userDataList ->
            userDataList?.let {
                if (it.isNotEmpty()) {
                    binding.username.text = it[0].username

                }
            }
        }

        viewModel.getDataUser("20|XZFCCFWyVVNwPowyzK6eduU5q95YcCTQkHcrdawr")

        binding.shopCard.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_shopFragment)
        }
        binding.consulCard.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_messageFragment)
        }
        binding.teaCard.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_teaFragment)
        }
        binding.statisticCard.setOnClickListener{
//            findNavController().navigate(R.id.action_homeFragment_to_statisticFragment)
        }
    }
}