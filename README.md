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

![Author Table Schema](https://github.com/user-attachments/assets/fda931aa-3048-456f-b5cb-d45a40f6aadf)

### Books Table
This table stores details about the books, including a foreign key `author_id` that links to the `authors` table.

![Book Table Schema](https://github.com/user-attachments/assets/155ef79b-bdb2-49c2-8b4c-c49088ff4d9e)


## 📸 Application Output Screenshots

![initial](https://github.com/user-attachments/assets/90e749f1-4538-4578-a51d-4500de27899d)
![initial]()
![initial]()
  


### 1. Main Menu
The main entry point of the application, showing all available options.

![Main Menu Screenshot](https://github.com/user-attachments/assets/bcfd29e1-8676-4241-90dd-eea0cbcac241)
![Main Menu Screenshot](https://github.com/user-attachments/assets/ad86220b-6571-4cb7-b321-1dbf9201c969)


### 2. Adding a New Book
The process of adding a new book to the database.

![Adding Book Screenshot](![Create](https://github.com/user-attachments/assets/ff923c86-8b69-4fc1-9a8d-a2670772f55c)

### 3. Viewing All Books
A list of all books currently available in the bookstore.

![View All Books Screenshot](![ReadAsList](https://github.com/user-attachments/assets/b84698a5-bd96-4613-b1ce-837412729404)

### 4. Searching for a Book
The output when searching for a specific book by its  title.

![Search Book Screenshot](https://github.com/user-attachments/assets/544f0329-e5e1-4e06-b1da-a60beccc1385)
