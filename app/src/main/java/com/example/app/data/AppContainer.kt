package com.example.app.data

import android.content.Context
import com.example.app.data.database.BikeDb
import com.example.app.network.BikeApiService
import com.example.app.network.NetworkConnectionInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * App container
 *
 * @constructor Create empty App container
 */
interface AppContainer {
    val bikesRepository: BikesRepository
}

/**
 * Default app container
 *
 * @property context
 * @constructor Create empty Default app container
 */
class DefaultAppContainer(private val context: Context) : AppContainer {

    private val networkCheck = NetworkConnectionInterceptor(context)
    private val client = OkHttpClient.Builder()
        .addInterceptor(networkCheck)
        .connectTimeout(24, TimeUnit.HOURS) // Adjust the timeout duration as needed
        .readTimeout(24, TimeUnit.HOURS)
        .writeTimeout(24, TimeUnit.HOURS)
        .build()


    private val baseUrl = "http://10.0.2.2:3000"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
            Json.asConverterFactory("application/json".toMediaType()),
        )
        .baseUrl(baseUrl)
        .client(client)
        .build()

    private val retrofitService: BikeApiService by lazy {
        retrofit.create(BikeApiService::class.java)
    }

    /*
    override val bikesRepository: BikesRepository by lazy {
        ApiBikesRepository(retrofitService)
    }
    */
    override val bikesRepository: BikesRepository by lazy {
        CachingBikesRepository(BikeDb.getDatabase(context = context).bikeDao(), retrofitService, context)
    }


}


