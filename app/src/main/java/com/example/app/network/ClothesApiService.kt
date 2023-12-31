package com.example.app.network

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

// define what the API looks like
interface ClothesApiService {
    // suspend is added to force the user to call this in a coroutine scope
    @GET("bikes")
    suspend fun getClothes(): List<ApiClothes>

}

// helper function
fun ClothesApiService.getBikesAsFlow(): Flow<List<ApiClothes>> = flow {
    try {
        emit(getClothes())
    }
    catch(e: Exception){
        Log.e("API", "getBikesAsFlow: "+e.stackTraceToString(), )
    }
}