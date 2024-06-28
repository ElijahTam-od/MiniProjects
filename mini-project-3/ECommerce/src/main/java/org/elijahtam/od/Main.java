package org.elijahtam.od;

import org.elijahtam.od.entities.Cart;
import org.elijahtam.od.entities.Shop;
import org.elijahtam.od.entities.User;
import org.elijahtam.od.controller.CartController;
import org.elijahtam.od.controller.ContextMenu;
import org.elijahtam.od.controller.ShopController;
import org.elijahtam.od.controller.UserController;
import org.elijahtam.od.view.CartView;
import org.elijahtam.od.view.ShopView;
import org.elijahtam.od.view.UserView;

public class Main {
    /** The UserController instance for managing user-related operations. */
    static UserController userController;

    /** The CartController instance for managing cart-related operations. */
    static CartController cartController;

    public static void main(String[] args) {
        // Setup and initialize parameters for contextMenu
        setUpComponents();

        // Create context menu to let user choose activities
        ContextMenu contextMenu = new ContextMenu(userController, cartController);
        contextMenu.start();
    }

    /**
     * Sets up necessary components for the application:
     * - Creates a shop and its associated view.
     * - Creates a user and its associated view.
     * - Creates a cart and its associated view.
     */
    private static void setUpComponents() {
        // Create new user for demo
        User user = new User("placeholder");
        UserView userView = new UserView();
        userController = new UserController(user, userView);

        // Create a cart for user
        Cart cart = userController.getUser().getCart();
        CartView cartView = new CartView();
        cartController = new CartController(cart, cartView);
    }
}