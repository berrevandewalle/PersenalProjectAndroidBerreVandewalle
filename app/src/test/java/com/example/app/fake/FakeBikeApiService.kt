package com.example.app.fake

import com.example.app.network.ApiBike
import com.example.app.network.BikeApiService
import com.example.app.fake.FakeDataSource

abstract class FakeBikeApiService : BikeApiService {
    override suspend fun getBikes(): List<ApiBike> {
        return FakeDataSource.tasks
    }
}
