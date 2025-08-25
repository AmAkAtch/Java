package day09_file_io_and_exceptionhandling.basics.BasicsOfFileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CustomerOrderManagement {
    public static void main(String[] args) {
        // Create and save a customer order
        createCustomerOrder();
        
        // Read and display all orders
        displayAllOrders();
    }
    
    public static void createCustomerOrder() {
        try {
            // Create FileWriter in append mode (adds to existing file)
            FileWriter orderWriter = new FileWriter("customer_orders.txt", true);
            
            // Write order details
            orderWriter.write("=== NEW ORDER ===\n");
            orderWriter.write("Order ID: ORD-001\n");
            orderWriter.write("Customer: Jane Doe\n");
            orderWriter.write("Product: Laptop Computer\n");
            orderWriter.write("Quantity: 1\n");
            orderWriter.write("Price: $899.99\n");
            orderWriter.write("Date: 2025-01-15\n");
            orderWriter.write("Status: Pending\n");
            orderWriter.write("==================\n\n");
            
            orderWriter.close();
            System.out.println("Customer order saved successfully!");
            
        } catch (IOException e) {
            System.out.println("Error saving order: " + e.getMessage());
        }
    }
    
    public static void displayAllOrders() {
        try {
            File orderFile = new File("customer_orders.txt");
            
            if (!orderFile.exists()) {
                System.out.println("No orders found. Order file doesn't exist.");
                return;
            }
            
            Scanner fileReader = new Scanner(orderFile);
            System.out.println("\n=== ALL CUSTOMER ORDERS ===");
            
            while (fileReader.hasNextLine()) {
                String orderLine = fileReader.nextLine();
                System.out.println(orderLine);
            }
            
            fileReader.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Error reading orders: " + e.getMessage());
        }
    }
}
