package org.elijahtam.od.view;

import org.elijahtam.od.entities.Cart;
import org.elijahtam.od.entities.product.Product;

public class CartView {
    public void displayView(Cart cart){
        System.out.println("===== Cart Contents =====");
        if (cart.getProductList().isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            for (Product product : cart.getProductList()) {
                System.out.println("Original Product ID: " + product.getId());
                System.out.println("Cart Product ID: " + product.getCartReferenceId());
                System.out.println("Name: " + product.getName());
                System.out.println("Description: " + product.getDescription());
                System.out.println("Price: $" + product.getPrice());
                System.out.println("Quantity: " + product.getQuantity());
                System.out.println("------------------------");
            }
        }
    }
}
