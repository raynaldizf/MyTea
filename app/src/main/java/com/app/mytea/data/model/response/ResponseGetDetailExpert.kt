package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseGetDetailExpert(
    @SerializedName("data")
    var data: DataXXXXXXXXXXXXXXXXXXX?,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
)