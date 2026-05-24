package RFT_Internship_Day_10;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class Product {
    String name;
    double price;
    int quantity;
    Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    void display() {
        System.out.println("---------------------------");
        System.out.println("Product Name : " + name);
        System.out.println("Price        : " + price);
        System.out.println("Quantity     : " + quantity);
    }
}

public class InventoryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // HashMap
        HashMap<String, Product> inventory = new HashMap<>();
        int choice;
        do {
            System.out.println("\n...........INVENTORY MANAGEMENT SYSTEM.............");
            System.out.println("1. Add Product");
            System.out.println("2. Update Quantity");
            System.out.println("3. Search Product");
            System.out.println("4. Calculate Total Inventory Value");
            System.out.println("5. Remove Product");
            System.out.println("6. Low Stock Alert");
            System.out.println("7. Display All Products");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Product Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter Product Quantity: ");
                    int quantity = sc.nextInt();

                    Product p = new Product(name, price, quantity);
                    inventory.put(name, p);
                    System.out.println("Product Added Successfully!");
                    break;

                case 2:     // Update Quantity
                    System.out.print("Enter Product Name: ");
                    String updateName = sc.nextLine();

                    if (inventory.containsKey(updateName)) {
                        System.out.print("Enter New Quantity: ");
                        int newQty = sc.nextInt();

                        inventory.get(updateName).quantity = newQty;

                        System.out.println("Quantity Updated!");
                    } else {
                        System.out.println("Product Not Found!");
                    }
                    break;

                case 3:     // Search Product
                    System.out.print("Enter Product Name: ");
                    String searchName = sc.nextLine();

                    if (inventory.containsKey(searchName)) {
                        inventory.get(searchName).display();
                    } else {
                        System.out.println("Product Not Found!");
                    }
                    break;

                case 4:        // Calculate Total Inventory Value
                    double totalValue = 0;

                    for (Map.Entry<String, Product> entry : inventory.entrySet()) {

                        Product product = entry.getValue();

                        totalValue += product.price * product.quantity;
                    }

                    System.out.println("Total Inventory Value = " + totalValue);
                    break;

                case 5:      // Remove Product
                    System.out.print("Enter Product Name: ");
                    String removeName = sc.nextLine();

                    if (inventory.containsKey(removeName)) {
                        inventory.remove(removeName);
                        System.out.println("Product Removed!");
                    } else {
                        System.out.println("Product Not Found!");
                    }
                    break;


                case 6:     // Low Stock Alert
                    System.out.println("\nLow Stock Products (Quantity < 5)");
                    boolean found = false;

                    for (Product product : inventory.values()) {
                        if (product.quantity < 5) {
                            product.display();
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("No Low Stock Products!");
                    }
                    break;

                case 7:     // Display All Products
                    if (inventory.isEmpty()) {
                        System.out.println("Inventory is Empty!");
                    } else {
                        System.out.println("\nAll Products:");

                        for (Product product : inventory.values()) {
                            product.display();
                        }
                    }
                    break;

                // Exit
                case 0:
                    System.out.println("Exiting Program...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }
        } while (choice != 0);
        sc.close();
    }
}
