package com.group7.bookstore;

import com.group7.bookstore.model.Author;
import com.group7.bookstore.model.Book;
import com.group7.bookstore.service.BookstoreService;
import com.group7.bookstore.util.DatabaseUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final BookstoreService service = new BookstoreService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("--- Bookstore Management System ---");
        try {
            // Test the initial connection once.
            DatabaseUtil.getConnection();
            runMenu();
        } catch (Exception e) {
            System.err.println("FATAL: Could not connect to the database. Please check your settings.");
            System.err.println(e.getMessage());
        } finally {
            DatabaseUtil.closeConnection();
            scanner.close();
            System.out.println("\n--- Application Closed ---");
        }
    }

    private static void runMenu() {
        while (true) {
            printMainMenu();
            int choice = getIntegerInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleBookMenu();
                    break;
                case 2:
                    handleAuthorMenu();
                    break;
                case 3:
                    return; // Exit
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Manage Books");
        System.out.println("2. Manage Authors");
        System.out.println("3. Exit");
        System.out.println("---------------------");
    }

    // --- Book Management ---
    private static void handleBookMenu() {
        while (true) {
            System.out.println("\n--- Book Management ---");
            System.out.println("1. List all books");
            System.out.println("2. Add a new book");
            System.out.println("3. Update a book's price");
            System.out.println("4. Delete a book");
            System.out.println("5. Search books by title");
            System.out.println("6. Find books by author");
            System.out.println("7. Back to Main Menu");
            System.out.println("-----------------------");
            int choice = getIntegerInput("Enter your choice: ");

            switch (choice) {
                case 1: handleListAllBooks(); break;
                case 2: handleAddNewBook(); break;
                case 3: handleUpdateBookPrice(); break;
                case 4: handleDeleteBook(); break;
                case 5: handleSearchBookByTitle(); break;
                case 6: handleFindBooksByAuthor(); break;
                case 7: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    // --- Author Management ---
    private static void handleAuthorMenu() {
        while (true) {
            System.out.println("\n--- Author Management ---");
            System.out.println("1. List all authors");
            System.out.println("2. Add a new author");
            System.out.println("3. Update an author");
            System.out.println("4. Delete an author");
            System.out.println("5. Back to Main Menu");
            System.out.println("-------------------------");
            int choice = getIntegerInput("Enter your choice: ");

            switch (choice) {
                case 1: handleListAllAuthors(); break;
                case 2: handleAddNewAuthor(); break;
                case 3: handleUpdateAuthor(); break;
                case 4: handleDeleteAuthor(); break;
                case 5: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    // --- Book Handler Methods ---
    private static void handleListAllBooks() {
        System.out.println("\n[READ]: Listing all books from the database...");
        List<Book> books = service.listAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found in the database.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void handleAddNewBook() {
        System.out.println("\n[CREATE]: Add a new book");
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
    }

    private static void handleUpdateBookPrice() {
        System.out.println("\n[UPDATE]: Change a book's price");
        int bookIdToUpdate = getIntegerInput("Enter book ID to update: ");
        BigDecimal newPrice = getBigDecimalInput("Enter the new price: ");
        if (service.updateBookPrice(bookIdToUpdate, newPrice)) {
            System.out.println("Price updated successfully!");
        } else {
            System.out.println("Could not update price for book ID " + bookIdToUpdate + ". It may not exist.");
        }
    }

    private static void handleDeleteBook() {
        System.out.println("\n[DELETE]: Delete a book");
        int bookIdToDelete = getIntegerInput("Enter book ID to delete: ");
        if (service.removeBook(bookIdToDelete)) {
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Could not delete book with ID " + bookIdToDelete + ". It may not exist.");
        }
    }

    private static void handleSearchBookByTitle() {
        System.out.println("\n[SEARCH]: Search for a book");
        String title = getStringInput("Enter the book title to search for: ");
        List<Book> foundBooks = service.searchBooksByTitle(title);
        if (foundBooks.isEmpty()) {
            System.out.println("No books found with a title containing '" + title + "'.");
        } else {
            System.out.println("\nFound " + foundBooks.size() + " book(s):");
            foundBooks.forEach(System.out::println);
        }
    }

    private static void handleFindBooksByAuthor() {
        System.out.println("\n[SEARCH]: Find books by author");
        handleListAllAuthors();
        int authorId = getIntegerInput("Enter the author ID to see their books: ");
        List<Book> books = service.findBooksByAuthor(authorId);
        if (books.isEmpty()) {
            System.out.println("No books found for this author.");
        } else {
            System.out.println("\nFound " + books.size() + " book(s) for author ID " + authorId + ":");
            books.forEach(System.out::println);
        }
    }

    // --- Author Handler Methods ---
    private static void handleListAllAuthors() {
        System.out.println("\n[LIST]: Listing all authors...");
        List<Author> authors = service.listAllAuthors();
        if (authors.isEmpty()) {
            System.out.println("No authors found in the database.");
        } else {
            authors.forEach(System.out::println);
        }
    }

    private static void handleAddNewAuthor() {
        System.out.println("\n[CREATE]: Add a new author");
        String name = getStringInput("Enter author's name: ");
        String country = getStringInput("Enter author's country: ");
        if (service.addNewAuthor(name, country)) {
            System.out.println("SUCCESS: Author added!");
        } else {
            System.out.println("FAILURE: Could not add author.");
        }
    }

    private static void handleUpdateAuthor() {
        System.out.println("\n[UPDATE]: Update an author");
        handleListAllAuthors();
        int authorId = getIntegerInput("Enter the ID of the author to update: ");
        String newName = getStringInput("Enter the new name for the author: ");
        String newCountry = getStringInput("Enter the new country for the author: ");
        if (service.updateAuthor(authorId, newName, newCountry)) {
            System.out.println("SUCCESS: Author updated!");
        } else {
            System.out.println("FAILURE: Could not update author. ID may not exist.");
        }
    }

    private static void handleDeleteAuthor() {
        System.out.println("\n[DELETE]: Delete an author");
        System.out.println("WARNING: Deleting an author will NOT delete their books. The books will be kept without an assigned author.");
        handleListAllAuthors();
        int authorId = getIntegerInput("Enter the ID of the author to delete: ");
        if (service.deleteAuthor(authorId)) {
            System.out.println("SUCCESS: Author deleted!");
        } else {
            System.out.println("FAILURE: Could not delete author. ID may not exist.");
        }
    }

    // --- Input Helper Methods ---
    private static String getStringInput(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input != null && !input.isEmpty()) {
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
                System.out.println("Invalid input. Please enter a valid number (e.g., 29.99).");
            }
        }
    }
}