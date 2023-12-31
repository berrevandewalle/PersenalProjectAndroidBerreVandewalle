package com.example.app.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.app.model.Bike
import com.google.gson.annotations.SerializedName

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

)

fun dbBike.asDomainBike(): Bike {
    return Bike(
        this.id,
        this.name,
        this.price,
        this.imgSrc
    )
}

fun Bike.asDbBike(): dbBike {
    return dbBike(
        id = this.id,
        name = this.name,
        price = this.price,
        imgSrc = this.imgSrc
    )
}

fun List<dbBike>.asDomainBikes(): List<Bike> {
    var bikeList = this.map {
        Bike(it.id, it.name, it.price, it.imgSrc)
    }
    return bikeList
}
