package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseGetProduct(
    @SerializedName("data")
    var data: List<DataX>?,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
)