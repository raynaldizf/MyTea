package com.app.mytea.data.model.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("email")
    var email: String?,
    @SerializedName("username")
    var username: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("password")
    var password: String?,
    @SerializedName("password_confirm")
    var passwordConfirm: String?
)
