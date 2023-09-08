package com.app.mytea.view.home.tea

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        }

        binding.cardFertilizer.setOnClickListener{

        }

        binding.cardHarvest.setOnClickListener{

        }

        binding.cardPest.setOnClickListener{

        }
    }
}