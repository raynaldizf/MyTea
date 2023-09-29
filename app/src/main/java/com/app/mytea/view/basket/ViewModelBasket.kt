package com.app.mytea.view.basket

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.mytea.data.model.response.DataXXXXXXXXXXXXXXXX
import com.app.mytea.data.model.response.NotCheckout
import com.app.mytea.data.model.response.ResponseGetBasket
import com.app.mytea.data.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelBasket : ViewModel() {
    private val basket = MutableLiveData<List<NotCheckout>?>()

    private val checkPrice = MutableLiveData<DataXXXXXXXXXXXXXXXX?>()

    fun allLiveDataBasket(): MutableLiveData<List<NotCheckout>?> {
        return basket
    }

    fun allLiveDataCheckPrice(): MutableLiveData<DataXXXXXXXXXXXXXXXX?> {
        return checkPrice
    }

    fun getDataBasket(token: String) {
        ApiClient.instance.cart("Bearer $token")
            .enqueue(object : Callback<ResponseGetBasket> {
                override fun onResponse(
                    call: Call<ResponseGetBasket>,
                    response: Response<ResponseGetBasket>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()?.data}")

                    if (response.isSuccessful) {
                        basket.postValue(response.body()?.data?.notCheckout)
                    } else {
                        basket.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseGetBasket>, t: Throwable) {
                    basket.postValue(null)
                }

            })
    }

    fun checkPrice(token: String) {
        ApiClient.instance.cart("Bearer $token")
            .enqueue(object : Callback<ResponseGetBasket> {
                override fun onResponse(
                    call: Call<ResponseGetBasket>,
                    response: Response<ResponseGetBasket>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()?.data}")

                    if (response.isSuccessful) {
                        checkPrice.postValue(response.body()?.data)
                    } else {
                        checkPrice.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseGetBasket>, t: Throwable) {
                    checkPrice.postValue(null)
                }

            })
    }
}