package com.example.testinglab

class UserRepository(private val database: Database) {
    fun saveUser(user: User) {
        database.saveUser(user)
    }

    fun getUserByEmail(email: String): User? {
        return database.getUserByEmail(email)
    }
}