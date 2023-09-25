package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class CategoryX(
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("updated_at")
    var updatedAt: String?
)