package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseGetDetailProduct(
    @SerializedName("data")
    var data: DataXXXX?,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
)