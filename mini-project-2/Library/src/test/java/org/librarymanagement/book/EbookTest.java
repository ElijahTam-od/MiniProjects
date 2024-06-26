package org.librarymanagement.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EbookTest {
    private EBook ebook;

    @BeforeEach
    public void setUp() {
        ebook = new EBook("Author", "Title", "1234567890", 15.5f);
    }

    @Test
    public void testGetAuthor() {
        assertEquals("Author", ebook.getAuthor());
    }

    @Test
    public void testSetAuthor() {
        ebook.setAuthor("New Author");
        assertEquals("New Author", ebook.getAuthor());
    }

    @Test
    public void testGetTitle() {
        assertEquals("Title", ebook.getTitle());
    }

    @Test
    public void testSetTitle() {
        ebook.setTitle("New Title");
        assertEquals("New Title", ebook.getTitle());
    }

    @Test
    public void testGetISBN() {
        assertEquals("1234567890", ebook.getISBN());
    }

    @Test
    public void testSetISBN() {
        ebook.setISBN("0987654321");
        assertEquals("0987654321", ebook.getISBN());
    }

    @Test
    public void testGetType() {
        assertEquals("EBook", ebook.getType());
    }

    @Test
    public void testGetSizeInMB() {
        assertEquals(15.5f, ebook.getSizeInMB());
    }

    @Test
    public void testSetSizeInMB() {
        ebook.setSizeInMB(20.0f);
        assertEquals(20.0f, ebook.getSizeInMB());
    }

    @Test
    public void testSetSizeInMBPositive() {
        ebook.setSizeInMB(20.0f);
        assertEquals(20.0f, ebook.getSizeInMB());
    }

    @Test
    public void testSetSizeInMBZero() {
        ebook.setSizeInMB(0.0f);
        assertEquals(0.0f, ebook.getSizeInMB());
    }

    @Test
    public void testSetSizeInMBNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            ebook.setSizeInMB(-5.0f);
        });
    }

    @Test
    public void testSetSizeInMBNegativeWithMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ebook.setSizeInMB(-5.0f);
        });
        assertEquals("Size in MB cannot be negative.", exception.getMessage());
    }

    @Test
    public void testConstructorAndFields() {
        EBook newEbookTest = new EBook("Test Author", "Test Title", "2222222222", 25.0f);
        assertEquals("Test Author", newEbookTest.getAuthor());
        assertEquals("Test Title", newEbookTest.getTitle());
        assertEquals("2222222222", newEbookTest.getISBN());
        assertEquals("EBook", newEbookTest.getType());
        assertEquals(25.0f, newEbookTest.getSizeInMB());
    }
}
