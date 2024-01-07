package com.example.app

import com.example.app.data.BikesRepository
import com.example.app.fake.FakeApiBikeRepository
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Rule

/**
 * Bike overview view model test
 *
 * @constructor Create empty Bike overview view model test
 */
@ExperimentalCoroutinesApi
class BikeOverviewViewModelTest {

    @get:Rule
    private val testDispatcher = TestCoroutineDispatcher()

    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var viewModel: BikeOverviewViewModel

    /**
     * Setup
     *
     */
    @Before
    fun setup() {
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

            // Set some sample values in the ViewModel
            viewModel.setNewBikeId(11)
            viewModel.setNewBikeName("Test Bike")
            viewModel.setNewBikePrice(1000.0)
            viewModel.setNewBikeImgSrc("test_image.jpg")
            viewModel.setNewBikeDescription("This is a test bike.")

            // Mock the repository response when refreshing bikes
            //fakeRepository.setBikesResult(flowOf(listOf(Bike(1, "Dummy Bike", 0.0, "", ""))))

            // Call addBike
            viewModel.addBike()

            // Verify that saveBike was called with the correct parameters
            // Implement the assertion based on your needs

            // Verify that BikeListState is updated correctly
            val bikeListState = viewModel.uiListState.first()
            assertEquals(10, bikeListState.bikeList.size) // Assuming there was one dummy bike initially
            assertEquals("Test Bike", bikeListState.bikeList[1].name) // Check the newly added bike
        }

    /**
     * Setting name changes state
     *
     */
    @Test
    fun settingNameChangesState() {
        val someBikeName = "Giant bike"
        viewModel.setNewBikeName(someBikeName)
        Assert.assertEquals(viewModel.uiState.value.newBikeName, someBikeName)
    }
}
