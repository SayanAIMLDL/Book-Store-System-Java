package com.group7.bookstore.dao;

import com.group7.bookstore.model.Author;
import com.group7.bookstore.util.DatabaseUtil;

import java.sql.*;

public class AuthorDao {

    /**
     * Finds an author by their name.
     * @param name The name of the author to find.
     * @return An Author object if found, otherwise null.
     * @throws SQLException
     */
    public Author findAuthorByName(String name) throws SQLException {
        String sql = "SELECT * FROM authors WHERE name = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Author author = new Author();
                author.setId(rs.getInt("author_id"));
                author.setName(rs.getString("name"));
                author.setCountry(rs.getString("country"));
                return author;
            }
        }
        return null; // Author not found
    }

    /**
     * Creates a new author in the database.
     * @param author The Author object to create (without an ID).
     * @return The same Author object, now updated with the auto-generated ID from the database.
     * @throws SQLException
     */
    public Author createAuthor(Author author) throws SQLException {
        String sql = "INSERT INTO authors (name, country) VALUES (?, ?)";
        // Use RETURN_GENERATED_KEYS to get the new author_id
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, author.getName());
            pstmt.setString(2, author.getCountry());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating author failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Update the author object with the new ID
                    author.setId(generatedKeys.getInt(1));
                    return author;
                } else {
                    throw new SQLException("Creating author failed, no ID obtained.");
                }
            }
        }
    }
}