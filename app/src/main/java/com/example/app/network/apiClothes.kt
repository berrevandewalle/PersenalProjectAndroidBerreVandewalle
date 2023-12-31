package com.example.app.network

import com.example.app.model.Bike
import com.example.app.model.Clothes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ApiClothes(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val imgSrc: String,
)

// extension function for an ApiTask List to convert is to a Domain Task List
fun Flow<List<ApiClothes>>.asDomainObjects(): Flow<List<Clothes>> {
    return map {
        it.asDomainObjects()
    }
}

fun List<ApiClothes>.asDomainObjects(): List<Clothes> {
    var domainList = this.map {
        Clothes(it.id, it.name, it.price, it.description, it.imgSrc)
    }
    return domainList
}
