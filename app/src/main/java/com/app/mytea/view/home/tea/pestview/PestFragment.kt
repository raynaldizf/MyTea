package com.app.mytea.view.home.tea.pestview

import androidx.appcompat.widget.SearchView
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.mytea.data.datastore.SharedPref
import com.app.mytea.databinding.FragmentPestBinding
import com.app.mytea.view.home.tea.TeaViewModel
import com.app.mytea.view.home.tea.pestview.adapter.PestAdapter

class PestFragment : Fragment() {
    lateinit var binding: FragmentPestBinding
    private lateinit var viewModel : TeaViewModel
    lateinit var sharedPref: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(TeaViewModel::class.java)
        sharedPref = SharedPref(requireContext())
        binding.btnBack.setOnClickListener{
            findNavController().navigateUp()
        }

        viewModel.allLiveDataPest().observe(viewLifecycleOwner) { pestList ->
            // Update the UI when data changes
            pestList?.let { data ->
                val adapter = PestAdapter(data)
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
                }
            }
            viewModel.getPest(token)

        }


        binding.searchPest.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                val filteredPests = viewModel.searchPests(query.orEmpty())
                val adapter = PestAdapter(filteredPests!!)
                binding.listItemRecyclerView.adapter = adapter
                return true
            }
        })

    }
}
