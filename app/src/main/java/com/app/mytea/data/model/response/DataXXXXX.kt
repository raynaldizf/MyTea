package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class DataXXXXX(
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("is_checkout")
    var isCheckout: Int?,
    @SerializedName("product_id")
    var productId: Int?,
    @SerializedName("qty")
    var qty: Int?,
    @SerializedName("updated_at")
    var updatedAt: String?,
    @SerializedName("user_id")
    var userId: Int?
)