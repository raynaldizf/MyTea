package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseGetDetailPest(
    @SerializedName("data")
    var data: DataXXXXXXXX?,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
)