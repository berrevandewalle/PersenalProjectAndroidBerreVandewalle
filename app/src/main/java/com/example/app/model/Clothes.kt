package com.example.app.model

import com.google.gson.annotations.SerializedName

data class Clothes (
        // var description: String? = null,
        @SerializedName("id")
        var id: Int,

        @SerializedName("name")
        var name: String,

        @SerializedName("price")
        var price: Double,

        @SerializedName("description")
        val description: String,

        @SerializedName("img_src")
        val imgSrc: String,
)
