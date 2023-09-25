package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseGetSavedData(
    @SerializedName("data")
    var data: List<DataXXXXXXX>?,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
)