package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class DataXXXXXX(
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("product_id")
    var productId: Int?,
    @SerializedName("updated_at")
    var updatedAt: String?,
    @SerializedName("user_id")
    var userId: Int?
)