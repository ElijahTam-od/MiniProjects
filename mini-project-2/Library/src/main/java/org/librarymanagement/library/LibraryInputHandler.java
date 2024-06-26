package org.librarymanagement.library;

import org.librarymanagement.book.Book;
import org.librarymanagement.book.EBook;
import org.librarymanagement.book.Manuscript;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LibraryInputHandler {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = Library.getInstance();
    private static LibraryInputHandler instance;
    private boolean exit = false;

    private LibraryInputHandler() {
    }

    public static LibraryInputHandler getInstance(){
        if (instance == null) {
            instance = new LibraryInputHandler();
        }
        return instance;
    }

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

    private void createAndAddManuscript(String author, String title, String ISBN) {
        System.out.println("Enter any cover type (e.g: Parchment, Vellum, Plain leather, etc.): ");
        String coverType = scanner.nextLine().trim();
        Manuscript manuscript = new Manuscript(author, title, ISBN, coverType);
        library.addBook(manuscript);
        System.out.println("Manuscript added.");
    }

    private void findBookByISBN() {
        System.out.println("Enter ISBN: ");
        String query = scanner.nextLine();
        Optional<Book> result = library.findBookByISBN(query);

        result.ifPresentOrElse(
                book -> System.out.println("Book found: " + book.getTitle() + ", Author: " + book.getAuthor()),
                () -> System.out.println("Book not found.")
        );
    }

    private void findBooksByAuthor() {
        System.out.println("Enter author: ");
        String query = scanner.nextLine();
        List<Book> result = library.findBooksByAuthor(query);

        if (!result.isEmpty()) {
            result.forEach(book -> System.out.println("\nTitle: " + book.getTitle() +
                    "\nAuthor: " + book.getAuthor() +
                    "\nISBN: " + book.getISBN()));
        } else {
            System.out.println("Books not found.");
        }
    }

    private void findBooksByTitle() {
        System.out.println("Enter title: ");
        String query = scanner.nextLine();
        List<Book> result = library.findBooksByTitle(query);

        if (!result.isEmpty()) {
            result.forEach(book -> System.out.println("\nTitle: " + book.getTitle() +
                    "\nAuthor: " + book.getAuthor() +
                    "\nISBN: " + book.getISBN()));
        } else {
            System.out.println("Books not found.");
        }
    }

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
