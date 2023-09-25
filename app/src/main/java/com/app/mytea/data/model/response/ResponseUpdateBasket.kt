package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseUpdateBasket(
    @SerializedName("data")
    var data: DataXXXXXXXXXXXXXXXXX?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("success")
    var success: Boolean?
)