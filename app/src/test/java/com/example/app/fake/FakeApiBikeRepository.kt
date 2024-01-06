package com.example.app.fake

import com.example.app.data.BikesRepository
import com.example.app.model.Bike
import com.example.app.network.asDomainObjects
import kotlinx.coroutines.flow.Flow

abstract class FakeApiBikeRepository : BikesRepository {
    override fun getBikes(): Flow<List<Bike>> {
        return FakeDataSource.tasks.asDomainObjects().
    }
}
