# 🎓 Student Management System (Java 8 + JDBC)

A console-based Student Management System built using Java 8 features and JDBC, following modular clean architecture and production-ready coding practices.

---

## 📌 Features

- ✅ Add, Update, Delete, View Students
- ✅ Java 8 Features: Streams, Lambdas, Optionals
- ✅ JDBC with MySQL for database interaction
- ✅ DAO-Service Layer Architecture
- ✅ File-based DB Configuration
- ✅ Exception Handling & Logging
- ✅ Modular Project Structure
- ✅ JUnit 5 Test Cases
- ✅ CSV Export and Log File Generation (optional)

---

## 🏗️ Project Structure

student-management/
├── config/ # DB configuration file
├── dao/ # DAO interfaces and implementations
├── model/ # Student model/entity
├── service/ # Business logic layer
├── util/ # Utility classes (DB, Validators, CSV, etc.)
├── exception/ # Custom exceptions
├── test/ # JUnit test cases
├── logs/ # Log file output
├── MainApp.java # Console UI Entry point
├── README.md # Project documentation


---

## 🛠️ Technologies Used

- Java 8
- JDBC
- MySQL
- DAO-Service Pattern
- JUnit 5 (Testing)
- java.util.logging (Logging)
- File-based configuration

---

## 💾 MySQL Database Setup

```sql
CREATE DATABASE student_db;
USE student_db;

CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    marks DOUBLE
);

