package com.example.app.ui.bikeOverview

import androidx.work.WorkInfo
import com.example.app.model.Bike

/**
 * Bike overview state
 *
 * @property newBikeId
 * @property newBikeName
 * @property newBikePrice
 * @property newBikeImgSrc
 * @property newBikeDescription
 * @property scrollActionIdx
 * @property scrollToItemIndex
 * @constructor Create empty Bike overview state
 */
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

/**
 * Bike list state
 *
 * @property bikeList
 * @constructor Create empty Bike list state
 */
data class BikeListState(val bikeList: List<Bike> = listOf())

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
