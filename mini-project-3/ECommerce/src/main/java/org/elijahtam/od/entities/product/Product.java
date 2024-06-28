package org.elijahtam.od.entities.product;

import java.util.UUID;

/**
 * The {@code Product} class represents a generic product in an e-commerce application.
 * It serves as an abstract base class for specific types of products, encapsulating common attributes
 * such as ID, name, description, price, and quantity.
 *
 *
 * <p>Example subclass usage:</p>
 * <pre>{@code
 * public class ElectronicProduct extends Product {
 *     private String brand;
 *     private int warrantyPeriod;
 *
 *     public ElectronicProduct(String id, String name, String description, double price, int quantity, String brand, int warrantyPeriod) {
 *         super(id, name, description, price, quantity);
 *         this.brand = brand;
 *         this.warrantyPeriod = warrantyPeriod;
 *     }
 * }
 * }</pre>
 */
public abstract class Product {
    /**
     * The unique identifier for the product.
     */
    private String id;
    /**
     * The unique identifier for the product inside the cart.
     */
    private String cartReferenceId;

    /**
     * The name of the product.
     */
    private String name;

    /**
     * A brief description of the product.
     */
    private String description;

    /**
     * The price of the product.
     */
    private double price;

    /**
     * The available quantity of the product in stock.
     */
    private int quantity;

    /**
     * Constructs a new {@code Product} with an auto-generated ID.
     *
     * @param name the name of the product
     * @param description a brief description of the product
     * @param price the price of the product
     * @param quantity the available quantity of the product in stock
     */
    public Product(String name, String description, double price, int quantity) {
        this.id =  UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Constructs a new {@code Product} with a specified ID and the specified details.
     *
     * @param id          the ID of the product
     * @param name        the name of the product
     * @param description a brief description of the product
     * @param price       the price of the product
     * @param quantity    the available quantity of the product in stock
     */
    public Product(String id, String name, String description, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns the unique identifier for the product.
     *
     * @return the unique identifier for the product
     */
    public String getId() {
        return id;
    }

    public void setCartReferenceId(String cartReferenceId){
        this.cartReferenceId = cartReferenceId;
    }

    public String getCartReferenceId(){
        return cartReferenceId;
    }

    /**
     * Returns the name of the product.
     *
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name the name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a brief description of the product.
     *
     * @return a brief description of the product
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets a brief description of the product.
     *
     * @param description a brief description of the product
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the price of the product.
     *
     * @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price the price of the product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the available quantity of the product in stock.
     *
     * @return the available quantity of the product in stock
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the available quantity of the product in stock.
     *
     * @param quantity the available quantity of the product in stock
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
