package com.app.mytea.view.saved

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mytea.R
import com.app.mytea.data.datastore.SharedPref
import com.app.mytea.data.model.response.DataXXXXXXX
import com.app.mytea.databinding.FragmentSavedBinding
import com.app.mytea.view.home.shop.adapter.ListShopItemAdapter
import com.app.mytea.view.saved.adapter.SavedAdapter

class SavedFragment : Fragment() {
    lateinit var binding : FragmentSavedBinding
    lateinit var viewModelSaved: ViewModelSaved
    lateinit var adapterSaved : SavedAdapter
    lateinit var sharedPref: SharedPref
    private var allSavedItems: List<DataXXXXXXX>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPref(requireContext())

        viewModelSaved = ViewModelProvider(requireActivity()).get(ViewModelSaved::class.java)
        val jamal = ViewModelProvider(requireActivity()).get(ViewModelSaved::class.java)
        var tokens = ""
        sharedPref.getToken.asLiveData().observe(viewLifecycleOwner) { token ->
            when (token) {
                "Undefined" -> Log.d("Token Gagal", "Undefined token $token")
                else -> {
                    Log.d("Token Berhasil", "Get token $token")
                    tokens = token

                }
            }
            viewModelSaved.getDataSaved(tokens)

        }

        viewModelSaved.allLiveDataSaved().observe(viewLifecycleOwner) { savedItems ->
            if (savedItems != null) {
                allSavedItems = savedItems

                binding.listItemRecyclerView.layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL, false
                )
                adapterSaved = SavedAdapter(savedItems as ArrayList<DataXXXXXXX>, viewModelSaved, tokens)
                binding.listItemRecyclerView.adapter = adapterSaved
            }
        }

        binding.searchSaved.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModelSaved.searchSavedItems(newText, allSavedItems)
                }
                return true
            }
        })


        viewModelSaved.getFilteredLiveData().observe(viewLifecycleOwner) { filteredItems ->
            if (filteredItems != null) {
                adapterSaved.shopItems = filteredItems as ArrayList<DataXXXXXXX>
                adapterSaved.notifyDataSetChanged()
            }
        }
    }

}