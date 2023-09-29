package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseGetBasket(
    @SerializedName("data")
    var data: DataXXXXXXXXXXXXXXXX?,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
)