// File: src/com/group7/bookstore/Main.java
package com.group7.bookstore; // <-- Correct package for Main.java

import com.group7.bookstore.model.Book;
import com.group7.bookstore.service.BookstoreService; // <-- This import will now work

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final BookstoreService service = new BookstoreService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("--- Bookstore Management System ---");
        runMenu();
        System.out.println("--- Application Closed ---");
        scanner.close();
    }

    private static void runMenu() {
        while (true) {
            printMenu();
            int choice = getIntegerInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleListAllBooks(); // This method is now included
                    break;
                case 2:
                    handleAddNewBook();
                    break;
                case 3:
                    handleUpdateBookPrice(); // This method is now included
                    break;
                case 4:
                    handleDeleteBook(); // This method is now included
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
            System.out.println("------------------------------------");
        }
    }

    // ... ALL HANDLER METHODS ARE NOW INCLUDED ...

    private static void printMenu() {
        System.out.println("\nChoose an operation:");
        System.out.println("1. READ:   List all books");
        System.out.println("2. CREATE: Add a new book");
        System.out.println("3. UPDATE: Update a book's price");
        System.out.println("4. DELETE: Delete a book");
        System.out.println("5. Exit");
    }

    private static void handleListAllBooks() {
        System.out.println("\n[R]EAD: Listing all books from the database...");
        List<Book> books = service.listAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found in the database.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void handleAddNewBook() {
        System.out.println("\n[C]REATE: Add a new book");
        try {
            String title = getStringInput("Enter book title: ");
            String authorName = getStringInput("Enter author's name: ");
            String authorCountry = getStringInput("Enter author's country: ");
            BigDecimal price = getBigDecimalInput("Enter price: ");
            int quantity = getIntegerInput("Enter quantity: ");

            if (service.addNewBook(title, price, quantity, authorName, authorCountry)) {
                System.out.println("\nSUCCESS: Book added successfully!");
            } else {
                System.out.println("\nFAILURE: Failed to add book. Check error logs for details.");
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void handleUpdateBookPrice() {
        System.out.println("\n[U]PDATE: Change a book's price");
        int bookIdToUpdate = getIntegerInput("Enter book ID to update: ");
        BigDecimal newPrice = getBigDecimalInput("Enter the new price: ");
        if (service.updateBookPrice(bookIdToUpdate, newPrice)) {
            System.out.println("Price updated successfully!");
        } else {
            System.out.printf("Could not update price for book ID %d. It may not exist.\n", bookIdToUpdate);
        }
    }

    private static void handleDeleteBook() {
        System.out.println("\n[D]ELETE: Delete a book");
        int bookIdToDelete = getIntegerInput("Enter book ID to delete: ");
        if (service.removeBook(bookIdToDelete)) {
            System.out.println("Book deleted successfully!");
        } else {
            System.out.printf("Could not delete book with ID %d. It may not exist.\n", bookIdToDelete);
        }
    }

    // --- Robust Input Helper Methods ---

    private static String getStringInput(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private static int getIntegerInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static BigDecimal getBigDecimalInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number (e.g., 299.99).");
            }
        }
    }
}