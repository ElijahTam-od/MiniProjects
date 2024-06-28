package org.elijahtam.od.entities;

import org.elijahtam.od.entities.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The {@code Cart} class represents a shopping cart.
 * It holds a list of products and is associated with a user.
 */
public class Cart {
    /**
     *The owner of the cart
     */
    User user;

    /**
     *The list of products inside the cart
     */
    List<Product> productList;

    /**
     * Constructs a new {@code Cart} for the specified user.
     *
     * @param user the owner of the cart
     */
    public Cart(User user){
        this.user = user;
        this.productList = new ArrayList<>();
    }

    /**
     * Removes a product from the cart.
     *
     * @param product the product to be removed
     */
    public void removeProduct(Product product){
        productList.remove(product);
    }

    public void removeProductById(String cartReferenceId){

        productList.removeIf(product -> product.getCartReferenceId().equals(cartReferenceId));
    }

    /**
     * Adds a product from the cart.
     *
     * @param product the product to be added
     */
    public void addProduct(Product product){
        product.setCartReferenceId(UUID.randomUUID().toString());
        productList.add(product);
    }

    /**
     * Calculates the total price of all products in the cart.
     *
     * @return the total price of all products in the cart
     */
    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Product product : productList) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    /**
     * Returns the owner of the cart.
     *
     * @return the user who owns the cart
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the owner of the cart.
     *
     * @param user the user who will own the cart
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns the list of products in the cart.
     *
     * @return the list of products in the cart
     */
    public List<Product> getProductList() {
        return productList;
    }

    /**
     * Sets the list of products in the cart.
     *
     * @param productList the list of products to be set in the cart
     */
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
