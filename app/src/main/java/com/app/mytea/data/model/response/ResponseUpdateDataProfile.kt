package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseUpdateDataProfile(
    @SerializedName("data")
    var data: DataXXXXXXXXXXXXXXX?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("success")
    var success: Boolean?
)