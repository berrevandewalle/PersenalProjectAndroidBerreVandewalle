package com.example.app.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.app.model.Bike
import com.example.app.model.Clothes
import com.google.gson.annotations.SerializedName

@Entity(tableName = "clothes")
data class dbClothes(
    @PrimaryKey
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("price")
    val price: Double = 0.0,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("img_src")
    val imgSrc: String = "",
)

fun dbClothes.asDomainClothes(): Clothes {
    return Clothes(
        this.id,
        this.name,
        this.price,
        this.description,
        this.imgSrc
    )
}

fun Clothes.asDbClothes(): dbClothes {
    return dbClothes(
        id = this.id,
        name = this.name,
        price = this.price,
        description = this.description,
        imgSrc = this.imgSrc
    )
}

fun List<dbClothes>.asDomainClothes(): List<Clothes> {
    var clothesList = this.map {
        Clothes(it.id, it.name, it.price,it.description, it.imgSrc)
    }
    return clothesList
}
