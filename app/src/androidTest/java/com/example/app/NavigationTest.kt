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

class NavigationTest {
    private val someTaskName: String = "some task name"

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            BikeApp(navController = navController, navigationType = BikeNavigationType.BOTTOM_NAVIGATION)
        }
    }

    @Test
    fun verifyStartDestination() {
        composeTestRule
            .onNodeWithText("BikeApp")
            .assertIsDisplayed()
    }

    @Test
    fun navigateToBikeDetails() {
        composeTestRule
            .onNodeWithContentDescription("navigate to detail page")
            .performClick()
        composeTestRule
            .onNodeWithText("Bike description")
            .assertIsDisplayed()
    }

    /*
    @Test
    fun clickAddTask() {
        composeTestRule
            .onNodeWithContentDescription("Add")
            .performClick()
        composeTestRule
            .onNodeWithText("taskname")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Save")
            .assertIsDisplayed()
    }

    @Test
    fun canAddTask() {
        composeTestRule
            .onNodeWithContentDescription("Add")
            .performClick()
        composeTestRule
            .onNodeWithText("taskname")
            .performTextInput(someTaskName)
        composeTestRule
            .onNodeWithText("Save")
            .performClick()
        composeTestRule
            .onNodeWithText(someTaskName)
            .assertIsDisplayed()
    }*/
}