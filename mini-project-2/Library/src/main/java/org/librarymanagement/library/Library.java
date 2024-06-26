package org.librarymanagement.library;

import org.librarymanagement.book.Book;
import org.librarymanagement.book.EBook;
import org.librarymanagement.book.Manuscript;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {
    private static Library instance;
    private static List<Book> bookList = new ArrayList<>();

    private Library(){
        bookList = new ArrayList<>();
        createSampleData(); //Adds 4 sample books
    }

    public static Library getInstance(){
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    //=======================================================
    //Add books
    public void addBook(Book book) {
        bookList.add(book);
    }

    //List all books
    public List<Book> getBookList() {
        return bookList;
    }

    //Find by ISBN
    public Optional<Book> findBookByISBN(String ISBN){
        return bookList.stream().filter(book -> book.getISBN().equals(ISBN)).findFirst();
    }

    //Remove by ISBN
    public void removeBookByISBN(String ISBN){
        findBookByISBN(ISBN).ifPresentOrElse(
                book -> bookList.remove(book),
                () -> {throw new NoSuchElementException("Book not found with ISBN: " + ISBN);}
        );
    }

    //Find by Author
    public List<Book> findBooksByAuthor(String author){
        return bookList.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    //Find by Title
    public List<Book> findBooksByTitle(String title){
        return bookList.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    private void createSampleData(){
        bookList.add(new Manuscript("William Caxton", "On English", "970-0-618-26030-0", "Leather back"));
        bookList.add(new Manuscript("William Caxton", "On The Yonder", "970-0-658-21420-0", "Leather back"));
        bookList.add(new EBook("Unknown", "Canterbury Tales", "970-0-618-24240-0", 15.2f));
        bookList.add(new EBook("Martin", "Legend of Kora", "970-0-618-31142-0", 5.05f));
    }

}

