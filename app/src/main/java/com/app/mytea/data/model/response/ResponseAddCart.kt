package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseAddCart(
    @SerializedName("data")
    var data: DataXXXXX?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("success")
    var success: Boolean?
)