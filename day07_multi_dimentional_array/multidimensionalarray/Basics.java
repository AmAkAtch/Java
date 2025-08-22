package day07_multi_dimentional_array.multidimensionalarray;

import java.util.Arrays;

public class Basics {
    public static void main(String[] args) {

        // Array with 2 rows and 3 columns
        String[][] array2d = new String[2][3];

        /*
         * They are not flat memory, every row can have their own array stored somewhere
         * else in memory
         */

        array2d[0] = new String[] { "Youtube", "video", "Editing" };
        array2d[1] = new String[] { "Coding", "Java", "React" };

        // How to print arrays
        System.out.println(Arrays.toString(array2d[0]));
        System.out.println(Arrays.deepToString(array2d));

        // Declaring and initializing at the same itme
        int[][] scores = { { 1, 30 }, { 2, 35 }, { 3, 25 }, { 4, 56 } };
        System.out.println(Arrays.deepToString(scores));
        System.out.println(scores.length); // This will show numbers of rows in multi dimentional arrays

        /* COPY AND COMPARE MULTI DIMENSIONAL ARRAYS */
        int[][] original = { { 1, 30 }, { 2, 35 }, { 3, 25 }, { 4, 56 } };
        int[][] copy = deepCopy(original);

        // compare the arrays before copying
        System.out.println(Arrays.deepEquals(original, copy));

        // Existing sales 2d array and add new row in it
        String[][] sales = { { "laptop", "5" }, { "iphone", "2" } };
        String[] newRow = { "Samsung", "7" };

        System.out.println("Oringal Sales");
        System.out.println(Arrays.deepToString(sales));

        System.out.println("After adding one row");

        sales = addRow(sales, newRow);
        System.out.println(Arrays.deepToString(sales));

    }

    // method to copy one multi dim array to another
    public static int[][] deepCopy(int[][] original) {

        // first create empty amulti dim array that matches the rows of original array
        int[][] copy = new int[original.length][];
        if (original.length != 0) {
            for (int row = 0; row < original.length; row++) {

                // for every row init a new array that matches the length of row's array
                // (columns)
                copy[row] = new int[original[row].length];
                for (int col = 0; col < original[row].length; col++) {
                    copy[row][col] = original[row][col];
                    System.out.print(copy[row][col] + " ");
                }
                System.out.println();
            }
        }

        return copy;
    }

    // method to add new row in array
    public static String[][] addRow(String[][] sales, String[] newRow) {

        String[][] newSales = new String[sales.length + 1][]; // Create array with 1 extra row
        for (int row = 0; row < sales.length; row++) { // iterate over each row
            newSales[row] = new String[sales[row].length]; // create array for row representing columns
            newSales[row] = sales[row]; // copy arrays
        }
        newSales[sales.length] = newRow; // after exiting loop cpoy the new row in last row

        return newSales;
    }
}
