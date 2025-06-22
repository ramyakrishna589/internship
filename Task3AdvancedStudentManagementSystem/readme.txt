=====================================================
Advanced Student Management System - Java Assignment
====================================================

 Description:
---------------
A console-based Java application that performs student record management using
Object-Oriented Programming, Collections (ArrayList, HashMap, TreeSet), and 
file handling with Object Serialization.

This system allows you to add, update, delete, search, and display students.
All data is saved to a file (`students.dat`) to retain information between runs.

Project Structure:
---------------------
- Student.java           → Represents a student (fields, methods, Comparable)
- StudentManager.java    → Manages all student operations and data storage
- Main.java              → Menu-driven interface to interact with the system
- README.txt             → Instructions and documentation
- students.dat           → Data file created at runtime

 Technologies Used:
----------------------
- Java SE (Standard Edition)
- OOP Concepts (Encapsulation, Classes, Objects)
- Java Collections: ArrayList, HashMap, TreeSet
- Exception Handling
- File I/O with ObjectInputStream and ObjectOutputStream

 How to Run:
--------------
1. Save all `.java` files into a single folder
2. Open Command Prompt and navigate to that folder
3. Compile the code:
   javac *.java
4. Run the program:
   java Main

 Functionalities:
-------------------
- Add new student (with input validation)
- Remove student by ID
- Update existing student data
- Search student by ID
- Display all students (sorted by ID using TreeSet)
- Persistent storage (saves to and loads from `students.dat`)

 Validations:
----------------
- ID must be unique
- Age must be > 0
- Name and Address must not be empty

