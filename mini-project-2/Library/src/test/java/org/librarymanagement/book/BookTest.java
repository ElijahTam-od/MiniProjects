package org.librarymanagement.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestBook extends Book {
    public TestBook(String author, String title, String ISBN, String type) {
        super(author, title, ISBN, type);
    }
}

public class BookTest{
    private TestBook book;

    @BeforeEach
    public void setUp() {
        book = new TestBook("Author", "Title", "1234567890", "Ebook");
    }

    @Test
    public void testGetAuthor() {
        assertEquals("Author", book.getAuthor());
    }

    @Test
    public void testSetAuthor() {
        book.setAuthor("New Author");
        assertEquals("New Author", book.getAuthor());
    }

    @Test
    public void testGetTitle() {
        assertEquals("Title", book.getTitle());
    }

    @Test
    public void testSetTitle() {
        book.setTitle("New Title");
        assertEquals("New Title", book.getTitle());
    }

    @Test
    public void testGetISBN() {
        assertEquals("1234567890", book.getISBN());
    }

    @Test
    public void testSetISBN() {
        book.setISBN("0987654321");
        assertEquals("0987654321", book.getISBN());
    }

    @Test
    public void testGetType() {
        assertEquals("Ebook", book.getType());
    }

    @Test
    public void testSetAndGetMethods() {
        book.setAuthor("Another Author");
        book.setTitle("Another Title");
        book.setISBN("1111111111");

        assertEquals("Another Author", book.getAuthor());
        assertEquals("Another Title", book.getTitle());
        assertEquals("1111111111", book.getISBN());
        assertEquals("Ebook", book.getType()); // Type should not change as it's final
    }

    @Test
    public void testConstructorAndFields() {
        TestBook anotherBook = new TestBook("Test Author", "Test Title", "2222222222", "Manuscript");
        assertEquals("Test Author", anotherBook.getAuthor());
        assertEquals("Test Title", anotherBook.getTitle());
        assertEquals("2222222222", anotherBook.getISBN());
        assertEquals("Manuscript", anotherBook.getType());
    }
}
