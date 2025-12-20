# Student Management System (Java)

A console-based Student Management System built using Core Java.  
This project helps manage student records with features like adding, updating, deleting, searching, and sorting students.  
Data is persisted using JSON format with the help of the Jackson library.

---

## Features

- Add new student records
- Update existing student details
- Delete student records
- Search students by partial name
- Sort students by:
  - Name
  - Roll number
  - Average marks
  - Grade
- JSON-based file persistence
- Custom exception handling for better error management

---

##  Technologies Used

- **Java (Core Java)**
- **Jackson Library** (for JSON serialization & deserialization)
- **Collections Framework** (`Map`, `List`)
- **Git & GitHub** (version control)

---

## 📂 Project Structure 

Student-Management-System-Java/
│
├── miniproject1.java        # Main application file (entry point)
├── students.json            # JSON file for persistent student data
├── README.md                # Project documentation
└── .gitignore               # Git ignored files 
students.json  # Auto-generated file to store student records

 ## ▶️ How to Run the Project

git clone https://github.com/DRHUV18ux/Student-Management-System-Java.git
cd Student-Management-System-Java
javac -cp ".;lib/*" miniproject1.java
 java -cp ".;lib/*" miniproject1




