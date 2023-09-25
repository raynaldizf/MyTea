package com.app.mytea.data.model.response


import com.google.gson.annotations.SerializedName

data class DataXXXXXXXX(
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("detail")
    var detail: String?,
    @SerializedName("how_to_controll")
    var howToControll: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("updated_at")
    var updatedAt: String?
)