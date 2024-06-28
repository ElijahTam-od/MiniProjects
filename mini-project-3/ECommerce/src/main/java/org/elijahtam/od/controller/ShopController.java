package org.elijahtam.od.controller;

import org.elijahtam.od.entities.Shop;
import org.elijahtam.od.entities.product.ElectronicProduct;
import org.elijahtam.od.entities.product.FoodProduct;
import org.elijahtam.od.entities.product.Product;
import org.elijahtam.od.view.ShopView;

import java.time.LocalDate;
import java.util.*;

public class ShopController {
    private Shop shop;
    private ShopView shopView;
    private Scanner scanner;
    private ContextMenu contextMenu;

    private UserController userController;
    private CartController cartController;

    public ShopController(Shop shop, ShopView shopView, ContextMenu contextMenu){
        this.shop = shop;
        this.shopView = shopView;
        this.scanner = new Scanner(System.in);
        this.contextMenu = contextMenu;
    }

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

    private void addProduct() {
        Product product = getProduct();
        int desiredQuantity = getDesiredQuantity(product);

        // Create a new instance of the specific subclass based on product type
        Product productToAdd = createProductInstance(product, desiredQuantity);

        cartController.addProductToCart(userController.getUser(), productToAdd);
    }

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

    private void displayCart() {
        cartController.displayCart();
        System.out.println("Press Enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exitShop() {
        System.out.println("Returning to main menu...");
        contextMenu.start();
    }

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

    public void displayProductInventory(){
        shopView.displayShop(shop);
    }

    private void loadProductsData(){
        HashMap<String, Product> productInventory = shop.getProductInventory();

        for( Product product: sampleDataSet()){
            productInventory.put(product.getId(), product);
        }
    }

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
