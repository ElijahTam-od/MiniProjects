package org.elijahtam.od.controller;

import org.elijahtam.od.entities.Shop;
import org.elijahtam.od.entities.product.ElectronicProduct;
import org.elijahtam.od.entities.product.FoodProduct;
import org.elijahtam.od.entities.product.Product;
import org.elijahtam.od.view.ShopView;

import java.time.LocalDate;
import java.util.*;

/**
 * The ShopController class manages the shopping functionality of the application,
 * including adding products to cart, displaying inventory, and handling user actions.
 */
public class ShopController {
    private Shop shop;
    private ShopView shopView;
    private Scanner scanner;
    private ContextMenu contextMenu;

    private UserController userController;
    private CartController cartController;

    /**
     * Constructs a ShopController object with the specified shop, shopView, and contextMenu.
     *
     * @param shop the Shop object representing the store's inventory
     * @param shopView the ShopView object for displaying the shop's inventory
     * @param contextMenu the ContextMenu object for navigating between different views
     */
    public ShopController(Shop shop, ShopView shopView, ContextMenu contextMenu){
        this.shop = shop;
        this.shopView = shopView;
        this.scanner = new Scanner(System.in);
        this.contextMenu = contextMenu;
    }

    /**
     * Starts the shopping process, allowing users to interact with the store's inventory.
     *
     * @param userController the UserController object for managing user-related operations
     * @param cartController the CartController object for managing cart-related operations
     */
    public void startShopping(UserController userController, CartController cartController){
        this.userController = userController;
        this.cartController = cartController;
        loadProductsData();
        while (true) {
            displayProductInventory();
            int action = getUserAction();

            switch (action) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    displayCart();
                    break;
                case 4:
                    exitShop();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Prompts the user to remove a product from the shopping cart.
     */
    private void removeProduct() {
        System.out.println("Enter cart product ID:");
        String productKey = scanner.nextLine();
        boolean removed = cartController.removeProductFromCart(userController.getUser(), productKey);
        if (removed) {
            System.out.println("Product removed from cart.");
        } else {
            System.out.println("Product with ID " + productKey + " does not exist in the cart.");
        }
    }

    /**
     * Prompts the user to add a product to the shopping cart.
     */
    private void addProduct() {
        Product product = getProduct();
        int desiredQuantity = getDesiredQuantity(product);

        // Create a new instance of the specific subclass based on product type
        Product productToAdd = createProductInstance(product, desiredQuantity);

        cartController.addProductToCart(userController.getUser(), productToAdd);
    }

    /**
     * Creates an instance of a specific subclass of Product based on the given product and quantity.
     *
     * @param product the Product object representing the product to be added
     * @param quantity the int value representing the desired quantity of the product
     * @return the Product object with the specified quantity
     */
    private Product createProductInstance(Product product, int quantity) {
        if (product instanceof FoodProduct) {
            FoodProduct foodProduct = (FoodProduct) product;
            return new FoodProduct(foodProduct.getId(), foodProduct.getName(), foodProduct.getDescription(), foodProduct.getPrice(), quantity, foodProduct.getExpirationDate(), foodProduct.getDietaryInfo());
        } else if (product instanceof ElectronicProduct) {
            ElectronicProduct electronicProduct = (ElectronicProduct) product;
            return new ElectronicProduct(electronicProduct.getId(),electronicProduct.getName(), electronicProduct.getDescription(), electronicProduct.getPrice(), quantity, electronicProduct.getBrand(), electronicProduct.getWarrantyPeriod());
        }
        // Handle other subclasses of Product if needed
        return null; // Handle error case appropriately
    }

    /**
     * Displays the user's shopping cart.
     */
    private void displayCart() {
        cartController.displayCart();
        System.out.println("Press Enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Exits the shopping process and returns to the main menu.
     */
    private void exitShop() {
        System.out.println("Returning to main menu...");
        contextMenu.start();
    }

    /**
     * Prompts the user to enter the desired quantity of a product.
     *
     * @param product the Product object representing the selected product
     * @return the int value representing the desired quantity of the product
     */
    private int getDesiredQuantity(Product product) {
        int quantity = 0;
        while (true) {
            try {
                System.out.println("Enter desired quantity for " + product.getName() + ": ");
                quantity = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (quantity > 0 && quantity <= product.getQuantity()) {
                    break;
                } else {
                    System.out.println("Invalid quantity. Please enter a value between 1 and " + product.getQuantity() + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next(); // Consume the invalid input to prevent infinite looping
            }
        }
        return quantity;
    }

    /**
     * Prompts the user to enter the ID of a product and retrieves the corresponding Product object.
     *
     * @return the Product object with the specified ID
     */
    public Product getProduct() {
        Product product = null;
        while (true) {
            System.out.println("Enter product ID:");
            String productKey = scanner.nextLine();
            product = shop.getProductByID(productKey);

            if (product != null) {
                break;
            } else {
                System.out.println("Product does not exist. Please try again.\n");
            }
        }
        return product;
    }

    /**
     * Prompts the user to enter an action and retrieves the selected action.
     *
     * @return the int value representing the selected action
     */
    public int getUserAction(){
        int input = 0;
        while (true) {
            try {
                System.out.println("===== Actions =====");
                System.out.println("1 - Add product to cart");
                System.out.println("2 - Remove product from cart");
                System.out.println("3 - Visit Cart");
                System.out.println("4 - Exit Shop");
                System.out.print("Enter Selection: ");

                input = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (input >= 1 && input <= 4) {
                    break;
                } else {
                    System.out.println("Choice out of range, please enter a number between 1 and 4.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number between 1 and 4.\n");
                scanner.next(); // Consume the invalid input to prevent infinite looping
            }
        }
        return input;
    }

    /**
     * Displays the shop's product inventory.
     */
    public void displayProductInventory(){
        shopView.displayShop(shop);
    }

    /**
     * Loads sample product data into the shop's inventory.
     */
    private void loadProductsData(){
        HashMap<String, Product> productInventory = shop.getProductInventory();

        for( Product product: sampleDataSet()){
            productInventory.put(product.getId(), product);
        }
    }

    /**
     * Creates and returns a list of sample products for testing purposes.
     *
     * @return the List of Product objects representing sample products
     */
    private List<Product> sampleDataSet(){
        List<Product> sampleDataSet = new ArrayList<>();
        sampleDataSet.add(new FoodProduct(
                "Apple",
                "A fresh, juicy apple.",
                0.50,
                100,
                LocalDate.of(2024, 7, 15)
        ));

        sampleDataSet.add(new FoodProduct(
                "Whole Grain Bread",
                "Healthy whole grain bread.",
                2.00,
                50,
                LocalDate.of(2024, 7, 10)
        ));

        sampleDataSet.add(new FoodProduct(
                "Organic Milk",
                "1 liter of organic whole milk.",
                1.50,
                30,
                LocalDate.of(2024, 7, 5)
        ));

        sampleDataSet.add(new FoodProduct(
                "Organic Milk",
                "1 liter of organic whole milk.",
                1.50,
                30,
                LocalDate.of(2024, 7, 5)
        ));

        sampleDataSet.add(new ElectronicProduct(
                "Smartphone",
                "Latest model with 128GB storage.",
                699.99,
                20,
                "TechBrand",
                "2 years"
        ));

        sampleDataSet.add(new ElectronicProduct(
                "Laptop",
                "High-performance laptop with 16GB RAM.",
                999.99,
                15,
                "CompTech",
                "1 year"
        ));

        return sampleDataSet;
    }
}
