package com.example.app.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.app.R

/**
 * Bike overview screen
 *
 * @property title
 * @property icon
 * @constructor Create empty Bike overview screen
 */
enum class BikeOverviewScreen(@StringRes val title: Int, val icon: ImageVector) {
    /**
     * Start
     *
     * @constructor Create empty Start
     */
    Start(title = R.string.app_name, icon = Icons.Filled.Check),

    /**
     * Detail bike
     *
     * @constructor Create empty Detail bike
     */
    detailBike(title = R.string.detail, icon = Icons.Filled.Check)

}
