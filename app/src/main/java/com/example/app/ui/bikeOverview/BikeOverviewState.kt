package com.example.app.ui.bikeOverview

import androidx.work.WorkInfo
import com.example.app.model.Bike

data class BikeOverviewState(
    val currentBikeList: List<Bike>,
    //val isAddingVisible: boolean = false,
    val newBikeId: Int = 0,
    val newBikeName: String = "",
    val newBikePrice: Double = 0.0,
    val newBikeImgSrc: String = "",
    val scrollActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0,
    )

data class BikeListState(val bikeList: List<Bike> = listOf())
/*listOf(Bike(1, "Cervelo", 100.0, "https://www.lease-a-bike.be/cdn-cgi/image/format=auto,quality=70,width=750/storage/uploads/cervelo-s5-black.png"),
        Bike(2, "S-works", 200.0, "https://www.bikeshop.nl/sites/default/files/tarmac-sl7-sw-etap-carb-cmlnsilgrn_hero.jpg"),
        Bike(3, "Trek", 300.0, "https://www.lease-a-bike.nl/cdn-cgi/image/format=auto,quality=70,width=750/storage/uploads/97ea3f31-3681-4edb-b7b8-f05f0ae2aaec/modane-sl6.jpg")))
*/
data class WorkerState(val workerInfo: WorkInfo? = null)

sealed interface BikeApiState {
    object Success : BikeApiState
    object Error : BikeApiState
    object Loading : BikeApiState
}
