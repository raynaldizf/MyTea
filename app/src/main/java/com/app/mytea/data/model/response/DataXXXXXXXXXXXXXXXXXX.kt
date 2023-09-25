package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class DataXXXXXXXXXXXXXXXXXX(
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("education")
    var education: String?,
    @SerializedName("experience")
    var experience: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("updated_at")
    var updatedAt: String?
)