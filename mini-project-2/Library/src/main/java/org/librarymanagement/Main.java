package org.librarymanagement;

import org.librarymanagement.library.LibraryInputHandler;

public class Main {

    public static void main(String[] args) {
        //Description: Develop a basic library management system that allows users to add, remove, and search for books.

        //Tasks
        //Create a Book class with attributes like title, author, and ISBN.
        //Implement methods for adding, removing, and searching books.
        //Use a list to store book objects.
        //Implement basic OOP principles: encapsulation, inheritance, and polymorphism.


        //This program assumes that there will only be 1 library for the entire library management system
        LibraryInputHandler inputHandler = LibraryInputHandler.getInstance();
        inputHandler.start();
    }
}