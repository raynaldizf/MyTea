package com.app.mytea.view.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.mytea.data.model.response.DataXXXXXXXXXXXXX
import com.app.mytea.data.model.response.ResponseGetDataUser
import com.app.mytea.data.network.ApiClient
import retrofit2.*

class HomeViewModel : ViewModel() {
    private val user = MutableLiveData<List<DataXXXXXXXXXXXXX>?>()

    fun allLiveDataUser(): MutableLiveData<List<DataXXXXXXXXXXXXX>?>{
        return user
    }

    fun getDataUser(token : String){
        ApiClient.instance.user("Bearer $token").enqueue(object : Callback<ResponseGetDataUser>{
            override fun onResponse(
                call: Call<ResponseGetDataUser>,
                response: Response<ResponseGetDataUser>
            ) {
                Log.d("TAG", "onResponse: ${response.body()}")
                if (response.isSuccessful) {
                    user.postValue(response.body()?.data)
                } else {
                    user.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseGetDataUser>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
                user.postValue(null)
            }

        })
    }
}