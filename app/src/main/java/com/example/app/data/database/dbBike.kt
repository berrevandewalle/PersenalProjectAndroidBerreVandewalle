package com.example.app.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.app.model.Bike
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "bikes")
data class dbBike(
    @PrimaryKey
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("price")
    val price: Double = 0.0,
    @SerializedName("img_src")
    val imgSrc: String = "",
    @SerializedName("description")
    val description: String,

)

fun dbBike.asDomainBike(): Bike {
    return Bike(
        this.id,
        this.name,
        this.price,
        this.imgSrc,
        this.description
    )
}

fun Bike.asDbBike(): dbBike {
    return dbBike(
        id = this.id,
        name = this.name,
        price = this.price,
        imgSrc = this.imgSrc,
        description = this.description
    )
}

fun List<dbBike>.asDomainBikes(): List<Bike> {
    var bikeList = this.map {
        Bike(it.id, it.name, it.price, it.imgSrc, it.description)
    }
    return bikeList
}
