package com.example.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.example.app.data.BikeSampler
import com.example.app.data.database.BikeDao
import com.example.app.data.database.BikeDb
import com.example.app.data.database.dbBike
import com.example.app.ui.BikeApp
import com.example.app.ui.theme.BikeAppTheme
import com.example.app.ui.util.BikeNavigationType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Main activity
 *
 * @constructor Create empty Main activity
 */
class MainActivity : ComponentActivity() {
    private lateinit var bikeDb: BikeDb
    lateinit var dao: BikeDao
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*//added because the app grows. The dex file is a Dalvic Executable (a part of the compilation process of Android)
        //if it becomes to large, the OS has issues handling it well...
        val dexOutputDir: File = codeCacheDir
        dexOutputDir.setReadOnly()*/
        Log.i("vm inspection", "Main activity onCreate")
/*
        bikeDb = BikeDb.getDatabase(context = applicationContext)
        dao = bikeDb.bikeDao()

        GlobalScope.launch(Dispatchers.IO) {
            // Databasebewerkingen uitvoeren op een achtergrondthread
            val bikes = BikeSampler.getAll()
            for (bike in bikes) {
                dao.insert(dbBike(bike.id, bike.name, bike.price, bike.imgSrc, bike.description))
            }
        }
*/


        setContent {
            BikeAppTheme {
                // create a Surface to overlap image and texts
                Surface {
                    val windowSize = calculateWindowSizeClass(activity = this)
                    when (windowSize.widthSizeClass) {
                        WindowWidthSizeClass.Compact -> {
                            BikeApp(BikeNavigationType.BOTTOM_NAVIGATION)
                        }
                        WindowWidthSizeClass.Medium -> {
                            BikeApp(BikeNavigationType.NAVIGATION_RAIL)
                        }
                        WindowWidthSizeClass.Expanded -> {
                            BikeApp(navigationType = BikeNavigationType.PERMANENT_NAVIGATION_DRAWER)
                        }
                        else -> {
                            BikeApp(navigationType = BikeNavigationType.BOTTOM_NAVIGATION)
                        }
                    }
                }
            }
        }
    }
}