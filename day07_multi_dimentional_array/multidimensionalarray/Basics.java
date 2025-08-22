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
    }
}
