package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class DataXXXXXXXXXXX(
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("products")
    var products: List<ProductX>?,
    @SerializedName("updated_at")
    var updatedAt: String?
)