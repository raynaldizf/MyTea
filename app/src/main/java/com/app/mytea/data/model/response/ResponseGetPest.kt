package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseGetPest(
    @SerializedName("data")
    var data: List<DataXX>?,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
)