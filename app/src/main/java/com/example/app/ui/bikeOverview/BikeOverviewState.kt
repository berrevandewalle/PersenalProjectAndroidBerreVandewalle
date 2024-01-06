package com.example.app.ui.bikeOverview

import androidx.work.WorkInfo
import com.example.app.model.Bike

data class BikeOverviewState(
   // val currentBikeList: List<Bike>,
    //val isAddingVisible: boolean = false,
    val newBikeId: Int = 10,
    val newBikeName: String = "",
    val newBikePrice: Double = 0.0,
    val newBikeImgSrc: String = "",
    val newBikeDescription: String = "",
    val scrollActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0,
    )

data class BikeListState(val bikeList: List<Bike> = listOf())

data class WorkerState(val workerInfo: WorkInfo? = null)

sealed interface BikeApiState {
    object Success : BikeApiState
    object Error : BikeApiState
    object Loading : BikeApiState
}
