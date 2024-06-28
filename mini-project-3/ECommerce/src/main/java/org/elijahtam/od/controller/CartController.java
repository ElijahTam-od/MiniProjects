package org.elijahtam.od.controller;

import org.elijahtam.od.entities.Cart;
import org.elijahtam.od.entities.User;
import org.elijahtam.od.entities.product.Product;
import org.elijahtam.od.view.CartView;

import java.util.List;

/**
 * The CartController class manages operations related to the shopping cart,
 * such as displaying the cart contents, adding products to the cart, and removing products from the cart.
 */
public class CartController {
    private Cart cart;
    private CartView cartView;

    /**
     * Constructs a CartController object with the specified Cart and CartView.
     *
     * @param cart the Cart object representing the user's shopping cart
     * @param cartView the CartView object for displaying the cart's contents
     */
    public CartController(Cart cart, CartView cartView) {
        this.cart = cart;
        this.cartView = cartView;
    }

    /**
     * Displays the contents of the user's shopping cart using the associated CartView.
     */
    public void displayCart() {
        cartView.displayView(cart);
    }

    /**
     * Adds the specified product to the user's shopping cart.
     *
     * @param user the User object representing the user
     * @param product the Product object to be added to the cart
     */
    public void addProductToCart(User user, Product product){
        user.getCart().addProduct(product);
    }

    /**
     * Removes a product from the user's shopping cart based on the cartReferenceId.
     *
     * @param user the User object representing the user
     * @param cartReferenceId the String representing the unique identifier of the product in the cart
     * @return true if the product was successfully removed, false otherwise
     */
    public boolean removeProductFromCart(User user, String cartReferenceId) {
        List<Product> productList = user.getCart().getProductList();

        // Find the product with the specified cartReferenceId in the cart
        for (Product product : productList) {
            if (cartReferenceId.equals(product.getCartReferenceId())) {
                productList.remove(product); // Remove the product from the cart
                return true; // Return true indicating successful removal
            }
        }

        // Return false if no product with the given cartReferenceId was found
        return false;
    }
}
