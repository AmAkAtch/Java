package day17_advanced_collections;

import java.util.HashMap;

import day10_classes.study_programe.Employee;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class BasicHashMap {

    public static void main(String[] args) {

        HashMap<String, Integer> employees = new HashMap<>();

        employees.put("Ramesh", 50);
        employees.put("Suresh", 56);
        employees.put("Kartik", 23);

        System.out.println(employees);

        // BASIC HASHMAP OPERATIONS
        // get value by key
        int ageOfRamesh = employees.get("Ramesh");
        System.out.println("Age of Ramesh: " + ageOfRamesh);

        // check if key exists
        if (employees.containsKey("Ramesha")) {
            System.out.println("Employees contain Ramesh");
        } else {
            System.out.println("Nope...");
        }

        // check if value exists
        if (employees.containsValue(50)) {
            System.out.println("Yes someone aged 50 exists in the database system");
        } else {
            System.out.println("No body is aged 50 in organization");
        }

        // get value or default if key doesnt exist
        int ageOfKanti = employees.getOrDefault("Kanti", 20);
        System.out.println("Kanti is aged: " + ageOfKanti);

        // remove by key
        employees.remove("Ramesh");
        System.out.println("Ramesh Retired");
        System.out.println(employees);

        // get size
        System.out.println(employees.size());

        // LOOPING THROUGH HASHMAP
        // 1. looping via key
        System.out.println("Name of Employees");
        for (String name : employees.keySet()) {
            System.out.println(name);
        }

        // 2. Looping via values
        System.out.println("Ages of Employees");
        for (int age : employees.values()) {
            System.out.println(age);
        }

        // 3. Looping via entire set
        System.out.println("Every Employee with their ages");
        for (HashMap.Entry<String, Integer> entry : employees.entrySet()) {
            System.out.println(entry.getKey() + " is aged " + entry.getValue());
        }

        // OTHER HASHMAP OPTIONS
        // TREEMAP TO SORT THE ITEMS VIA KEY
        TreeMap<Integer, String> students = new TreeMap<>();

        students.put(16051, "Rushiraj");
        students.put(16003, "Rushi");
        students.put(16003, "Bhumik");
        students.put(16017, "Rushi");

        System.out.println("Students sorted via their Key: " + students);

        // LinkedHashMap to store items in order
        LinkedHashMap<String, Integer> grades = new LinkedHashMap<>();

        grades.put("Rushi", 20);
        grades.put("Bhumik", 11);
        grades.put("Rushiraj", 20);

        System.out.println("Grades per student in order: " + grades);

    }
}
