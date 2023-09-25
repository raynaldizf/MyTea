package com.app.mytea.view.home.tea.fertilizerview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.mytea.R
import com.app.mytea.data.datastore.SharedPref
import com.app.mytea.databinding.FragmentFertilizerBinding
import com.app.mytea.view.home.tea.TeaViewModel
import com.app.mytea.view.home.tea.fertilizerview.adapter.FertilizerAdapter
import com.app.mytea.view.home.tea.pestview.adapter.PestAdapter

class FertilizerFragment : Fragment() {
    lateinit var binding : FragmentFertilizerBinding
    private lateinit var viewModel : TeaViewModel
    lateinit var sharedPref: SharedPref
    private var tokens = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFertilizerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(TeaViewModel::class.java)
        sharedPref = SharedPref(requireContext())
        binding.btnBack.setOnClickListener{
            findNavController().navigateUp()
        }

        viewModel.allLiveDataFertilizer().observe(viewLifecycleOwner) { pestList ->
            // Update the UI when data changes
            pestList?.let { data ->
                val adapter = FertilizerAdapter(data)
                val gridLayoutManager = GridLayoutManager(requireContext(), 2)
                binding.listItemRecyclerView.layoutManager = gridLayoutManager
                binding.listItemRecyclerView.adapter = adapter
            }
        }

        sharedPref.getToken.asLiveData().observe(viewLifecycleOwner) { token ->
            when (token) {
                "Undefined" -> Log.d("Token Gagal", "Undefined token $token")
                else -> {
                    Log.d("Token Berhasil", "Get token $token")
                    tokens = token

                }
            }
            viewModel.getFertilizer(tokens)

        }

        binding.searchFertilizer.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                val filteredFertilizer = viewModel.searchPesticides(query.orEmpty())
                val adapter = FertilizerAdapter(filteredFertilizer!!)
                binding.listItemRecyclerView.adapter = adapter
                return true
            }
        })
    }

}