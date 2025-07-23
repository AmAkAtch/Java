package day02_variables_datatypes_operators;

public class OrderCalculator {
    public static void main(String[] args) {
        //declare variables with data trpe
        double itemPrice =  25.50;
        int quantity = 3;
        double taxRate = 0.08;
        String customerName = "Alice";
        boolean isOrderConfirmed = true;
        double discount = 5.00;

        //calculate total cost
        double subTotal = itemPrice * quantity;
        double taxAmount = subTotal *taxRate;
        double totalCost = subTotal + taxAmount - discount;

        //Print Order Details
        System.out.println("Customer: "+customerName);
        System.out.println("Items: "+quantity);
        System.out.println("Sub total: "+subTotal);
        System.out.println("Tax: $"+taxAmount);
        System.out.println("Discount: $"+discount);
        System.out.println("Total: $"+totalCost);
        System.out.println("Order Confirmed: "+isOrderConfirmed);
    }
}

