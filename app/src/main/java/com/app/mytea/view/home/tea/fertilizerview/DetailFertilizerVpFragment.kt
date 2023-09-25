package com.app.mytea.view.home.tea.fertilizerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.mytea.R
import com.app.mytea.databinding.FragmentDetailFertilizerVpBinding
import com.app.mytea.view.home.tea.TeaViewModel

class DetailFertilizerVpFragment : Fragment() {
    lateinit var binding : FragmentDetailFertilizerVpBinding
    private lateinit var viewModel : TeaViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailFertilizerVpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(TeaViewModel::class.java)
        val fertilizerId = arguments?.getString("id")
        val tokensId = arguments?.getString("token")
        viewModel.allLiveDataDetailFertilizer().observe(viewLifecycleOwner) {
            if (it != null) {
                binding.textDetail.text = it.detail
            }
        }
        viewModel.getDetailFertilizer(tokensId!!, fertilizerId.toString())
    }

}