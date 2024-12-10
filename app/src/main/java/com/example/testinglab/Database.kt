package com.example.testinglab

object Database {
    private val users = hashMapOf<String, User>()

    // Saves a user to the database
    fun saveUser(user: User) {
        users[user.email] = user
    }

    // Retrieves a user by email from the database
    fun getUserByEmail(email: String): User? {
        return users[email]
    }
}