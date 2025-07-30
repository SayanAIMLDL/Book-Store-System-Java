
package com.group7.bookstore.model;

import java.math.BigDecimal;

public class Book {
    private int id;
    private String title;
    private BigDecimal price;
    private int quantity;
    private Author author;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        // Handle cases where author might not be set
        String authorName = (author != null) ? author.getName() : "No Author";
        // Create a nicely formatted string representation
        return String.format("Book[ID=%-3d | Title=%-55s | Price=₹%-8.2f | Qty=%-3d | Author=%s]",
                id, title, price, quantity, authorName);
    }
}