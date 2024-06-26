package org.librarymanagement.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.librarymanagement.book.Book;
import org.librarymanagement.book.Manuscript;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;

    @BeforeEach
    public void setUp() {
        library = Library.getInstance();
    }

    @Test
    public void testAddBook() {
        Book newBook = new Manuscript("John Doe", "Test Book", "1234567890", "Paperback");
        library.addBook(newBook);

        List<Book> bookList = library.getBookList();
        assertTrue(bookList.contains(newBook));
    }

    @Test
    public void testFindBookByISBN_existingISBN() {
        String existingISBN = "970-0-618-24240-0";
        Optional<Book> foundBook = library.findBookByISBN(existingISBN);

        assertTrue(foundBook.isPresent());
        assertEquals("Canterbury Tales", foundBook.get().getTitle());
    }

    @Test
    public void testFindBookByISBN_nonExistingISBN() {
        String nonExistingISBN = "9999999999";
        Optional<Book> foundBook = library.findBookByISBN(nonExistingISBN);

        assertTrue(foundBook.isEmpty());
    }

    @Test
    public void testRemoveBookByISBN_existingISBN() {
        String existingISBN = "970-0-618-24240-0";
        int initialSize = library.getBookList().size();

        assertDoesNotThrow(() -> library.removeBookByISBN(existingISBN));
        assertEquals(initialSize - 1, library.getBookList().size());
        assertFalse(library.findBookByISBN(existingISBN).isPresent());
    }

    @Test
    public void testRemoveBookByISBN_nonExistingISBN() {
        String nonExistingISBN = "9999999999";

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> library.removeBookByISBN(nonExistingISBN));
        assertEquals("Book not found with ISBN: " + nonExistingISBN, exception.getMessage());
    }

    @Test
    public void testFindBooksByAuthor_existingAuthor() {
        String author = "William Caxton";
        List<Book> booksByAuthor = library.findBooksByAuthor(author);

        assertEquals(2, booksByAuthor.size());
        assertTrue(booksByAuthor.stream().allMatch(book -> book.getAuthor().equalsIgnoreCase(author)));
    }

    @Test
    public void testFindBooksByAuthor_nonExistingAuthor() {
        String nonExistingAuthor = "Nonexistent Author";
        List<Book> booksByAuthor = library.findBooksByAuthor(nonExistingAuthor);

        assertTrue(booksByAuthor.isEmpty());
    }

    @Test
    public void testFindBooksByTitle_existingTitle() {
        String title = "Legend of Kora";
        List<Book> booksByTitle = library.findBooksByTitle(title);

        assertEquals(1, booksByTitle.size());
        assertTrue(booksByTitle.stream().allMatch(book -> book.getTitle().equalsIgnoreCase(title)));
    }

    @Test
    public void testFindBooksByTitle_nonExistingTitle() {
        String nonExistingTitle = "Nonexistent Title";
        List<Book> booksByTitle = library.findBooksByTitle(nonExistingTitle);

        assertTrue(booksByTitle.isEmpty());
    }
}
