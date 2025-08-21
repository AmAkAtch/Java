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

    public static void main(String[] args) {
        int[] numbers = { 1, 5, 6, 2, 20, 21, 44, 2, 33, 45, 232324, 4535343, 35, 45, 425, 32423, 4, 234, 234, 234, 234,
                234, 234, 23, 434, 5635, 645, 67456, 6745, 534, 52, 5, 566234, 23, 456 };
        System.out.println(findMax(numbers));
    }
}
