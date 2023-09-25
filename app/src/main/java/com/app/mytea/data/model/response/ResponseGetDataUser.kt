package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseGetDataUser(
    @SerializedName("data")
    var data: List<DataXXXXXXXXXXXXX>?,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
)