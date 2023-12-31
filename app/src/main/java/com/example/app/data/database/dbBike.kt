package com.example.app.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.app.model.Bike
import com.google.gson.annotations.SerializedName

/**
 * Db bike
 *
 * @property id
 * @property name
 * @property price
 * @property imgSrc
 * @property description
 * @constructor Create empty Db bike
 */
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

/**
 * As domain bike
 *
 * @return
 */
fun dbBike?.asDomainBike(): Bike {
    return Bike(
        id = this?.id ?: 0,
        name = this?.name.orEmpty(),
        price = this?.price ?: 0.0,
        imgSrc = this?.imgSrc.orEmpty(),
        description = this?.description.orEmpty()
    )
}

/**
 * As db bike
 *
 * @return
 */
fun Bike.asDbBike(): dbBike {
    return dbBike(
        id = this.id,
        name = this.name,
        price = this.price,
        imgSrc = this.imgSrc,
        description = this.description
    )
}

/**
 * As domain bikes
 *
 * @return
 */
fun List<dbBike>.asDomainBikes(): List<Bike> {
    var bikeList = this.map {
        Bike(it.id, it.name, it.price, it.imgSrc, it.description)
    }
    return bikeList
}
