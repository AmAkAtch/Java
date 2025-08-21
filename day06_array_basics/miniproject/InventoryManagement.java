package day06_array_basics.miniproject;

public class InventoryManagement {

    static String[] items = new String[10];
    static int[] quantity = new int[10];
    static int arrayIndexLocation = 0;

    public static void addItem(String itemName, int number) {
        if (arrayIndexLocation < items.length) {
            items[arrayIndexLocation] = itemName;
            quantity[arrayIndexLocation] = number;
            arrayIndexLocation++;
        } else {
            System.out.println("Array full Cant add more items");
        }
    }

    public static void displayInventory() {
        for (int i = 0; i < items.length; i++) {
            System.out.println(i + 1 + " " + items[i] + " " + quantity[i]);
        }
    }

    public static void findQuantity(String itemName) {
        if (items.length > 0) {
            for (int i = 0; i < items.length; i++) {
                if (itemName.equals(items[i])) {
                    System.out.println("Stock of " + items[i] + " is " + quantity[i]);
                }
            }
        }
    }

    public static void main(String[] args) {
        addItem("Laptop", 100);
        addItem("iPhone", 20);
        displayInventory();
        findQuantity("iPhone");
    }
}
