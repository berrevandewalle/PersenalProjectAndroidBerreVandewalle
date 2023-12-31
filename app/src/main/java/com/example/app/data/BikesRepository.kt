package com.example.app.data

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.app.data.database.BikeDao
import com.example.app.data.database.asDbBike
import com.example.app.data.database.asDomainBike
import com.example.app.data.database.asDomainBikes
import com.example.app.data.database.dbBike
import com.example.app.model.Bike
import com.example.app.network.BikeApiService
import com.example.app.network.asDomainObjects
import com.example.app.network.getBikesAsFlow
import com.example.app.workerUtils.WifiNotificationWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import java.net.SocketTimeoutException
import java.util.UUID

interface BikesRepository {
    // all items from datasource
    fun getBikes(): Flow<List<Bike>>

    // one specific item
    fun getBike(id: String): Flow<Bike?>

    suspend fun insertBike(bike: Bike)

    suspend fun deleteBike(bike: Bike)

    suspend fun updateBike(bike: Bike)

    suspend fun refresh()

    var wifiWorkInfo: Flow<WorkInfo>
}

class CachingBikesRepository(private val bikeDao: BikeDao, private val bikeApiService: BikeApiService, context: Context) : BikesRepository {

    // this repo contains logic to refresh the tasks (remote)
    // sometimes that logic is written in a 'usecase'

    override fun getBikes(): Flow<List<Bike>> {
        // checkes the array of items comming in
        // when empty --> tries to fetch from API
        // clear the DB if inspector is broken...
        /*runBlocking { bikeDao.getAllItems().collect{
            for(t: dbBike in it)
                bikeDao.delete(t)
        } }*/

        return bikeDao.getAllItems().map {
            it.asDomainBikes()
        }.onEach {
            // todo: check when refresh is called (why duplicates??)
            if (it.isEmpty()) {
                refresh()
            }
        }
    }

    override fun getBike(name: String): Flow<Bike?> {
        return bikeDao.getItem(name).map {
            it.asDomainBike()
        }
    }

    override suspend fun insertBike(bike: Bike) {
        bikeDao.insert(bike.asDbBike())
    }

    override suspend fun deleteBike(bike: Bike) {
        bikeDao.delete(bike.asDbBike())
    }

    override suspend fun updateBike(bike: Bike) {
        bikeDao.update(bike.asDbBike())
    }

    private var workID = UUID(1,2)
    //the manager is private to the repository
    private val workManager = WorkManager.getInstance(context)
    //the info function is public
    override var wifiWorkInfo: Flow<WorkInfo> =
        workManager.getWorkInfoByIdFlow(workID)

    override suspend fun refresh() {
        //refresh is used to schedule the workrequest

        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val requestBuilder = OneTimeWorkRequestBuilder<WifiNotificationWorker>()
        val request = requestBuilder.setConstraints(constraints).build()
        workManager.enqueue(request)
        workID = request.id
        wifiWorkInfo = workManager.getWorkInfoByIdFlow(request.id)



        //note the actual api request still uses coroutines
        try {
            bikeApiService.getBikesAsFlow().asDomainObjects().collect {
                    value ->
                for (bike in value) {
                    Log.i("TEST", "refresh: $value")
                    insertBike(bike)
                }
            }
        } catch (e: SocketTimeoutException) {
            // log something
        }
    }
}