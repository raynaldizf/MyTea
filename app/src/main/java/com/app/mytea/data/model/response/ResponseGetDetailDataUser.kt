package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseGetDetailDataUser(
    @SerializedName("data")
    var data: DataXXXXXXXXXXXXXX?,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
)