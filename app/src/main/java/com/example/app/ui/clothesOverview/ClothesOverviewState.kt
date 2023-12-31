package com.example.app.ui.clothesOverview

import androidx.work.WorkInfo
import com.example.app.data.AppContainer
import com.example.app.data.BikesRepository
import com.example.app.model.Bike
import com.example.app.model.Clothes
import kotlinx.coroutines.flow.Flow

data class ClothesOverviewState (
    // val currentBikeList: List<Bike>,
    val isAddingVisible: Boolean = false,
    val newClothesId: Int = 0,
    val newClothesName: String = "",
    val newClothesPrice: Double = 0.0,
    val newClothesDescription: String = "",
    val newClothesImgSrc: String = "",
    val scrollActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0,
)

data class ClothesListState(val clothesList: List<Clothes> = listOf())
/*listOf(Bike(1, "Cervelo", 100.0, "https://www.lease-a-bike.be/cdn-cgi/image/format=auto,quality=70,width=750/storage/uploads/cervelo-s5-black.png"),
        Bike(2, "S-works", 200.0, "https://www.bikeshop.nl/sites/default/files/tarmac-sl7-sw-etap-carb-cmlnsilgrn_hero.jpg"),
        Bike(3, "Trek", 300.0, "https://www.lease-a-bike.nl/cdn-cgi/image/format=auto,quality=70,width=750/storage/uploads/97ea3f31-3681-4edb-b7b8-f05f0ae2aaec/modane-sl6.jpg")))
*/
data class WorkerState(val workerInfo: WorkInfo? = null)

sealed interface ClothesApiState {
    object Success : ClothesApiState
    object Error : ClothesApiState
    object Loading : ClothesApiState
}
