package com.example.testinglab

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testinglab.ui.theme.TestingLabTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    // Helper function to perform user registration in UI tests
    private fun performUserRegistration(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ) {
        composeTestRule.setContent {
            TestingLabTheme {
                MainScreen()
            }
        }

        composeTestRule.onNodeWithText("First Name").performTextInput(firstName)
        composeTestRule.onNodeWithText("Last Name").performTextInput(lastName)
        composeTestRule.onNodeWithText("Email").performTextInput(email)
        composeTestRule.onNodeWithText("Password").performTextInput(password)
        composeTestRule.onNodeWithText("Register").performClick()
    }

    @Test
    fun successfulUserRegistration() {
        // Test successful user registration in UI
        performUserRegistration("John", "Doe", "john.doe@example.com", "password123")
        composeTestRule.onNodeWithText("Registration successful!").assertExists()
    }

    @Test
    fun displayErrorMessageOnValidationFailure() {
        // Test displaying error message on validation failure in UI
        performUserRegistration("John", "Doe", "john.doe@example.com", "pass")
        composeTestRule.onNodeWithText("Password is too short").assertExists()
    }

    @Test
    fun displaySuccessMessageOnSuccessfulRegistration() {
        // Test displaying success message on successful registration in UI
        performUserRegistration("John", "Doe", "john.doe@example.com", "password123")
        composeTestRule.onNodeWithText("Registration successful!").assertExists()
    }
}