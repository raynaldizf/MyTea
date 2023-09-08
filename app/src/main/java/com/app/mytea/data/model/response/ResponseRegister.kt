package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseRegister(
    @SerializedName("data")
    var data: Data?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("success")
    var success: Boolean?
)