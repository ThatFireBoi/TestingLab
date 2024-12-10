package com.example.testinglab

class UserManager(
    private val userRepository: UserRepository,
    private val emailService: EmailService,
    private val userValidator: UserValidator
) {
    fun registerUser(
        userData: UserRegistrationData,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            if (!userValidator.validate(userData)) {
                onError("Validation failed")
                return
            }
            val user = User(
                firstName = userData.firstName,
                lastName = userData.lastName,
                email = userData.email,
                password = userData.password,
                age = userData.age
            )
            userRepository.saveUser(user)
            emailService.sendWelcomeEmail(userData.email)
            onSuccess()
        } catch (e: Exception) {
            onError(e.message ?: "An unknown error occurred")
        }
    }

    fun authenticateUser(
        email: String,
        password: String,
        onResult: (Boolean) -> Unit
    ) {
        val user = userRepository.getUserByEmail(email)
        onResult(user?.password == password)
    }
}