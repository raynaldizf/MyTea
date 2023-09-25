package com.app.mytea.view.home.consultation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mytea.R
import com.app.mytea.data.datastore.SharedPref
import com.app.mytea.databinding.FragmentMessageBinding
import com.app.mytea.view.home.consultation.adapter.MessageAdapter
import com.app.mytea.view.home.shop.adapter.ListShopItemAdapter

class MessageFragment : Fragment() {
    lateinit var binding : FragmentMessageBinding
    lateinit var viewModel : ViewModelConsultant
    lateinit var sharedPref: SharedPref


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModelConsultant::class.java)
        sharedPref = SharedPref(requireContext())

        viewModel.allLiveDataExpert().observe(viewLifecycleOwner) {
            if (it != null) {
                Log.d("TAG", "onViewCreated: $it")
                binding.rvExpert.layoutManager = LinearLayoutManager(context,
                    LinearLayoutManager.VERTICAL,false)
                val adapter = MessageAdapter(it)
                binding.rvExpert.adapter = adapter
            } else {
                Log.d("TAG", "onViewCreated: null")
            }
        }

        var tokens = ""
        sharedPref.getToken.asLiveData().observe(viewLifecycleOwner) { token ->
            when (token) {
                "Undefined" -> Log.d("Token Gagal", "Undefined token $token")
                else -> {
                    Log.d("Token Berhasil", "Get token $token")
                    tokens = token

                }
            }
            viewModel.getExpert(tokens)

        }

    }

}