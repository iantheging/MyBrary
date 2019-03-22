package com.mybrary;

public class Book {

    private String title;
    private String author;
    private String isbn;
    private int ownerID;
    private int borrowID;

    public Book() {
        title = "Default";
        author = "Default";
        isbn = "Default";
        ownerID = 11;
        borrowID = 11;
    }

    public Book(String title, String author, String isbn, int ownerID, int borrowID) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.ownerID = ownerID;
        this.borrowID = borrowID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public int getBorrowID() {
        return borrowID;
    }
}
