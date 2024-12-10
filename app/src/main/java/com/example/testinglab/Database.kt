package com.example.testinglab

object Database {
    private val users = mutableListOf<User>()

    // Saves a user to the database
    fun saveUser(user: User) {
        users.add(user)
    }

    // Retrieves a user by email from the database
    fun getUserByEmail(email: String): User? {
        return users.find { it.email == email }
    }
}