package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseGetDetailCategory(
    @SerializedName("data")
    var data: DataXXXXXXXXXXXX?,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
)