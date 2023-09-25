package com.app.mytea.view.home.shop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.mytea.databinding.FragmentShopDetailBinding
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class ShopDetailFragment : Fragment() {
    lateinit var binding : FragmentShopDetailBinding
    private lateinit var viewModelShop: ViewModelShop

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShopDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // bundle get data
        val id = arguments?.getString("id")

        Log.d("Test ID", "onViewCreated: $id")

        binding.btnBack.setOnClickListener{
            findNavController().navigateUp()
        }

        viewModelShop = ViewModelProvider(requireActivity()).get(ViewModelShop::class.java)
        viewModelShop.getDetailProduct("20|XZFCCFWyVVNwPowyzK6eduU5q95YcCTQkHcrdawr", id.toString())
        viewModelShop.allLiveDataDetailProduct().observe(viewLifecycleOwner){
            if (it != null){
                binding.textDescription.text = it.description
                binding.textNameProduct.text = it.name
            }
        }

        var quantity = 0 // Initialize the quantity to 0

        binding.btnPlus.setOnClickListener {
            quantity++
            binding.textQuantity.text = quantity.toString()
        }

        binding.btnMinus.setOnClickListener {
            if (quantity > 0) {
                quantity--
                binding.textQuantity.text = quantity.toString()
            }
        }

        binding.btnCheckout.setOnClickListener {
            viewModelShop.allLiveDataAddCart().observe(viewLifecycleOwner){
                if (it != null){
//                    Toast.makeText(requireContext(), "Berhasil ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(), "Gagal ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
                }
            }
            viewModelShop.addCart("20|XZFCCFWyVVNwPowyzK6eduU5q95YcCTQkHcrdawr",id!!.toRequestBody("text/plain".toMediaType()),quantity.toString().toRequestBody("text/plain".toMediaType()))
            Toast.makeText(requireContext(), "Berhasil ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()

        }

        binding.btnFav.setOnClickListener{
            viewModelShop.allLiveDataAddSaved().observe(viewLifecycleOwner){
                if (it != null){
//                    Toast.makeText(requireContext(), "Berhasil ditambahkan ke favorit", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(), "Gagal ditambahkan ke favorit", Toast.LENGTH_SHORT).show()
                }
            }
            viewModelShop.addSaved("20|XZFCCFWyVVNwPowyzK6eduU5q95YcCTQkHcrdawr",id!!.toRequestBody("text/plain".toMediaType()))
            Toast.makeText(requireContext(), "Berhasil ditambahkan ke favorit", Toast.LENGTH_SHORT).show()

        }
    }

}