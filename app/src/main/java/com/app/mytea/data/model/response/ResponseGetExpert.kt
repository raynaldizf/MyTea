package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseGetExpert(
    @SerializedName("data")
    var data: List<DataXXX>?,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
)