package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("data")
    var data: String?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("success")
    var success: Boolean?
)