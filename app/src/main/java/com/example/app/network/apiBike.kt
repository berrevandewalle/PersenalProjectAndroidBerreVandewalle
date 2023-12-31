package com.example.app.network

import com.example.app.model.Bike
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ApiBike(
    val id: Int,
    val name: String,
    val price: Double,
    val imgSrc: String,
)

// extension function for an ApiTask List to convert is to a Domain Task List
fun Flow<List<ApiBike>>.asDomainObjects(): Flow<List<Bike>> {
    return map {
        it.asDomainObjects()
    }
}

fun List<ApiBike>.asDomainObjects(): List<Bike> {
    var domainList = this.map {
        Bike(it.id, it.name, it.price, it.imgSrc)
    }
    return domainList
}
