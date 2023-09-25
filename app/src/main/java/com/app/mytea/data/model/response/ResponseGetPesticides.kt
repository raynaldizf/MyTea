package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseGetPesticides(
    @SerializedName("data")
    var data: List<DataXXXXXXXXX>?,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
)