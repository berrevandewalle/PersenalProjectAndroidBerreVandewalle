package com.example.app.network

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

// define what the API looks like
interface BikeApiService {
    // suspend is added to force the user to call this in a coroutine scope
    @GET("bikes")
    suspend fun getBikes(): List<ApiBike>

}

// helper function
fun BikeApiService.getBikesAsFlow(): Flow<List<ApiBike>> = flow {
    try {
        emit(getBikes())
    }
    catch(e: Exception){
        Log.e("API", "getBikesAsFlow: "+e.stackTraceToString(), )
    }
}