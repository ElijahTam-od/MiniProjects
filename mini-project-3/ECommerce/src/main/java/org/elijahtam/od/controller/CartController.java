package org.elijahtam.od.controller;

import org.elijahtam.od.entities.Cart;
import org.elijahtam.od.entities.User;
import org.elijahtam.od.entities.product.Product;
import org.elijahtam.od.view.CartView;

import java.util.List;

public class CartController {
    private Cart cart;
    private CartView cartView;

    public CartController(Cart cart, CartView cartView) {
        this.cart = cart;
        this.cartView = cartView;
    }

    public void displayCart() {
        cartView.displayView(cart);
    }

    public void addProductToCart(User user, Product product){
        user.getCart().addProduct(product);
    }

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
