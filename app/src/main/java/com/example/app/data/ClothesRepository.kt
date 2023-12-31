package com.example.app.data


import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.app.data.database.BikeDao
import com.example.app.data.database.ClothesDao
import com.example.app.data.database.asDbBike
import com.example.app.data.database.asDbClothes
import com.example.app.data.database.asDomainBike
import com.example.app.data.database.asDomainBikes
import com.example.app.data.database.asDomainClothes
import com.example.app.model.Bike
import com.example.app.model.Clothes
import com.example.app.network.BikeApiService
import com.example.app.network.ClothesApiService
import com.example.app.network.asDomainObjects
import com.example.app.network.getBikesAsFlow
import com.example.app.workerUtils.WifiNotificationWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException
import java.util.UUID

interface ClothesRepository {
    // all items from datasource
    fun getClothes(): Flow<List<Clothes>>

    // one specific item
    fun getClothing(id: String): Flow<Clothes?>

    suspend fun insertClothing(clothing: Clothes)

    suspend fun deleteClothing(clothing: Clothes)

    suspend fun updateClothing(clothing: Clothes)

    suspend fun refresh()

    var wifiWorkInfo: Flow<WorkInfo>
}

class CachingClothesRepository(private val clothesDao: ClothesDao, private val clothesApiService: ClothesApiService, context: Context) : ClothesRepository {

    // this repo contains logic to refresh the tasks (remote)
    // sometimes that logic is written in a 'usecase'
    override fun getClothes(): Flow<List<Clothes>> {
        // checkes the array of items comming in
        // when empty --> tries to fetch from API
        // clear the DB if inspector is broken...
        /*runBlocking { taskDao.getAllItems().collect{
            for(t: dbTask in it)
                taskDao.delete(t)
        } }*/
        return clothesDao.getAllItems().map {
            it.asDomainClothes()
        }.onEach {
            // todo: check when refresh is called (why duplicates??)
            if (it.isEmpty()) {
                refresh()
            }
        }
    }

    override fun getClothing(name: String): Flow<Clothes?> {
        return clothesDao.getItem(name).map {
            it.asDomainClothes()
        }
    }

    override suspend fun insertClothing(clothing: Clothes) {
        clothesDao.insert(clothing.asDbClothes())
    }

    override suspend fun deleteClothing(clothing: Clothes) {
        clothesDao.delete(clothing.asDbClothes())
    }

    override suspend fun updateClothing(clothing: Clothes) {
        clothesDao.update(clothing.asDbClothes())
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
            clothesApiService.getBikesAsFlow().asDomainObjects().collect {
                    value ->
                for (clothing in value) {
                    Log.i("TEST", "refresh: $value")
                    insertClothing(clothing)
                }
            }
        } catch (e: SocketTimeoutException) {
            // log something
        }
    }
}