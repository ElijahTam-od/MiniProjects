package org.elijahtam.od.entities.product;
/**
 * The {@code ElectronicProduct} class represents an electronic item in an e-commerce application.
 * It extends the {@link Product} class and includes additional attributes specific to electronic items,
 * such as brand and warranty period.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * ElectronicProduct laptop = new ElectronicProduct("E001", "Laptop", "High-end gaming laptop", 1500.00, 10, "BrandX", "24 months");
 * System.out.println(laptop);
 * }</pre>
 *
 * @see Product
 */
public class ElectronicProduct extends Product{
    /**
     * The brand of the product.
     */
    private String brand;
    /**
     * The warranty period of the product.
     */
    private String warrantyPeriod;

    /**
     * Constructs a new {@code ElectronicProduct}.
     *
     * @param name the product name
     * @param description the product description
     * @param price the product price
     * @param quantity the product quantity
     */
    public ElectronicProduct(String name, String description, double price, int quantity) {
        super(name, description, price, quantity);
    }

    /**
     * Constructs a new {@code ElectronicProduct} with brand, and warranty period.
     *
     * @param name the product name
     * @param description the product description
     * @param price the product price
     * @param quantity the product quantity
     * @param brand the brand of the product
     * @param warrantyPeriod the warranty period of the product
     */
    public ElectronicProduct(String name, String description, double price, int quantity, String brand, String warrantyPeriod) {
        super(name, description, price, quantity);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    /**
     * Constructs a new {@code ElectronicProduct} with specified id, brand, and warranty period.
     *
     * @param name the product name
     * @param description the product description
     * @param price the product price
     * @param quantity the product quantity
     * @param brand the brand of the product
     * @param warrantyPeriod the warranty period of the product
     */
    public ElectronicProduct(String id,String name, String description, double price, int quantity, String brand, String warrantyPeriod) {
        super(id, name, description, price, quantity);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    /**
     * Returns the brand of the electronic product.
     *
     * @return the brand of the electronic product.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand of the product.
     *
     * @param brand the brand of the product
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Returns the warranty period of the product.
     *
     * @return the warranty period of the product
     */
    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    /**
     * Sets the warranty period of the product.
     *
     * @param warrantyPeriod the warranty period of the product
     */
    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}
