package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseSaved(
    @SerializedName("data")
    var data: DataXXXXXX?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("success")
    var success: Boolean?
)