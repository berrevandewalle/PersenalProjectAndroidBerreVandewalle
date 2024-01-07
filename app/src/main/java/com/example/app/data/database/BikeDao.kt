package com.example.app.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Bike dao
 *
 * @constructor Create empty Bike dao
 */
@Dao
interface BikeDao {
    /**
     * Insert
     *
     * @param bike
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bike: dbBike)

    /**
     * Update
     *
     * @param bike
     */
    @Update
    suspend fun update(bike: dbBike)

    /**
     * Delete
     *
     * @param bike
     */
    @Delete
    suspend fun delete(bike: dbBike)

    /**
     * Get item
     *
     * @param name
     * @return
     */
    @Query("SELECT * from bikes WHERE name = :name")
    fun getItem(name: String): Flow<dbBike>

    /**
     * Get all items
     *
     * @return
     */
    @Query("SELECT * from bikes ORDER BY name ASC")
    fun getAllItems(): Flow<List<dbBike>>
}