package com.example.app.ui.detail

import androidx.work.WorkInfo
import com.example.app.model.Bike

data class DetailBikeState(
    val currentBike: List<Bike>,
    //val isAddingVisible: boolean = false,
    val BikeId: Int = 0,
    val BikeName: String = "",
    val BikePrice: Double = 0.0,
    val BikeImgSrc: String = "",
    val scrollActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0,
)

data class DetailBikeListState(val bikeList: List<Bike> = listOf())

data class WorkerState(val workerInfo: WorkInfo? = null)

sealed interface BikeApiState {
    object Success : BikeApiState
    object Error : BikeApiState
    object Loading : BikeApiState
}
