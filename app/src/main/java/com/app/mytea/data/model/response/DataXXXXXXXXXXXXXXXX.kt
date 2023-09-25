package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class DataXXXXXXXXXXXXXXXX(
    @SerializedName("is_checkout")
    var isCheckout: List<Any>?,
    @SerializedName("not_checkout")
    var notCheckout: List<NotCheckout>?,
    @SerializedName("not_checkout_total")
    var notCheckoutTotal: Int?
)