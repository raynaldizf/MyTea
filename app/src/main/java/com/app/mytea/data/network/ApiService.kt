package com.app.mytea.data.network

import com.app.mytea.data.model.request.LoginRequest
import com.app.mytea.data.model.request.Profile
import com.app.mytea.data.model.request.RegisterRequest
import com.app.mytea.data.model.response.ResponseAddCart
import com.app.mytea.data.model.response.ResponseDeleteSaved
import com.app.mytea.data.model.response.ResponseGetBasket
import com.app.mytea.data.model.response.ResponseGetCategory
import com.app.mytea.data.model.response.ResponseGetDataUser
import com.app.mytea.data.model.response.ResponseGetDetailCategory
import com.app.mytea.data.model.response.ResponseGetDetailDataUser
import com.app.mytea.data.model.response.ResponseGetDetailExpert
import com.app.mytea.data.model.response.ResponseGetDetailPest
import com.app.mytea.data.model.response.ResponseGetDetailPesticides
import com.app.mytea.data.model.response.ResponseGetDetailProduct
import com.app.mytea.data.model.response.ResponseGetExpert
import com.app.mytea.data.model.response.ResponseGetPest
import com.app.mytea.data.model.response.ResponseGetPesticides
import com.app.mytea.data.model.response.ResponseGetProduct
import com.app.mytea.data.model.response.ResponseGetSavedData
import com.app.mytea.data.model.response.ResponseLogin
import com.app.mytea.data.model.response.ResponseRegister
import com.app.mytea.data.model.response.ResponseSaved
import com.app.mytea.data.model.response.ResponseUpdateBasket
import com.app.mytea.data.model.response.ResponseUpdateDataProfile
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("login")
    fun login(@Body data : LoginRequest) : Call<ResponseLogin>

    @POST("register")
    fun register(@Body data : RegisterRequest) : Call<ResponseRegister>

    @GET("products")
    fun product(@Header("Authorization") token: String): Call<ResponseGetProduct>

    @GET("products/{id}")
    fun detailProduct(@Header("Authorization") token: String, @Path("id") id: String): Call<ResponseGetDetailProduct>

    @POST("carts")
    @Multipart
    fun addCart(@Header("Authorization") token: String,
                @Part("product_id") product_id: RequestBody,
                @Part("qty") qty: RequestBody
    ): Call<ResponseAddCart>

//    @PUT("carts/{id}")
//    @Multipart
//    fun updateCart(@Header("Authorization") token: String,
//                @Path("id") id: String,
//                @Part("qty") qty: RequestBody
//    ): Call<ResponseUpdateBasket>
//
//    @DELETE("carts/{id}")
//    fun deleteCart(@Header("Authorization") token: String, @Path("id") id: String): Call<>


    @POST("saved")
    @Multipart
    fun addSaved(@Header("Authorization") token: String,
                @Part("product_id") product_id: RequestBody
    ): Call<ResponseSaved>

    @GET("saved")
    fun getSaved(@Header("Authorization") token: String): Call<ResponseGetSavedData>

    @DELETE("saved/{id}")
    fun deleteSaved(@Header("Authorization") token: String, @Path("id") id: String): Call<ResponseDeleteSaved>

    @GET("pests")
    fun pest(@Header("Authorization") token: String): Call<ResponseGetPest>

    @GET("pests/{id}")
    fun detailPest(@Header("Authorization") token: String, @Path("id") id: String): Call<ResponseGetDetailPest>

    @GET("pesticides")
    fun pesticides(@Header("Authorization") token: String): Call<ResponseGetPesticides>

    @GET("pesticides/{id}")
    fun detailPesticides(@Header("Authorization") token: String, @Path("id") id: String): Call<ResponseGetDetailPesticides>

    @GET("users")
    fun user(@Header("Authorization") token: String): Call<ResponseGetDataUser>

    @GET("users/{id}")
    fun detailUser(@Header("Authorization") token: String, @Path("id") id: String): Call<ResponseGetDetailDataUser>

    @PUT("users/{id}")
    fun updateProfile(@Header("Authorization") token: String,
                      @Path("id") id: String,
                      @Body data : Profile
    ) : Call<ResponseUpdateDataProfile>

    @GET("experts")
    fun expert(@Header("Authorization") token: String): Call<ResponseGetExpert>

    @GET("experts/{id}")
    fun detailExpert(@Header("Authorization") token: String, @Path("id") id: String): Call<ResponseGetDetailExpert>

    @GET("categories")
    fun categories(@Header("Authorization") token: String): Call<ResponseGetCategory>

    @GET("categories/{id}")
    fun categoriesDetail(@Header("Authorization") token: String, @Path("id") id: String): Call<ResponseGetDetailCategory>

    @GET("carts")
    fun cart(@Header("Authorization") token: String): Call<ResponseGetBasket>


}