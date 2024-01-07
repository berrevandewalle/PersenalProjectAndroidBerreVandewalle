package com.example.app.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app.ui.bikeOverview.BikeOverview
import com.example.app.ui.detail.DetailBikeScreen


/**
 * Nav component
 *
 * @param navController
 * @param modifier
 * @param fabActionVisible
 * @param fabResetAction
 * @receiver
 */
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
            BikeOverview(
                isAddingVisisble = fabActionVisible,
                makeInvisible = fabResetAction,
                navController = navController,
            )
        }
        composable(route = "detailBike/{name}") { backStackEntry ->
            backStackEntry.arguments?.getString("name")?.let { name ->
                Log.d("Navigation", "Received name: $name")
                DetailBikeScreen(name = name, navController= navController)
            }
        }
    }
}