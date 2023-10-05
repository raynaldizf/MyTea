package com.app.mytea.view.home.tea

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.mytea.R
import com.app.mytea.databinding.FragmentTeaBinding

class TeaFragment : Fragment() {
    lateinit var binding : FragmentTeaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTeaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardTea.setOnClickListener{
            findNavController().navigate(R.id.action_teaFragment_to_teaViewFragment)
        }

        binding.cardFertilizer.setOnClickListener{
            findNavController().navigate(R.id.action_teaFragment_to_fertilizerFragment)
        }

        binding.cardHarvest.setOnClickListener{
            findNavController().navigate(R.id.action_teaFragment_to_teaDetailFragment)
        }

        binding.cardPest.setOnClickListener{
            findNavController().navigate(R.id.action_teaFragment_to_pestFragment)
        }

        binding.btnBack.setOnClickListener{
            findNavController().navigateUp()
        }


    }
}