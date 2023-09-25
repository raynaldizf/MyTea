package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseGetCategory(
    @SerializedName("data")
    var data: List<DataXXXXXXXXXXX>?,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
)