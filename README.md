# Book Store Management System (Java & JDBC)

This project is a comprehensive Book Store Management System built using Java and connected to a MySQL database via JDBC. It allows users to perform CRUD (Create, Read, Update, Delete) operations on books and authors, managing the inventory and author information effectively.

## ✨ Features

*   **Book Management**: Add, view, update, and delete book records.
*   **Author Management**: Add, view, update, and delete author details.
*   **Database Connectivity**: Utilizes Java Database Connectivity (JDBC) with a MySQL connector to ensure persistent data storage.
*   **Service-Oriented Architecture**: Organizes code into distinct layers (DAO, Service, Model) for better maintainability and scalability.
*   **Console-Based UI**: A straightforward and interactive command-line interface to manage the bookstore.

## 🛠️ Tech Stack

*   **Programming Language**: Java
*   **Database**: MySQL
*   **API/Driver**: Java Database Connectivity (JDBC)

## 📦 Database Schema

The database consists of two primary tables: `authors` and `books`. A one-to-many relationship exists where one author can write multiple books.

### Authors Table
This table stores information about the authors.

![Author Table Schema](<img width="392" height="280" alt="image" src="https://github.com/user-attachments/assets/fda931aa-3048-456f-b5cb-d45a40f6aadf" />
)

### Books Table
This table stores details about the books, including a foreign key `author_id` that links to the `authors` table.

![Book Table Schema](<img width="627" height="342" alt="image" src="https://github.com/user-attachments/assets/155ef79b-bdb2-49c2-8b4c-c49088ff4d9e" />


## 📸 Application Output Screenshots

![initial](https://github.com/user-attachments/assets/e910065b-af83-4ab7-8a32-c9d5cbedf865)


### 1. Main Menu
The main entry point of the application, showing all available options.

![Main Menu Screenshot](![initial](https://github.com/user-attachments/assets/b6ecb947-df29-4f74-bd1a-8398b99185d5)
)

### 2. Adding a New Book
The process of adding a new book to the database.

![Adding Book Screenshot](![Create](https://github.com/user-attachments/assets/ff923c86-8b69-4fc1-9a8d-a2670772f55c)
)

### 3. Viewing All Books
A list of all books currently available in the bookstore.

![View All Books Screenshot](![ReadAsList](<img width="1433" height="681" alt="image" src="https://github.com/user-attachments/assets/b84698a5-bd96-4613-b1ce-837412729404" />))

### 4. Searching for a Book
The output when searching for a specific book by its  title.

![Search Book Screenshot](![ReadByTitle]<img width="1346" height="426" alt="image" src="https://github.com/user-attachments/assets/544f0329-e5e1-4e06-b1da-a60beccc1385" />
)


## 🚀 How to Get Started

1.  **Clone the repository**:
    ```bash
    git clone https://github.com/SayanAIMLDL/Book-Store-System-Java.git
    ```
2.  **Configure the Database**:
    *   Create a MySQL database.
    *   Update the database connection details (URL, username, password) in `src/com/group7/bookstore/util/DatabaseUtil.java`.
    *   Execute the SQL script to create the `authors` and `books` tables.![initial](https://github.com/user-attachments/assets/f365d475-d012-4c52-88a2-f84d4c2e58e9)


3.  **Add the MySQL JDBC Driver**:
    *   Ensure the `mysql-connector-j` library is included in your project's build path.

4.  **Run the Application**:
    *   Compile and run the `Main.java` file to start the application.
