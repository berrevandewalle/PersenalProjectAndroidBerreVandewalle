package com.example.app

import android.util.Log
import com.example.app.data.BikesRepository
import com.example.app.model.Bike
import com.example.app.ui.bikeOverview.BikeOverviewViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.mockito.Mockito
import java.lang.reflect.Field

/**
 * View model unit test
 *
 * @constructor Create empty View model unit test
 */
@ExperimentalCoroutinesApi
class ViewModelUnitTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private val repository: BikesRepository = Mockito.mock(BikesRepository::class.java)
    private var viewModel: BikeOverviewViewModel = BikeOverviewViewModel(repository)

    /**
     * Setup
     *
     */
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        // Set the main dispatcher to TestCoroutineDispatcher
        Dispatchers.setMain(testDispatcher)
    }

    /**
     * Add bike should call save bike with correct parameters and update bike list state
     *
     */
    @Test
    fun `addBike should call saveBike with correct parameters and update BikeListState`() =
        testScope.runBlockingTest {
            mockLog()
            // Set some sample values in the ViewModel
            viewModel.setNewBikeId(11)
            viewModel.setNewBikeName("Test Bike")
            viewModel.setNewBikePrice(1000.0)
            viewModel.setNewBikeImgSrc("test_image.jpg")
            viewModel.setNewBikeDescription("This is a test bike.")

            // Mock the repository response when refreshing bikes
            `when`(repository.refresh()).thenAnswer {
                flowOf(listOf(Bike(1, "Dummy Bike", 0.0, "", "")))
            }

            // Call addBike
            viewModel.addBike()

            // Verify that saveBike was called with the correct parameters
            verify(repository).insertBike(
                Bike(
                    id = 11,
                    name = "Test Bike",
                    price = 1000.0,
                    imgSrc = "test_image.jpg",
                    description = "This is a test bike."
                )
            )

            // Verify that BikeListState is updated correctly
            val bikeListState = viewModel.uiListState.first()
            assertEquals(2, bikeListState.bikeList.size) // Assuming there was one dummy bike initially
            assertEquals("Test Bike", bikeListState.bikeList[1].name) // Check the newly added bike
        }

    private fun mockLog() {
        val logField: Field = Log::class.java.getDeclaredField("log")
        logField.isAccessible = true
        logField.set(null, mock(android.util.Log::class.java))
    }
}