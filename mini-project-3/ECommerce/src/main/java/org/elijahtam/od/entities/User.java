package org.elijahtam.od.entities;
/**
 * The {@code User} class represents a user in the e-commerce application.
 * Each user has a cart to store products they wish to purchase.
 */
public class User {
    /**
     * The name of the user
     */
    private String username;

    /**
     * The cart of the user. Can add and remove products inside the cart
     */
    private Cart cart;

    /**
     * Constructs a new {@code User} with the specified username and an empty cart.
     *
     * @param username the username of the user
     */
    public User(String username) {
        this.username = username;
        this.cart = new Cart(this);
    }

    /**
     * Returns the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the cart of the user.
     *
     * @return the cart of the user
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * Sets the cart of the user.
     *
     * @param cart the cart of the user
     */
    public void setCart(Cart cart) {
        this.cart = cart;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", cart=" + cart +
                '}';
    }
}
