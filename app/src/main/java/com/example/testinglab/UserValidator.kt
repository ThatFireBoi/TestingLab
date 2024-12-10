package com.example.testinglab

class UserValidator {
    companion object {
        const val ERROR_FIELDS_EMPTY = "All fields must be filled"
        const val ERROR_INVALID_EMAIL = "Invalid email address"
        const val ERROR_SHORT_PASSWORD = "Password must be at least 6 characters"
        const val ERROR_UNDERAGE = "User must be at least 18 years old"
    }

    // Validates if the user data meets the basic requirements
    fun validate(userData: UserRegistrationData): Boolean {
        return with(userData) {
            firstName.isNotEmpty() &&
                    lastName.isNotEmpty() &&
                    email.isNotEmpty() &&
                    password.length >= 6 &&
                    age >= 18
        }
    }

    // Validates user input and returns an error message if validation fails
    fun validateUserInput(userData: UserRegistrationData): String? {
        with(userData) {
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                return ERROR_FIELDS_EMPTY
            }
            if (!isValidEmail(email)) {
                return ERROR_INVALID_EMAIL
            }
            if (!isValidPassword(password)) {
                return ERROR_SHORT_PASSWORD
            }
            if (!isValidAge(age)) {
                return ERROR_UNDERAGE
            }
        }
        return null
    }

    // Checks if the email is valid
    private fun isValidEmail(email: String): Boolean {
        return email.contains("@")
    }

    // Checks if the password meets the minimum length requirement
    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    // Checks if the age meets the minimum age requirement
    private fun isValidAge(age: Int): Boolean {
        return age >= 18
    }
}