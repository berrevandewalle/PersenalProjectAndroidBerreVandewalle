package com.example.app.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app.data.database.BikeDao
import com.example.app.data.database.dbBike
import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import com.example.app.BikeApplication
import com.example.app.data.BikesRepository
import com.example.app.model.Bike
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BikeDetailsViewModel(private val bikesRepository: BikesRepository): ViewModel() {
    // Get full bus schedule from Room DB
    fun getBikes(): Flow<List<Bike>> = bikesRepository.getBikes()
    // Get bus schedule based on the stop name from Room DB
    fun getBike(name: String): Flow<Bike?> =
        bikesRepository.getBike(name)

    suspend fun deleteBike(bike: Bike?): Job =
        viewModelScope.launch {
            bike?.let {
                withContext(Dispatchers.IO) {
                    Log.d("delete", "delete bike in ${bike.id}")
                    bikesRepository.deleteBike(it)
                }
            }
        }

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BikeApplication)
                BikeDetailsViewModel(application.container.bikesRepository)
            }
        }
    }
}