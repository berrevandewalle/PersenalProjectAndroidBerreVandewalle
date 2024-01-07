package com.example.app

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.app.ui.BikeApp
import com.example.app.ui.util.BikeNavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Navigation test
 *
 * @constructor Create empty Navigation test
 */
class NavigationTest {
    private val someBikeName: String = "some task name"
    private val someBikePrice: String = 4444.0.toString()

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    /**
     * Setup app nav host
     *
     */
    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            BikeApp(navController = navController, navigationType = BikeNavigationType.BOTTOM_NAVIGATION)
        }
    }

    /**
     * Verify start destination
     *
     */
    @Test
    fun verifyStartDestination() {
        composeTestRule
            .onNodeWithText("Bike app")
            .assertIsDisplayed()
    }



    /**
     * Click add task
     *
     */
    @Test
    fun clickAddTask() {
        composeTestRule
            .onNodeWithContentDescription("Add")
            .performClick()
        composeTestRule
            .onNodeWithText("bike name")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Save")
            .assertIsDisplayed()
    }

    /**
     * Can add task
     *
     */
    @Test
    fun canAddTask() {
        composeTestRule
            .onNodeWithContentDescription("Add")
            .performClick()
        composeTestRule
            .onNodeWithText("bike name")
            .performTextInput(someBikeName)
        composeTestRule
            .onNodeWithText("price bike")
            .performTextInput(someBikePrice)
        composeTestRule
            .onNodeWithText("Save")
            .performClick()
        composeTestRule
            .onNodeWithText(someBikeName)
            .assertIsDisplayed()
    }
}