package com.app.mytea.view.basket

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mytea.data.datastore.SharedPref
import com.app.mytea.databinding.FragmentBasketBinding
import com.app.mytea.view.basket.adapter.BasketAdapter

class BasketFragment : Fragment() {
    lateinit var binding : FragmentBasketBinding
    private lateinit var viewModel : ViewModelBasket
    private lateinit var sharedPref: SharedPref
    private val fee = 4000
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModelBasket::class.java)
        sharedPref = SharedPref(requireContext())

        viewModel.allLiveDataBasket().observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                binding.listItemRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                val adapter = BasketAdapter(it)
                binding.listItemRecyclerView.adapter = adapter

                var totalQuantity = 0

                for (item in it) {
                    totalQuantity += item.qty ?: 0
                }

                binding.qty.text = "Total ($totalQuantity items)"
                binding.priceFee.text = "Rp. ${fee}"
            } else {
                binding.listItemRecyclerView.visibility = View.GONE
            }
        }



        viewModel.allLiveDataCheckPrice().observe(viewLifecycleOwner){
            if(it != null){
                binding.price.text = "Rp. ${it.notCheckoutTotal}"
                binding.priceTotal.text = "Rp. ${it.notCheckoutTotal?.minus(fee)}"
            }else{
                binding.priceTotal.visibility = View.GONE
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
            viewModel.getDataBasket(tokens)
            viewModel.checkPrice(tokens)

        }

        binding.btnPay.setOnClickListener{
            binding.btnPay.setOnClickListener {
                viewModel.allLiveDataBasket().value?.let { basketData ->
                    if (basketData.isNotEmpty()) {
                        val productList = mutableListOf<String>()

                        for (item in basketData) {
                            val product = item.product
                            val productName = product?.name ?: "Nama Produk Tidak Tersedia"
                            val quantity = item.qty ?: 0
                            productList.add("$productName ($quantity buah)")
                        }

                        val totalQuantity = basketData.sumBy { it.qty ?: 0 }
                        val total = viewModel.allLiveDataCheckPrice().value?.notCheckoutTotal ?: 0

                        // Kirim pesan WhatsApp
                        sendWhatsAppMessage(productList, totalQuantity, fee, total)
                    } else {
                        binding.listItemRecyclerView.visibility = View.GONE
                    }
                }
            }

        }
    }
    private fun sendWhatsAppMessage(productList: List<String>, totalQuantity: Int, fee: Int, total: Int) {
        val whatsappIntent = Intent(Intent.ACTION_SEND)
        whatsappIntent.type = "text/plain"

        val stringBuilder = StringBuilder()

        stringBuilder.append("Produk yang akan saya pesan:\n")

        for (i in productList.indices) {
            val product = productList[i]
            stringBuilder.append("${i + 1}. $product\n")
        }

        stringBuilder.append("\nTotal ($totalQuantity items)\n")
        stringBuilder.append("Fee Shipping: Rp. $fee\n")
        stringBuilder.append("Total: Rp. ${total - fee}")

        val message = stringBuilder.toString()

        whatsappIntent.putExtra(Intent.EXTRA_TEXT, message)

        whatsappIntent.`package` = "com.whatsapp"

        try {
            startActivity(whatsappIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "WhatsApp tidak terinstal.", Toast.LENGTH_SHORT).show()
        }
    }
}