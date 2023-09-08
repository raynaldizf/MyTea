package com.app.mytea.view.authentication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.mytea.data.model.request.LoginRequest
import com.app.mytea.data.model.request.RegisterRequest
import com.app.mytea.data.model.response.ResponseLogin
import com.app.mytea.data.model.response.ResponseRegister
import com.app.mytea.data.network.ApiClient
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthenticationViewModel : ViewModel(){
    private val login = MutableLiveData<ResponseLogin?>()
    private val register = MutableLiveData<ResponseRegister?>()

    fun login() : MutableLiveData<ResponseLogin?>{
        return login
    }

    fun register() : MutableLiveData<ResponseRegister?>{
        return register
    }

    fun loginUser(request : LoginRequest){
        ApiClient.instance.login(request)
            .enqueue(object : Callback<ResponseLogin>{
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    Log.d("Is Sucess", "onResponse: ${response.isSuccessful}")
                    Log.d("Message", "onResponse: ${response.message()}")
                    Log.d("Error Body", "onResponse: ${response.errorBody()?.string()}")
                    if (response.isSuccessful){
                        login.postValue(response.body())
                    }else{
                        login.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    login.postValue(null)
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            })
    }

    fun registerUser(request: RegisterRequest){
        ApiClient.instance.register(request)
            .enqueue(object : Callback<ResponseRegister>{
                override fun onResponse(
                    call: Call<ResponseRegister>,
                    response: Response<ResponseRegister>
                ) {
                    Log.d("Is Sucess", "onResponse: ${response.isSuccessful}")
                    Log.d("Message", "onResponse: ${response.message()}")
                    Log.d("Error Body", "onResponse: ${response.errorBody()?.string()}")
                    if (response.isSuccessful){
                        register.postValue(response.body())
                    }else{
                        register.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                    register.postValue(null)
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            })
    }
}