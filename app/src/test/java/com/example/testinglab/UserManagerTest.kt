package com.example.testinglab

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull

@RunWith(MockitoJUnitRunner::class)
class UserManagerTest {

    private lateinit var userManager: UserManager
    private lateinit var userValidator: UserValidator
    private lateinit var userRepository: UserRepository
    private lateinit var emailService: EmailService

    @Before
    fun setUp() {
        // Initialize mocks and UserManager instance before each test
        userValidator = mock(UserValidator::class.java)
        userRepository = mock(UserRepository::class.java)
        emailService = mock(EmailService::class.java)
        userManager = UserManager(userRepository, emailService, userValidator)
    }

    @Test
    fun `registerUser should return null on successful registration`() {
        val userData =
            UserRegistrationData("John", "Doe", "john.doe@example.com", "password123", 25)
        `when`(userValidator.validateUserInput(userData)).thenReturn(null)

        // Test successful user registration
        val result = userManager.registerUser(userData, onSuccess = {}, onError = {})

        assertNull(result)
        verify(userRepository).saveUser(any(User::class.java))
        verify(emailService).sendWelcomeEmail(userData.email)
    }

    @Test
    fun `registerUser should return error message on validation failure`() {
        val userData = UserRegistrationData("John", "Doe", "john.doe@example.com", "pass", 25)
        `when`(userValidator.validateUserInput(userData)).thenReturn(UserValidator.ERROR_SHORT_PASSWORD)

        // Test user registration failure due to validation errors
        val result = userManager.registerUser(userData, onSuccess = {}, onError = {})

        assertEquals(UserValidator.ERROR_SHORT_PASSWORD, result)
        verify(userRepository, never()).saveUser(any(User::class.java))
        verify(emailService, never()).sendWelcomeEmail(anyString())
    }

    @Test
    fun `authenticateUser should return user on successful authentication`() {
        val email = "john.doe@example.com"
        val password = "password123"
        val user = User("John", "Doe", email, password, 25)
        `when`(userRepository.getUserByEmail(email)).thenReturn(user)

        // Test successful user authentication
        val result = userManager.authenticateUser(email, password, onResult = {})

        assertEquals(user, result)
    }

    @Test
    fun `authenticateUser should return null on authentication failure`() {
        val email = "john.doe@example.com"
        val password = "password123"
        `when`(userRepository.getUserByEmail(email)).thenReturn(null)

        // Test user authentication failure
        val result = userManager.authenticateUser(email, password, onResult = {})

        assertNull(result)
    }
}