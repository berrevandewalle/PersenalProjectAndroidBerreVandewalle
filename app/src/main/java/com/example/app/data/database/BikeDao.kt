package com.example.app.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BikeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bike: dbBike)

    @Update
    suspend fun update(bike: dbBike)

    @Delete
    suspend fun delete(bike: dbBike)

    @Query("SELECT * from bikes WHERE name = :name")
    fun getItem(name: String): Flow<dbBike>

    @Query("SELECT * from bikes ORDER BY name ASC")
    fun getAllItems(): Flow<List<dbBike>>
}