package com.app.mytea.view.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.mytea.data.model.request.Profile
import com.app.mytea.data.model.response.DataXXXXXXXXXXXXX
import com.app.mytea.data.model.response.DataXXXXXXXXXXXXXXX
import com.app.mytea.data.model.response.ResponseGetDataUser
import com.app.mytea.data.model.response.ResponseGetDetailDataUser
import com.app.mytea.data.model.response.ResponseUpdateDataProfile
import com.app.mytea.data.network.ApiClient
import com.google.gson.GsonBuilder
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {
    private val user = MutableLiveData<List<DataXXXXXXXXXXXXX>?>()
    private val detailUser = MutableLiveData<ResponseGetDetailDataUser?>()
    private val updateDataUser = MutableLiveData<ResponseUpdateDataProfile?>()


    fun allLiveDataUser(): MutableLiveData<List<DataXXXXXXXXXXXXX>?> {
        return user
    }

    fun allLiveDataDetailUser(): MutableLiveData<ResponseGetDetailDataUser?> {
        return detailUser
    }

    fun allLiveDataUpdateDataUser(): MutableLiveData<ResponseUpdateDataProfile?> {
        return updateDataUser
    }

    fun getDataUser(token: String) {
        ApiClient.instance.user("Bearer $token").enqueue(object : Callback<ResponseGetDataUser> {
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

    fun getDetailDataUser(token: String, id: String) {
        ApiClient.instance.detailUser("Bearer $token", id)
            .enqueue(object : Callback<ResponseGetDetailDataUser> {
                override fun onResponse(
                    call: Call<ResponseGetDetailDataUser>,
                    response: Response<ResponseGetDetailDataUser>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    if (response.isSuccessful) {
                        detailUser.postValue(response.body())
                    } else {
                        detailUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseGetDetailDataUser>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                    detailUser.postValue(null)
                }

            })
    }

    fun updateUserProfile(token : String,id : String, data : Profile){
        ApiClient.instance.updateProfile("Bearer $token",id, data)
            .enqueue(object : Callback<ResponseUpdateDataProfile>{
                override fun onResponse(
                    call: Call<ResponseUpdateDataProfile>,
                    response: Response<ResponseUpdateDataProfile>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    if (response.isSuccessful) {
                        updateDataUser.postValue(response.body())
                    } else {
                        Log.d("TAG", "onResponse: ${response.errorBody()?.string()}")
                        updateDataUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseUpdateDataProfile>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                    updateDataUser.postValue(null)
                }

            })
    }
}