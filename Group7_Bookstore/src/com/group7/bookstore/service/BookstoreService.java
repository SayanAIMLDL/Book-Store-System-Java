package com.group7.bookstore.service; 

import com.group7.bookstore.dao.AuthorDao;
import com.group7.bookstore.dao.BookDao;
import com.group7.bookstore.model.Author;
import com.group7.bookstore.model.Book;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class BookstoreService {
    private final BookDao bookDao = new BookDao();
    private final AuthorDao authorDao = new AuthorDao();

    // --- Book Service Methods ---

    public List<Book> listAllBooks() {
        try {
            return bookDao.getAllBooks();
        } catch (SQLException e) {
            System.err.println("SERVICE ERROR: Could not retrieve book list. " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public boolean addNewBook(String title, BigDecimal price, int quantity, String authorName, String authorCountry) {
        try {
            if (price.compareTo(BigDecimal.ZERO) < 0) {
                System.err.println("SERVICE VALIDATION: Price cannot be negative.");
                return false;
            }
            if (quantity < 0) {
                System.err.println("SERVICE VALIDATION: Quantity cannot be negative.");
                return false;
            }
            Author author = authorDao.findAuthorByName(authorName);
            if (author == null) {
                System.out.println("SERVICE INFO: Author '" + authorName + "' not found. Creating new author.");
                Author newAuthor = new Author();
                newAuthor.setName(authorName);
                newAuthor.setCountry(authorCountry);
                author = authorDao.createAuthor(newAuthor);
            }
            bookDao.createBook(title, price, quantity, author.getId());
            return true;
        } catch (SQLException e) {
            System.err.println("SERVICE ERROR: Could not add new book. " + e.getMessage());
            return false;
        }
    }

    public boolean updateBookPrice(int bookId, BigDecimal newPrice) {
        try {
            return bookDao.updateBookPrice(bookId, newPrice);
        } catch (SQLException e) {
            System.err.println("SERVICE ERROR: Could not update price. " + e.getMessage());
            return false;
        }
    }

    public boolean removeBook(int bookId) {
        try {
            return bookDao.deleteBook(bookId);
        } catch (SQLException e) {
            System.err.println("SERVICE ERROR: Could not remove book. " + e.getMessage());
            return false;
        }
    }

    public List<Book> searchBooksByTitle(String title) {
        try {
            return bookDao.findBooksByTitle(title);
        } catch (SQLException e) {
            System.err.println("SERVICE ERROR: Could not search for books. " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<Book> findBooksByAuthor(int authorId) {
        try {
            return bookDao.findBooksByAuthorId(authorId);
        } catch (SQLException e) {
            System.err.println("SERVICE ERROR: Could not find books by author. " + e.getMessage());
            return Collections.emptyList();
        }
    }

    // --- Author Service Methods ---

    public List<Author> listAllAuthors() {
        try {
            return authorDao.getAllAuthors();
        } catch (SQLException e) {
            System.err.println("SERVICE ERROR: Could not retrieve author list. " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public boolean addNewAuthor(String name, String country) {
        try {
            Author author = new Author();
            author.setName(name);
            author.setCountry(country);
            authorDao.createAuthor(author);
            return true;
        } catch (SQLException e) {
            System.err.println("SERVICE ERROR: Could not add new author. " + e.getMessage());
            return false;
        }
    }

    public boolean updateAuthor(int authorId, String newName, String newCountry) {
        try {
            Author author = new Author();
            author.setId(authorId);
            author.setName(newName);
            author.setCountry(newCountry);
            return authorDao.updateAuthor(author);
        } catch (SQLException e) {
            System.err.println("SERVICE ERROR: Could not update author. " + e.getMessage());
            return false;
        }
    }

    public boolean deleteAuthor(int authorId) {
        try {
            bookDao.disassociateBooksFromAuthor(authorId);
            return authorDao.deleteAuthor(authorId);
        } catch (SQLException e) {
            System.err.println("SERVICE ERROR: Could not delete author. " + e.getMessage());
            return false;
        }
    }
}
