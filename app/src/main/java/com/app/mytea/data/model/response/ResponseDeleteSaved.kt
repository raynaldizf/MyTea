package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ResponseDeleteSaved(
    @SerializedName("data")
    var data: Any?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("success")
    var success: Boolean?
)