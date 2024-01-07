package com.example.app.network

import com.example.app.model.Bike
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

/**
 * Api bike
 *
 * @property id
 * @property name
 * @property price
 * @property imgSrc
 * @property description
 * @constructor Create empty Api bike
 */
@Serializable
data class ApiBike(
    val id: Int,
    val name: String,
    val price: Double,
    val imgSrc: String,
    val description: String,
)

/**
 * As domain objects
 *
 * @return
 */// extension function for an ApiTask List to convert is to a Domain Task List
fun Flow<List<ApiBike>>.asDomainObjects(): Flow<List<Bike>> {
    return map {
        it.asDomainObjects()
    }
}

/**
 * As domain objects
 *
 * @return
 */
fun List<ApiBike>.asDomainObjects(): List<Bike> {
    var domainList = this.map {
        Bike(it.id, it.name, it.price, it.imgSrc, it.description)
    }
    return domainList
}
