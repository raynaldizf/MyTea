package com.app.mytea.view.home.tea

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.mytea.data.model.response.DataXX
import com.app.mytea.data.model.response.DataXXXXXXXX
import com.app.mytea.data.model.response.DataXXXXXXXXX
import com.app.mytea.data.model.response.DataXXXXXXXXXX
import com.app.mytea.data.model.response.ResponseGetDetailPest
import com.app.mytea.data.model.response.ResponseGetDetailPesticides
import com.app.mytea.data.model.response.ResponseGetPest
import com.app.mytea.data.model.response.ResponseGetPesticides
import com.app.mytea.data.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeaViewModel : ViewModel() {
    // Pest
    private val pest = MutableLiveData<List<DataXX>?>()
    private val detailPest = MutableLiveData<DataXXXXXXXX?>()
    // Fertilizer
    private val fertilizer = MutableLiveData<List<DataXXXXXXXXX>?>()
    private val detailFertilizer = MutableLiveData<DataXXXXXXXXXX?>()


    // Pest
    fun allLiveDataPest(): MutableLiveData<List<DataXX>?> {
        return pest
    }
    fun allLiveDataDetailPest(): MutableLiveData<DataXXXXXXXX?> {
        return detailPest
    }

    // Fertilizer
    fun allLiveDataFertilizer(): MutableLiveData<List<DataXXXXXXXXX>?> {
        return fertilizer
    }
    fun allLiveDataDetailFertilizer(): MutableLiveData<DataXXXXXXXXXX?> {
        return detailFertilizer
    }


    // Pest
    fun getPest(token: String) {
        ApiClient.instance.pest("Bearer $token")
            .enqueue(object : Callback<ResponseGetPest> {
                override fun onResponse(
                    call: Call<ResponseGetPest>,
                    response: Response<ResponseGetPest>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()?.data}")

                    if (response.isSuccessful) {
                        pest.postValue(response.body()?.data)
                    } else {
                        pest.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseGetPest>, t: Throwable) {
                    pest.postValue(null)
                }

            })
    }

    fun getDetailPest(token: String, id: String) {
        ApiClient.instance.detailPest("Bearer $token", id)
            .enqueue(object : Callback<ResponseGetDetailPest> {
                override fun onResponse(
                    call: Call<ResponseGetDetailPest>,
                    response: Response<ResponseGetDetailPest>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()?.data}")

                    if (response.isSuccessful) {
                        detailPest.postValue(response.body()?.data)
                    } else {
                        detailPest.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseGetDetailPest>, t: Throwable) {
                    detailPest.postValue(null)
                }
            })
    }

    fun searchPests(query: String): List<DataXX>? {
        val allPests = pest.value
        if (allPests.isNullOrEmpty() || query.isBlank()) {
            return allPests
        }

        val filteredPests = mutableListOf<DataXX>()

        for (pest in allPests) {
            if (pest.name!!.contains(query, ignoreCase = true)) {
                filteredPests.add(pest)
            }
        }

        return filteredPests
    }

    // Fertilizer
    fun getFertilizer(token: String) {
        ApiClient.instance.pesticides("Bearer $token")
            .enqueue(object : Callback<ResponseGetPesticides> {
                override fun onResponse(
                    call: Call<ResponseGetPesticides>,
                    response: Response<ResponseGetPesticides>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()?.data}")

                    if (response.isSuccessful) {
                        fertilizer.postValue(response.body()?.data)
                    } else {
                        fertilizer.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseGetPesticides>, t: Throwable) {
                    fertilizer.postValue(null)
                }

            })
    }

    fun getDetailFertilizer(token: String, id: String) {
        ApiClient.instance.detailPesticides("Bearer $token", id)
            .enqueue(object : Callback<ResponseGetDetailPesticides> {
                override fun onResponse(
                    call: Call<ResponseGetDetailPesticides>,
                    response: Response<ResponseGetDetailPesticides>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()?.data}")

                    if (response.isSuccessful) {
                        detailFertilizer.postValue(response.body()?.data)
                    } else {
                        detailFertilizer.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseGetDetailPesticides>, t: Throwable) {
                    detailFertilizer.postValue(null)
                }
            })
    }

    fun searchPesticides(query: String): List<DataXXXXXXXXX>? {
        val allPesticides = fertilizer.value
        if (allPesticides.isNullOrEmpty() || query.isBlank()) {
            return allPesticides
        }

        val filteredPesticides = mutableListOf<DataXXXXXXXXX>()

        for (pesticides in allPesticides) {
            if (pesticides.name!!.contains(query, ignoreCase = true)) {
                filteredPesticides.add(pesticides)
            }
        }

        return filteredPesticides

    }
}