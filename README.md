## Purpose

This lab was designed to provide practical experience in refactoring existing Android code to improve its readability, maintainability, and adherence to Clean Code and SOLID principles, with a focus on applications built using Jetpack Compose. 
Additionally, we had to write unit tests and UI tests to ensure that the refactored code maintains its intended functionality.

During the refactoring process, several changes were made to improve the code quality and maintainability. Following is a brief summary of the changes. These changes addressed specific code smells such as lack of documentation, inefficient data 
structures, blocking operations, and unoptimized validation logic: 

# Validation Optimization

-Optimized the UserValidator class by making the validation process to return errors as soon as they are detected.

# Database Optimization

-Replaced the mutableList with a HashMap in the Database class to improve the efficiency of user lookups by email.

# Code Comments and Documentation

-Added comments and documentation to classes and methods to improve readability and maintainability.

# State Management

-Used rememberSaveable to retain state across recompositions and configuration changes, ensuring efficient state management.

-Improved the MainScreen function by organizing state variables and UI elements clearly

## SOLID Principles

-Single Responsibility Principle: Each class has a single responsibility, such as UserValidator for validation, EmailService for sending emails, and Database for data storage.
-Open/Closed Principle: Classes are open for extension but closed for modification. For example, the UserValidator can be extended with new validation methods without modifying existing code.
-Liskov Substitution Principle: Accomplished by ensuring that objects of derived classes can replace objects of the base class.
-Interface Segregation Principle: Accomplished by ensuring that classes do not depend on methods they do not use.
-Dependency Inversion Principle: High-level modules (UserManager) depend on abstractions (UserRepository, EmailService, UserValidator) rather than concrete implementations.
