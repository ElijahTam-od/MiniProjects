package org.librarymanagement.library;

import org.librarymanagement.book.Book;
import org.librarymanagement.book.EBook;
import org.librarymanagement.book.Manuscript;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Singleton class to handle library input from the user.
 */
public class LibraryInputHandler {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = Library.getInstance();
    private static LibraryInputHandler instance;
    private boolean exit = false;

    private LibraryInputHandler() {
    }


    /**
     * Returns the singleton instance of the LibraryInputHandler.
     *
     * @return the singleton instance.
     */
    public static LibraryInputHandler getInstance(){
        if (instance == null) {
            instance = new LibraryInputHandler();
        }
        return instance;
    }

    /**
     * Starts the library input handler, displaying a menu for user interaction.
     */
    public void start() {
        while (!exit) {
            int input = getUserSelection();

            switch (input) {
                case 1:
                    createAndAddBook();
                    break;
                case 2:
                    findBookByISBN();
                    break;
                case 3:
                    findBooksByAuthor();
                    break;
                case 4:
                    findBooksByTitle();
                    break;
                case 5:
                    removeBookByISBN();
                    break;
                case 6:
                    listAllBooks();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    /**
     * Displays the user selection menu and returns the selected option.
     *
     * @return the selected menu
     */
    private int getUserSelection() {
        int input = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("===== Library System =====");
                System.out.println("1 - Create and add new book");
                System.out.println("2 - Find book by ISBN");
                System.out.println("3 - Find books by author");
                System.out.println("4 - Find books by title");
                System.out.println("5 - Remove book by ISBN");
                System.out.println("6 - List all books");
                System.out.println("7 - Exit");
                System.out.print("Enter Selection: ");

                input = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (input >= 1 && input <= 7) {
                    validInput = true;
                } else {
                    System.out.println("Choice out of range, please enter a number between 1 and 7.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number between 1 and 7.\n");
                scanner.next(); // Consume the invalid input to prevent infinite looping
            }
        }
        return input;
    }

    /**
     * Prompts the user to create and add a book to the library.
     */
    private void createAndAddBook() {
        System.out.println("Enter Title: ");
        String title = scanner.nextLine();
        System.out.println("Enter Author: ");
        String author = scanner.nextLine();
        System.out.println("Enter ISBN: ");
        String ISBN = scanner.nextLine();

        while (true) {
            System.out.println("Select Type ('EBook', 'Manuscript'): ");
            String selection = scanner.nextLine().trim();

            if (selection.equalsIgnoreCase("EBook")) {
                createAndAddEbook(author, title, ISBN);
                break;
            } else if (selection.equalsIgnoreCase("Manuscript")) {
                createAndAddManuscript(author, title, ISBN);
                break;
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    /**
     * Prompts the user to enter the details of an ebook and adds it to the library.
     *
     * @param author the author of the ebook.
     * @param title  the title of the ebook.
     * @param ISBN   the ISBN of the ebook.
     */
    private void createAndAddEbook(String author, String title, String ISBN) {
        while (true) {
            System.out.println("Enter size in MB: ");
            try {
                float sizeInMB = Float.parseFloat(scanner.nextLine());
                EBook ebook = new EBook(author, title, ISBN, sizeInMB);
                library.addBook(ebook);
                System.out.println("EBook added.");
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number for size in MB.");
            }
        }
    }

    /**
     * Prompts the user to enter the details of a manuscript and adds it to the library.
     *
     * @param author the author of the manuscript.
     * @param title  the title of the manuscript.
     * @param ISBN   the ISBN of the manuscript.
     */
    private void createAndAddManuscript(String author, String title, String ISBN) {
        System.out.println("Enter any cover type (e.g: Parchment, Vellum, Plain leather, etc.): ");
        String coverType = scanner.nextLine().trim();
        Manuscript manuscript = new Manuscript(author, title, ISBN, coverType);
        library.addBook(manuscript);
        System.out.println("Manuscript added.");
    }

    /**
     * Prompts the user to enter an ISBN and searches for the book in the library.
     */
    private void findBookByISBN() {
        System.out.println("Enter ISBN: ");
        String query = scanner.nextLine();
        Optional<Book> result = library.findBookByISBN(query);

        result.ifPresentOrElse(
                book -> System.out.println("Book found: " + book.getTitle() + ", Author: " + book.getAuthor()),
                () -> System.out.println("Book not found.")
        );
    }

    /**
     * Prompts the user to enter an author and searches for books by the author in the library.
     */
    private void findBooksByAuthor() {
        System.out.println("Enter author: ");
        String query = scanner.nextLine();
        List<Book> result = library.findBooksByAuthor(query);

        if (!result.isEmpty()) {
            System.out.println("Found " + result.size() + " books\n");
            result.forEach(book -> System.out.println("\nTitle: " + book.getTitle() +
                    "\nAuthor: " + book.getAuthor() +
                    "\nISBN: " + book.getISBN()));
        } else {
            System.out.println("Books not found.");
        }
    }

    /**
     * Prompts the user to enter a title and searches for books by the author in the library.
     */
    private void findBooksByTitle() {
        System.out.println("Enter title: ");
        String query = scanner.nextLine();
        List<Book> result = library.findBooksByTitle(query);

        if (!result.isEmpty()) {
            System.out.println("Found " + result.size() + " books\n");
            result.forEach(book -> System.out.println("\nTitle: " + book.getTitle() +
                    "\nAuthor: " + book.getAuthor() +
                    "\nISBN: " + book.getISBN()));
        } else {
            System.out.println("Books not found.");
        }
    }

    /**
     * Prompts the user to enter an ISBN and searches for books by the ISBN then removes it in the library.
     */
    private void removeBookByISBN() {
        System.out.println("Enter ISBN: ");
        String query = scanner.nextLine();
        Optional<Book> result = library.findBookByISBN(query);

        result.ifPresentOrElse(
                book -> {
                    library.removeBookByISBN(query);
                    System.out.println("Book removed.");
                },
                () -> System.out.println("Book not found.")
        );
    }

    /**
     * Displays all the books in the library. If empty, will display a string "Library is empty"
     */
    private void listAllBooks() {
        List<Book> books = library.getBookList();
        if (books.isEmpty()) {
            System.out.println("Library is empty.");
        } else {
            books.forEach(book -> System.out.println("\nTitle: " + book.getTitle() +
                    "\nAuthor: " + book.getAuthor() +
                    "\nType: " + book.getType() +
                    "\nISBN: " + book.getISBN()));
        }
    }
}
