package day17_advanced_collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class BasicHashSet {

    public static void main(String[] args) {

        // Creating Set - Only unique entries allowed
        HashSet<String> studentNames = new HashSet<>();
        // Basic declaration same as arrayList

        studentNames.add("Rushiraj");
        studentNames.add("Bhumik");
        studentNames.add("Rushiraj");

        System.out.println(studentNames);

        // Basic set operations
        if (!studentNames.contains("Rushi")) {
            System.out.println("Rushi is not present, so adding him to set...");
            studentNames.add("Rushi");
        }

        // removing the name
        studentNames.remove("Bhumik");
        studentNames.remove("Madhav"); // trying to remove and entry which does not exist

        // size of the set
        System.out.println(studentNames.size());

        // looping the set
        for (String name : studentNames) {
            System.out.println(name);
        }

        // clear entire set in one command
        studentNames.clear();
        System.out.println(studentNames);

        // KNOWING YOUR OTHER OPTIONS AS LINKED HASHSET - TO MAINTAIN THE INSERTION
        // ORDER
        LinkedHashSet<String> order = new LinkedHashSet<>();

        order.add("first");
        order.add("second");
        order.add("third");
        order.add("first");

        System.out.println("LinkedHashSet of Order: " + order);

        // TREESET TO KEEP THE SET SORTED ALWAYS
        TreeSet<Integer> numbers = new TreeSet<>();

        numbers.add(1465);
        numbers.add(5642);
        numbers.add(5625);
        numbers.add(4555);

        System.out.println("Sorted TreeSet: " + numbers);

    }
}
