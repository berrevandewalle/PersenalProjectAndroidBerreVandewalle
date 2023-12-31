package com.example.app.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ClothesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: dbClothes)

    @Update
    suspend fun update(item: dbClothes)

    @Delete
    suspend fun delete(item: dbClothes)

    @Query("SELECT * from clothes WHERE name = :name")
    fun getItem(name: String): Flow<dbClothes>

    @Query("SELECT * from clothes ORDER BY name ASC")
    fun getAllItems(): Flow<List<dbClothes>>
}