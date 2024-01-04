package com.example.app.model

import com.google.gson.annotations.SerializedName

data class Bike(
   // var description: String? = null,
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("price")
    var price: Double,

    @SerializedName("img_src")
    val imgSrc: String,

    @SerializedName("description")
    val description: String,
)
