package com.example.app

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.app.data.database.BikeDao
import com.example.app.data.database.BikeDb
import com.example.app.data.database.asDbBike
import com.example.app.data.database.asDomainBike
import com.example.app.model.Bike
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class BikeDaoTest {
    private lateinit var bikeDao: BikeDao
    private lateinit var bikeDb: BikeDb

    private var bike1 = Bike(1, "bike1", 5000.0, "","")
    private var bike2 = Bike(2, "bike2",7000.0, "","")

    // unility functions
    private suspend fun addOneTaskToDb() {
        bikeDao.insert(bike1.asDbBike())
    }

    private suspend fun addTwoTasksToDb() {
        bikeDao.insert(bike1.asDbBike())
        bikeDao.insert(bike2.asDbBike())
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        bikeDb = Room.inMemoryDatabaseBuilder(context, BikeDb::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        bikeDao = bikeDb.bikeDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        bikeDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInert_insertTaskIntoDB() = runBlocking {
        addOneTaskToDb()
        val allItems = bikeDao.getAllItems().first()
        assertEquals(allItems[0].asDomainBike(), bike1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllTasks_returnsAllTasksFromDB() = runBlocking {
        addTwoTasksToDb()
        val allItems = bikeDao.getAllItems().first()
        assertEquals(allItems[0].asDomainBike(), bike1)
        assertEquals(allItems[1].asDomainBike(), bike2)
    }
}