package com.app.mytea.data.network

import com.app.mytea.data.model.request.LoginRequest
import com.app.mytea.data.model.request.RegisterRequest
import com.app.mytea.data.model.response.ResponseLogin
import com.app.mytea.data.model.response.ResponseRegister
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("login")
    fun login(@Body data : LoginRequest) : Call<ResponseLogin>

    @POST("register")
    fun register(@Body data : RegisterRequest) : Call<ResponseRegister>

}