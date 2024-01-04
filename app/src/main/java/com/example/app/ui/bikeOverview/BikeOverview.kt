package com.example.app.ui.bikeOverview

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.app.data.BikeSampler
import com.example.app.model.Bike
import com.example.app.ui.components.bike.BikeItem
import com.example.app.ui.components.bike.CreateBike
import com.example.app.ui.navigation.navComponent
import kotlinx.coroutines.launch



@Composable
fun BikeOverview(
    modifier: Modifier = Modifier,
    bikeOverviewViewModel: BikeOverviewViewModel = viewModel(factory = BikeOverviewViewModel.Factory),
    isAddingVisisble: Boolean = false,
    makeInvisible: () -> Unit = {},
    navController: NavHostController
){
    Log.i("vm inspection", "BikeOverview composition")
    val bikeOverviewState by bikeOverviewViewModel.uiState.collectAsState()
    val bikeListState by bikeOverviewViewModel.uiListState.collectAsState()

    // use the ApiState
    val bikeApiState = bikeOverviewViewModel.bikeApiState

    //use the workerstate
    val workerState by bikeOverviewViewModel.wifiWorkerState.collectAsState()
    Column {
        /*when(workerState.workerInfo?.state){
            null -> Text("state unknown")
            else -> Text(workerState.workerInfo?.state!!.name)
        }*/

        Box(modifier = modifier) {
            when (bikeApiState) {
                is BikeApiState.Loading -> Text("Loading...")
                is BikeApiState.Error -> Text("Couldn't load...")
                is BikeApiState.Success -> BikeListComponent(bikeOverviewState = bikeOverviewState,
                    bikeListState = bikeListState, navController = navController)
            }

            if (isAddingVisisble) {
                CreateBike(
                    bikeName = bikeOverviewState.newBikeName,
                    bikePrice = bikeOverviewState.newBikePrice,
                    bikeImgSrc = bikeOverviewState.newBikeImgSrc,
                    bikeDecription = bikeOverviewState.newBikeDescription,
                    onBikeNameChanged = { bikeOverviewViewModel.setNewBikeName(it) },
                    onBikePriceChanged = { bikeOverviewViewModel.setNewBikePrice(it) },
                    onBikeImgSrcChanged = { bikeOverviewViewModel.setNewBikeImgSrc(it) },
                    onBikeDescriptionChanged = { bikeOverviewViewModel.setNewBikeDescription(it) },
                    onBikeSaved = {
                        bikeOverviewViewModel.addBike()
                        makeInvisible()
                    },
                    onDismissRequest = { makeInvisible() },
                )
            }
        }
    }


}



@Composable
fun BikeListComponent(
    modifier: Modifier = Modifier,
    bikeOverviewState: BikeOverviewState,
    bikeListState: BikeListState,
    navController: NavHostController,
 ) {
    val lazyListState = rememberLazyListState()
    LazyColumn(state = lazyListState) {
        items(bikeListState.bikeList) {
            BikeItem(
                name = it.name, price = it.price, img = it.imgSrc, navController = navController)
        }
    }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(bikeOverviewState.scrollActionIdx) {
        if (bikeOverviewState.scrollActionIdx != 0) {
            coroutineScope.launch {
                lazyListState.animateScrollToItem(bikeOverviewState.scrollToItemIndex)
            }
        }
    }
}

/*
@Preview(showBackground = true, widthDp = 1000)
@Composable
fun BikeListComponentPreview() {
    BikeListComponent(bikeOverviewState = BikeOverviewState(BikeSampler.getAll()), bikeListState = BikeListState(listOf(
        Bike(0, "previewbike", 0.0, "src")
    )))
}
*/