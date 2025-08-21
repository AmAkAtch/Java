package day06_array_basics.basics;

public class ArrayBasics {

    // Function to find the max number from array
    public static int findMax(int[] numbers) {
        int maxNum = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (maxNum < numbers[i])
                maxNum = numbers[i];
        }
        return maxNum;
    }

    // function to find average
    public static double findAverage(int[] numbers) {
        double totalSum = 0;
        for (int num : numbers) {
            totalSum += num;
        }

        return (totalSum / numbers.length);
    }

    // function to find the index of specific value
    public static int findValue(int[] numbers, int value) {
        if (numbers.length == 0)
            return 0;
        for (int i = 0; i < numbers.length; i++) {
            if (value == numbers[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] numbers = { 1, 5, 6, 2, 20, 21, 44, 2, 33, 45, 232324, 4535343, 35, 45, 425, 32423, 4, 234, 234, 234, 234,
                234, 234, 23, 434, 5635, 645, 67456, 6745, 534, 52, 5, 566234, 23, 456 };
        System.out.println("Max number :" + findMax(numbers));
        System.out.println("Average :" + findAverage(numbers));
        System.out.println("Index of Number 33 in array :" + findValue(numbers, 33));
    }
}
