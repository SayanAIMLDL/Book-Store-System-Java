package com.group7.bookstore.dao;

import com.group7.bookstore.model.Author;
import com.group7.bookstore.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

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
        return null; 
    }

    public Author createAuthor(Author author) throws SQLException {
        String sql = "INSERT INTO authors (name, country) VALUES (?, ?)";
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
                    author.setId(generatedKeys.getInt(1));
                    return author;
                } else {
                    throw new SQLException("Creating author failed, no ID obtained.");
                }
            }
        }
    }

    
    public List<Author> getAllAuthors() throws SQLException {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT * FROM authors ORDER BY name";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Author author = new Author();
                author.setId(rs.getInt("author_id"));
                author.setName(rs.getString("name"));
                author.setCountry(rs.getString("country"));
                authors.add(author);
            }
        }
        return authors;
    }

    
    public boolean updateAuthor(Author author) throws SQLException {
        String sql = "UPDATE authors SET name = ?, country = ? WHERE author_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, author.getName());
            pstmt.setString(2, author.getCountry());
            pstmt.setInt(3, author.getId());
            return pstmt.executeUpdate() > 0;
        }
    }

    
    public boolean deleteAuthor(int authorId) throws SQLException {
        String sql = "DELETE FROM authors WHERE author_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, authorId);
            return pstmt.executeUpdate() > 0;
        }
    }
}