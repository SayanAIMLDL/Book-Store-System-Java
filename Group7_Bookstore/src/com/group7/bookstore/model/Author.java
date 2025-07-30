package com.group7.bookstore.model;

public class Author {
    private int id;
    private String name;
    private String country;

    // Getters and Setters for id, name, and country
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    @Override
    public String toString() {
        return String.format("Author[ID=%-3d | Name=%-25s | Country=%s]",
                id, name, country);
    }
}