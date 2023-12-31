package com.example.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app.ui.bikeOverview.BikeOverview
import com.example.app.ui.bikeOverview. BikeOverview
import com.example.app.ui.clothesOverview.ClothesOverview


@Composable
fun navComponent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    fabActionVisible: Boolean = false,
    fabResetAction: () -> Unit = {},
){
    NavHost(
        navController = navController,
        startDestination = BikeOverviewScreen.Start.name,
        modifier = modifier,
    ) {
        composable(route = BikeOverviewScreen.Start.name) {
            BikeOverview(isAddingVisisble = fabActionVisible, makeInvisible = fabResetAction)
        }
/*        composable(route = ClothesOverviewScreen.Start.name) {
            ClothesOverview(isAddingVisisble = fabActionVisible, makeInvisible = fabResetAction)
        }*/
    }
}