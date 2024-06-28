package org.elijahtam.od.view;

import org.elijahtam.od.entities.User;
import org.elijahtam.od.entities.product.Product;

import java.util.List;

/**
 * The {@code UserView} class represents the view for the user.
 */
public class UserView {
    public void displayUser(User user) {
        System.out.println("User: " + user.getUsername());
        System.out.println("Cart Contents:");
        List<Product> products = user.getCart().getProductList();
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("Total Price: " + user.getCart().getTotalPrice());
    }
}
