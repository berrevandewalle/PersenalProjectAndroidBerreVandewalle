package com.example.app.fake

import com.example.app.data.BikesRepository
import com.example.app.model.Bike
import com.example.app.network.asDomainObjects
import kotlinx.coroutines.flow.Flow

/**
 * Fake api bike repository
 *
 * @constructor Create empty Fake api bike repository
 */
abstract class FakeApiBikeRepository : BikesRepository {
    override fun getBikes(): Flow<List<Bike>> {
        return FakeDataSource.getBikesFlow()
    }
}
