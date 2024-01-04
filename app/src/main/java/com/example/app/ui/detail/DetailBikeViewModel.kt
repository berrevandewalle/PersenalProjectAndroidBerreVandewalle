package com.example.app.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app.data.database.BikeDao
import com.example.app.data.database.dbBike
import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.example.app.BikeApplication
import com.example.app.data.BikesRepository
import com.example.app.model.Bike

class BikeDetailsViewModel(private val bikesRepository: BikesRepository): ViewModel() {
    // Get full bus schedule from Room DB
    fun getBikes(): Flow<List<Bike>> = bikesRepository.getBikes()
    // Get bus schedule based on the stop name from Room DB
    fun getBike(name: String): Flow<Bike?> =
        bikesRepository.getBike(name)

    suspend fun deleteBike(bike: Bike?): Unit? =
        bike?.let { bikesRepository.deleteBike(it) }

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BikeApplication)
                BikeDetailsViewModel(application.container.bikesRepository)
            }
        }
    }
}