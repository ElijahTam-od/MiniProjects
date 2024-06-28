package org.elijahtam.od.entities.product;

import java.time.LocalDate;

/**
 * The {@code FoodProduct} class represents a food item in an e-commerce application.
 * It extends the {@link Product} class and includes additional attributes specific to food items,
 * such as expiration date and dietary information.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * FoodProduct apple = new FoodProduct("F001", "Apple", "Fresh apple", 1.00, 100, LocalDate.of(2023, 12, 31), "Vegan");
 * System.out.println(apple);
 * }</pre>
 *
 * @see Product
 */
public class FoodProduct extends Product{
    /**
     * The expiration date of the food product.
     */
    private LocalDate expirationDate;
    /**
     * The dietary information of the food product.
     */
    private String dietaryInfo;
    /**
     * Constructs a new {@code FoodProduct} with an auto-generated id.
     *
     * @param name the product name
     * @param description the product description
     * @param price the product price
     * @param quantity the product quantity
     * @param expirationDate the expiration date of the food product
     */
    public FoodProduct(String name, String description, double price, int quantity, LocalDate expirationDate) {
        super(name, description, price, quantity);
        this.expirationDate = expirationDate;
    }
    /**
     * Constructs a new {@code FoodProduct} with an auto-generated id and dietary information.
     *
     * @param name the product name
     * @param description the product description
     * @param price the product price
     * @param quantity the product quantity
     * @param expirationDate the expiration date of the food product
     * @param dietaryInfo the dietary information of the food product
     */
    public FoodProduct(String name, String description, double price, int quantity, LocalDate expirationDate, String dietaryInfo) {
        super(name, description, price, quantity);
        this.expirationDate = expirationDate;
        this.dietaryInfo = dietaryInfo;
    }

    /**
     * Constructs a new {@code FoodProduct} with specified id and dietary information.
     *
     * @param id the product ID
     * @param name the product name
     * @param description the product description
     * @param price the product price
     * @param quantity the product quantity
     * @param expirationDate the expiration date of the food product
     * @param dietaryInfo the dietary information of the food product
     */
    public FoodProduct(String id, String name, String description, double price, int quantity, LocalDate expirationDate, String dietaryInfo) {
        super(id, name, description, price, quantity);
        this.expirationDate = expirationDate;
        this.dietaryInfo = dietaryInfo;
    }

    /**
     * Returns the expiration date of the food product.
     *
     * @return the expiration date of the food product
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date of the food product.
     *
     * @param expirationDate the expiration date of the food product
     */
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Returns the dietary information of the food product.
     *
     * @return the dietary information of the food product
     */
    public String getDietaryInfo() {
        return dietaryInfo;
    }

    /**
     * Sets the dietary information of the food product.
     *
     * @param dietaryInfo the dietary information of the food product
     */
    public void setDietaryInfo(String dietaryInfo) {
        this.dietaryInfo = dietaryInfo;
    }

    @Override
    public String toString() {
        return "FoodProduct{" +
                "expirationDate=" + expirationDate +
                ", dietaryInfo='" + dietaryInfo + '\'' +
                ", id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", price=" + getPrice() +
                ", quantity=" + getQuantity() +
                '}';
    }
}
