package com.example.app.ui.detail

import androidx.work.WorkInfo
import com.example.app.model.Bike

/**
 * Detail bike state
 *
 * @property currentBike
 * @property BikeId
 * @property BikeName
 * @property BikePrice
 * @property BikeImgSrc
 * @property scrollActionIdx
 * @property scrollToItemIndex
 * @constructor Create empty Detail bike state
 */
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

/**
 * Detail bike list state
 *
 * @property bikeList
 * @constructor Create empty Detail bike list state
 */
data class DetailBikeListState(val bikeList: List<Bike> = listOf())

/**
 * Worker state
 *
 * @property workerInfo
 * @constructor Create empty Worker state
 */
data class WorkerState(val workerInfo: WorkInfo? = null)

/**
 * Bike api state
 *
 * @constructor Create empty Bike api state
 */
sealed interface BikeApiState {
    object Success : BikeApiState
    object Error : BikeApiState
    object Loading : BikeApiState
}
