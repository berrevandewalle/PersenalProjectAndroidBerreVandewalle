package com.example.app.ui

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.app.R
import com.example.app.ui.components.BikeAppAppBar
import com.example.app.ui.components.BikeBottomAppBar
import com.example.app.ui.components.BikeNavigationRail
import com.example.app.ui.components.NavigationDrawerContent
import com.example.app.ui.navigation.BikeOverviewScreen
import com.example.app.ui.navigation.navComponent
import com.example.app.ui.util.BikeNavigationType
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.ui.bikeOverview.BikeListState
import com.example.app.ui.bikeOverview.BikeOverviewState
import com.example.app.ui.bikeOverview.BikeOverviewViewModel
import kotlinx.coroutines.launch


/**
 * Bike app
 *
 * @param navigationType
 * @param navController
 * @param modifier
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BikeApp(
    navigationType: BikeNavigationType,
    navController: NavHostController = rememberNavController(),
){

    val backStackEntry by navController.currentBackStackEntryAsState()

    val goHome: () -> Unit = {
        navController.popBackStack(
            BikeOverviewScreen.Start.name,
            inclusive = false,
        )
    }

    val goBack: () -> Unit = {
        navController.popBackStack(
            BikeOverviewScreen.Start.name,
            inclusive = false,
        )
    }
    val currentScreenTitle = BikeOverviewScreen.values().find {
        it.name == backStackEntry?.destination?.route
    }?.title ?: BikeOverviewScreen.Start.title

    var isAddNewVisible by remember { mutableStateOf(false) }

    if (navigationType == BikeNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(drawerContent = {
            PermanentDrawerSheet(Modifier.width(dimensionResource(R.dimen.drawer_width))) {
                NavigationDrawerContent(
                    selectedDestination = navController.currentDestination,
                    onTabPressed = { node: String -> navController.navigate(node) },
                    modifier = Modifier
                        .wrapContentWidth()
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.inverseOnSurface)
                        .padding(dimensionResource(R.dimen.drawer_padding_content)),
                )
            }
        }) {
            Scaffold(
                containerColor = Color.Transparent,
                topBar = {
                    BikeAppAppBar(
                        currentScreenTitle = currentScreenTitle,
                        goBack = goBack
                    )
                },
                modifier = Modifier.padding(dimensionResource(id = R.dimen.drawer_width), 0.dp, 0.dp, 0.dp )
            ) { innerPadding ->
                navComponent(navController, modifier = Modifier.padding(innerPadding))
            }
        }

        }else if (navigationType == BikeNavigationType.BOTTOM_NAVIGATION) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                BikeAppAppBar(
                    currentScreenTitle = currentScreenTitle,
                    goBack = goBack
                )
            },
            bottomBar = {

                BikeBottomAppBar(goHome)

            },
            floatingActionButton = {
                FloatingActionButton(onClick = { isAddNewVisible = !isAddNewVisible }) {
                    Log.d("add2", isAddNewVisible.toString())
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            },
        ) { innerPadding ->
            navComponent(
                navController,
                modifier = Modifier.padding(innerPadding),
                fabActionVisible = isAddNewVisible,
                fabResetAction = { isAddNewVisible = false }
            )
        }
    }else {
        Row {
            AnimatedVisibility(visible = navigationType == BikeNavigationType.NAVIGATION_RAIL) {
                val navigationRailContentDescription = stringResource(R.string.navigation_rail)
                BikeNavigationRail(
                    selectedDestination = navController.currentDestination,
                    onTabPressed = { node: String -> navController.navigate(node) },
                )
            }
            Scaffold(
                containerColor = Color.Transparent,
                topBar = {
                    BikeAppAppBar(
                        currentScreenTitle = currentScreenTitle,
                        goBack = goBack
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = { isAddNewVisible = !isAddNewVisible }) {
                        Log.d("add2", isAddNewVisible.toString())
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                },
            ) { innerPadding ->
                navComponent(
                    navController,
                    modifier = Modifier.padding(innerPadding),
                    fabActionVisible = isAddNewVisible,
                    fabResetAction = { isAddNewVisible = false }
                )
            }
        }
    }
}


/*
@Preview(widthDp = 500)
@Composable
fun BikeAppPreview() {
    BikeAppTheme {
        // create a box to overlap image and texts
        Surface(modifier = Modifier.fillMaxWidth()) {
            BikeBody(listOf(Bike(1, "Cervelo", 100.0, "https://www.lease-a-bike.be/cdn-cgi/image/format=auto,quality=70,width=750/storage/uploads/cervelo-s5-black.png"),
                Bike(2, "S-works", 200.0, "https://www.bikeshop.nl/sites/default/files/tarmac-sl7-sw-etap-carb-cmlnsilgrn_hero.jpg"),
                Bike(3, "Trek", 300.0, "https://www.lease-a-bike.nl/cdn-cgi/image/format=auto,quality=70,width=750/storage/uploads/97ea3f31-3681-4edb-b7b8-f05f0ae2aaec/modane-sl6.jpg")
            ,

            ), modifier = Modifier.fillMaxWidth())
        }
    }
}*/


