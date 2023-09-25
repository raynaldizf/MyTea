package com.app.mytea.view.home.tea.pestview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.mytea.databinding.FragmentDetailPestVpBinding
import com.app.mytea.view.home.tea.TeaViewModel

class DetailPestVpFragment : Fragment() {
    lateinit var binding : FragmentDetailPestVpBinding
    private lateinit var viewModel : TeaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentDetailPestVpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(TeaViewModel::class.java)
        val pestId = arguments?.getString("id")
        val tokens = arguments?.getString("token")
        viewModel.allLiveDataDetailPest().observe(viewLifecycleOwner) {
            if (it != null) {
                binding.textDetail.text = it.detail
            }
        }
        viewModel.getDetailPest(tokens.toString(), pestId.toString())
    }
}