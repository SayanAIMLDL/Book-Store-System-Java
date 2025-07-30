package com.group7.bookstore.dao;

import com.group7.bookstore.model.Author;
import com.group7.bookstore.model.Book;
import com.group7.bookstore.util.DatabaseUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    private static final String COL_BOOK_ID = "book_id";
    private static final String COL_TITLE = "title";
    private static final String COL_PRICE = "price";
    private static final String COL_QUANTITY = "quantity";
    private static final String COL_AUTHOR_ID = "author_id";
    private static final String COL_AUTHOR_NAME = "author_name";
    private static final String COL_COUNTRY = "country";


    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.book_id, b.title, b.price, b.quantity, " + "a.author_id, a.name AS author_name, a.country " + "FROM books b LEFT JOIN authors a ON b.author_id = a.author_id ORDER BY b.book_id";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
        }
        return books;
    }

    public void createBook(String title, BigDecimal price, int quantity, Integer authorId) throws SQLException {
        String sql = "INSERT INTO books (title, price, quantity, author_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setBigDecimal(2, price);
            pstmt.setInt(3, quantity);

            if (authorId != null) {
                pstmt.setInt(4, authorId);
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }
            pstmt.executeUpdate();
        }
    }

    public boolean updateBookPrice(int bookId, BigDecimal newPrice) throws SQLException {
        String sql = "UPDATE books SET price = ? WHERE book_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBigDecimal(1, newPrice);
            pstmt.setInt(2, bookId);
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteBook(int bookId) throws SQLException {
        String sql = "DELETE FROM books WHERE book_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookId);
            return pstmt.executeUpdate() > 0;
        }
    }

    private Book mapResultSetToBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt(COL_BOOK_ID));
        book.setTitle(rs.getString(COL_TITLE));
        book.setPrice(rs.getBigDecimal(COL_PRICE));
        book.setQuantity(rs.getInt(COL_QUANTITY));

        int authorId = rs.getInt(COL_AUTHOR_ID);
        if (!rs.wasNull()) {
            Author author = new Author();
            author.setId(authorId);
            author.setName(rs.getString(COL_AUTHOR_NAME));
            author.setCountry(rs.getString(COL_COUNTRY));
            book.setAuthor(author);
        } else {
            book.setAuthor(null);
        }
        return book;
    }
}