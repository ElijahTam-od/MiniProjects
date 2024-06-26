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
    /**
     * Adds a book to the library's book list.
     *
     * @param book the book to be added
     */
    public void addBook(Book book) {
        bookList.add(book);
    }

    /**
     * Returns the list of all books in the library.
     *
     * @return the list of books
     */
    public List<Book> getBookList() {
        return bookList;
    }

    /**
     * Finds a book in the library by its ISBN.
     *
     * @param ISBN The ISBN of the book to be found.
     * @return An {@code Optional<Book>} containing the book if found, or an empty {@code Optional} if not found.
     */
    public Optional<Book> findBookByISBN(String ISBN){
        return bookList.stream().filter(book -> book.getISBN().equals(ISBN)).findFirst();
    }

    /**
     * Removes a book from the library by its ISBN.
     *
     * @param ISBN The ISBN of the book to be removed.
     * @throws NoSuchElementException if no book is found with the provided ISBN.
     */
    public void removeBookByISBN(String ISBN){
        findBookByISBN(ISBN).ifPresentOrElse(
                book -> bookList.remove(book),
                () -> {throw new NoSuchElementException("Book not found with ISBN: " + ISBN);}
        );
    }

    /**
     * Finds books in the library by their author.
     *
     * @param author The author of the books to be found.
     * @return A list of books written by the specified author.
     *         If no books are found, an empty list is returned.
     */
    public List<Book> findBooksByAuthor(String author){
        return bookList.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author))
                .collect(Collectors.toList());
    }

    /**
     * Finds books in the library by their title.
     *
     * @param title The title of the books to be found.
     * @return A list of books with the specified title.
     *         If no books are found, an empty list is returned.
     */
    public List<Book> findBooksByTitle(String title){
        String lowerCaseTitle = title.toLowerCase();
        return bookList.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(lowerCaseTitle))
                .collect(Collectors.toList());
    }

    private void createSampleData(){
        bookList.add(new Manuscript("William Caxton", "On English", "970-0-618-26030-0", "Leather back"));
        bookList.add(new Manuscript("William Caxton", "On The Yonder", "970-0-658-21420-0", "Leather back"));
        bookList.add(new EBook("Unknown", "Canterbury Tales", "970-0-618-24240-0", 15.2f));
        bookList.add(new EBook("Martin", "Legend of Kora", "970-0-618-31142-0", 5.05f));
    }

}

