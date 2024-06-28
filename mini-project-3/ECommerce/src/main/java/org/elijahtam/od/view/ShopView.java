package org.elijahtam.od.view;

import org.elijahtam.od.entities.Shop;
import org.elijahtam.od.entities.product.Product;

import java.util.HashMap;
import java.util.List;

public class ShopView {
    public void displayShop(Shop shop){
        System.out.println("===== Shop Inventory =====");
        HashMap<String, Product> productInventory = shop.getProductInventory();
        if (productInventory.isEmpty()) {
            System.out.println("The shop inventory is currently empty.");
        } else {
            for (Product product : productInventory.values()) {
                System.out.println("Product ID: " + product.getId());
                System.out.println("Name: " + product.getName());
                System.out.println("Description: " + product.getDescription());
                System.out.println("Price: $" + product.getPrice());
                System.out.println("Quantity: " + product.getQuantity());
                System.out.println("------------------------");
            }
        }
    }
}
