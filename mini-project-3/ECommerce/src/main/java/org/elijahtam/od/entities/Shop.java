package org.elijahtam.od.entities;

import org.elijahtam.od.entities.product.Product;

import java.util.HashMap;

/**
 * The {@code Shop} class represents a shop that holds a dictionary of products.
 * It provides methods to manage the inventory of products.
 */
public class Shop {
    HashMap<String, Product> productInventory;

    /**
     * The inventory of products, where each product is associated with a unique key.
     */
    public Shop(){
        productInventory = new HashMap<>();
    }
    /**
     * Constructs a new {@code Shop} with an empty product inventory.
     */
    public Shop(HashMap<String,Product> productInventory){
        this.productInventory = productInventory;
    }

    /**
     * Returns the product inventory.
     *
     * @return the product inventory
     */
    public HashMap<String, Product> getProductInventory() {
        return productInventory;
    }
    /**
     * Sets the product inventory.
     *
     * @param productInventory the product inventory to set
     */
    public void setProductInventory(HashMap<String, Product> productInventory) {
        this.productInventory = productInventory;
    }

    /**
     * Returns the product associated with the specified ID.
     *
     * @param productID the ID of the product to retrieve
     * @return the product associated with the specified ID, or {@code null} if no product is found
     */
    public Product getProductByID(String productID) {
        return productInventory.get(productID);
    }

    /**
     * Adds a product to the inventory with the specified key.
     *
     * @param key the key associated with the product
     * @param product the product to add
     */
    public void addProduct(String key, Product product) {
        productInventory.put(key, product);
    }
}
