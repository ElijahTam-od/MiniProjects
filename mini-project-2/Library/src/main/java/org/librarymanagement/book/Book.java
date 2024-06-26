package org.librarymanagement.book;

public abstract class Book implements BookInterface {
    private String author;
    private String title;
    private String ISBN;
    private final String Type;

    Book(String author, String title, String ISBN, String type){
        this.author = author;
        this.title = title;
        this.ISBN = ISBN;
        this.Type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getType() {
        return Type;
    }
}

