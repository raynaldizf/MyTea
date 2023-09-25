package com.app.mytea.view.home.consultation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.mytea.data.model.response.DataXXX
import com.app.mytea.data.model.response.ResponseGetDetailExpert
import com.app.mytea.data.model.response.ResponseGetExpert
import com.app.mytea.data.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelConsultant : ViewModel() {
    private val expert = MutableLiveData<List<DataXXX>?>()
    private val expertDetail = MutableLiveData<ResponseGetDetailExpert?>()

    fun allLiveDataExpert(): MutableLiveData<List<DataXXX>?> {
        return expert
    }

    fun allLiveDataExpertDetail(): MutableLiveData<ResponseGetDetailExpert?> {
        return expertDetail
    }

    fun getExpert(token : String){
        ApiClient.instance.expert("Bearer $token").enqueue(object : Callback<ResponseGetExpert> {
            override fun onResponse(
                call: Call<ResponseGetExpert>,
                response: Response<ResponseGetExpert>
            ) {
                Log.d("TAG", "onResponse: ${response.body()}")
                if (response.isSuccessful) {
                    expert.postValue(response.body()?.data)
                } else {
                    expert.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseGetExpert>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
                expert.postValue(null)
            }

        })
    }

    fun getDetailExpert(token : String, id : String){
        ApiClient.instance.detailExpert("Bearer $token", id).enqueue(object : Callback<ResponseGetDetailExpert> {
            override fun onResponse(
                call: Call<ResponseGetDetailExpert>,
                response: Response<ResponseGetDetailExpert>
            ) {
                Log.d("TAG", "onResponse: ${response.body()}")
                if (response.isSuccessful) {
                    expertDetail.postValue(response.body())
                } else {
                    expertDetail.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseGetDetailExpert>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
                expertDetail.postValue(null)
            }

        })
    }
}