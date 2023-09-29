package com.app.mytea.view.home.consultation

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
import com.app.mytea.databinding.FragmentDetailExpertBinding
import com.bumptech.glide.Glide

class DetailExpertFragment : Fragment() {
    lateinit var binding : FragmentDetailExpertBinding
    lateinit var sharedPref: SharedPref
    lateinit var viewModel : ViewModelConsultant

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailExpertBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModelConsultant::class.java)
        sharedPref = SharedPref(requireContext())

        val id = arguments?.getString("id")

        viewModel.allLiveDataExpertDetail().observe(viewLifecycleOwner){
            if(it != null){
//                Glide.with(requireContext())
//                        .load(it.data!!.image)
//                        .into(binding.photoExpert)
                binding.tvNama.text = it.data!!.name
                binding.eduDescription.text = it.data!!.education
                binding.tvExperienceDescription.text = it.data!!.experience
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
            viewModel.getDetailExpert(tokens,id!!)

        }

        binding.btnBack.setOnClickListener{
            findNavController().navigateUp()
        }
    }

}