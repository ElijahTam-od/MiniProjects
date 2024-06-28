package org.elijahtam.od.controller;

import org.elijahtam.od.entities.Shop;
import org.elijahtam.od.view.ShopView;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The {@code ContextMenu} class represents the context menu in the application.
 * It allows the user to choose between shopping, visiting their profile, or exiting the application.
 */
public class ContextMenu {
    UserController userController;
    CartController cartController;
    Scanner scanner = new Scanner(System.in);

    public ContextMenu(UserController userController, CartController cartController){
        this.userController = userController;
        this.cartController = cartController;
    }

    public void start(){
        while (true) {
            int selection = getUserSelection();

            switch (selection) {
                case 1:
                    goShopping();
                    break;
                case 2:
                    visitCart();
                    break;
                case 3:
                    visitProfile();
                    break;
                case 4:
                    exitApplication();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void exitApplication() {
        System.out.println("Exiting program...");
        System.exit(0);
    }

    private void visitProfile() {
        userController.displayUser();
    }

    private void visitCart() {
        userController.displayUser();
    }

    private void goShopping() {
        // Create shop for shopping
        Shop shop = new Shop();
        ShopView shopView = new ShopView();
        ShopController shopController = new ShopController(shop, shopView, this);
        shopController.startShopping(userController, cartController);
    }

    private int getUserSelection() {
        int input = 0;

        while (true) {
            try {
                System.out.println("===== Main Menu =====");
                System.out.println("1 - Go shopping");
                System.out.println("2 - Visit Profile");
                System.out.println("3 - Exit");
                System.out.print("Enter Selection: ");

                input = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (input >= 1 && input <= 3) {
                    break;
                } else {
                    System.out.println("Choice out of range, please enter a number between 1 and 3.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number between 1 and 3.\n");
                scanner.next(); // Consume the invalid input to prevent infinite looping
            }
        }
        return input;
    }
}
