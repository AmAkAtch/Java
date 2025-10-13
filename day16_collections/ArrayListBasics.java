package day16_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ArrayListBasics {

    public static void main(String[] args) {

        // <> are called generics and this is how you tell type of the arraylist
        ArrayList<String> names = new ArrayList<>();

        // This is how you add objects or items in array list
        names.add("Rushiraj");
        names.add("Bhumik");

        // This is how you print array list
        System.out.println(names);

        // this is how you access specific index
        String firstName = names.get(0);
        System.out.println(firstName);

        // this is how you check the size of array list
        System.out.println("Array list size: " + names.size());

        // remove specific item on specific index
        names.remove(1);
        System.out.println(names + "and new array size - " + names.size());

        // checking if item exists in array list
        if (names.contains("Bhumik")) {
            System.out.println("Bhumik exists...");
        } else {
            names.add("Bhumik");
            System.out.println("Bhumik didnt exist so we added him : " + names);
        }

        // This is how you loop on array list
        for (String name : names) {
            System.out.println(name);
        }

        // iterator method to use when you want to remove elements from the array list
        names.add("Rushi");
        Iterator<String> iterator = names.iterator();
        System.out.println(names);
        while (iterator.hasNext()) {
            String name = iterator.next();
            System.out.println(name);
            if (name.equals("Bhumik")) {
                iterator.remove();
                System.out.println("Safely removed Bhumik while looping");
            }
        }
        System.out.println(names);

        // helper methods in collection
        // Sorting
        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(85);
        scores.add(92);
        scores.add(78);
        scores.add(95);

        Collections.sort(scores); // Sorts in ascending order
        System.out.println(scores);

        // reversing the order
        Collections.reverse(scores);
        System.out.println(scores);

        // finding max and minimum
        System.out.println(Collections.max(scores));
        System.out.println(Collections.min(scores));

        // Shuffeling
        Collections.shuffle(scores);
        System.out.println(scores);

    }

}
