package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class ProductXXX(
    @SerializedName("category_id")
    var categoryId: Int?,
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("price")
    var price: Int?,
    @SerializedName("qty")
    var qty: Int?,
    @SerializedName("updated_at")
    var updatedAt: String?
)