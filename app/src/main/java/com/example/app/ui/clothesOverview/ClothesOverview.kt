package com.example.app.ui.clothesOverview

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.model.Bike
import com.example.app.model.Clothes
import com.example.app.ui.bikeOverview.BikeApiState
import com.example.app.ui.bikeOverview.BikeListState
import com.example.app.ui.bikeOverview.BikeOverviewState
import com.example.app.ui.bikeOverview.BikeOverviewViewModel
import com.example.app.ui.components.bike.BikeItem
import com.example.app.ui.components.bike.CreateBike
import com.example.app.ui.components.clothes.ClothesItem
import com.example.app.ui.components.clothes.CreateClothes
import kotlinx.coroutines.launch


@Composable
fun ClothesOverview(
    modifier: Modifier = Modifier,
    clothesOverviewViewModel: ClothesOverviewViewModel = viewModel(factory = ClothesOverviewViewModel.Factory),
    isAddingVisisble: Boolean = false,
    makeInvisible: () -> Unit = {},
){
    Log.i("vm inspection", "BikeOverview composition")
    val clothesOverviewState by clothesOverviewViewModel.uiState.collectAsState()
    val clothesListState by clothesOverviewViewModel.uiListState.collectAsState()

    // use the ApiState
    val clothesApiState = clothesOverviewViewModel.clothesApiState

    //use the workerstate
    val workerState by clothesOverviewViewModel.wifiWorkerState.collectAsState()
    Column {
        when(workerState.workerInfo?.state){
            null -> Text("state unknown")
            else -> Text(workerState.workerInfo?.state!!.name)
        }

        Box(modifier = modifier) {
            when (clothesApiState) {
                is ClothesApiState.Loading -> Text("Loading...")
                is ClothesApiState.Error -> Text("Couldn't load...")
                is ClothesApiState.Success -> ClothesListComponent(clothesOverviewState = clothesOverviewState, clothesListState = clothesListState)
            }

            if (isAddingVisisble) {
                CreateClothes(
                    clothesName = clothesOverviewState.newClothesName,
                    clothesPrice = clothesOverviewState.newClothesPrice,
                    clothesImgSrc = clothesOverviewState.newClothesImgSrc,
                    clothesDescription = clothesOverviewState.newClothesDescription,
                    onClothesNameChanged = { clothesOverviewViewModel.setNewClothesName(it) },
                    onClothesPriceChanged = { clothesOverviewViewModel.setNewClothesPrice(it) },
                    onClothesDescriptionChanged = { clothesOverviewViewModel.setNewClothesDescription(it)},
                    onClothesImgSrcChanged = { clothesOverviewViewModel.setNewClothesImgSrc(it) },
                    onClothesSaved = {
                        clothesOverviewViewModel.addClothes()
                        makeInvisible()
                    },
                    onDismissRequest = { makeInvisible() },
                )
            }
        }
    }


}



@Composable
fun ClothesListComponent(modifier: Modifier = Modifier, clothesOverviewState: ClothesOverviewState, clothesListState: ClothesListState) {
    val lazyListState = rememberLazyListState()
    LazyColumn(state = lazyListState) {
        items(clothesListState.clothesList) {
            ClothesItem(name = it.name, price = it.price, description = it.despcription, img = it.imgSrc)
        }
    }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(clothesOverviewState.scrollActionIdx) {
        if (clothesOverviewState.scrollActionIdx != 0) {
            coroutineScope.launch {
                lazyListState.animateScrollToItem(clothesOverviewState.scrollToItemIndex)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ClothesListComponentPreview() {
    ClothesListComponent(clothesOverviewState = ClothesOverviewState(), clothesListState = ClothesListState(listOf(
        Clothes(0, "preview", 0.0, "description", "src")
    ))
    )
}
