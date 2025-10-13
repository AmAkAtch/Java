package day15_everything;

import java.util.ArrayList;
import java.util.HashMap;

class StudentManagementSystem {

    private HashMap<Integer, Student> students;

    public StudentManagementSystem(Student student) {
        this.students = new HashMap<>();
    }

    public void addStudents(Student student) {
        // This method gets the studentid and uses that as a key to store student in
        // Student map
        students.put(student.getStudentId(), student);
        System.out.println("Student " + student.getStudentName() + " Added successfully");
    }

    // method to find student with studentId
    public Student findStudent(int studentId) throws StudentNotFoundException {
        /*
         * This method gets the student from hashmap using get method and the studentId
         * as the key, if student stays null throw an error or return the found student
         * object
         */
        Student student = students.get(studentId);

        if (student == null) {
            throw new StudentNotFoundException("Student with Student Id: " + studentId + "Not found");
        }

        return student;
    }

    // This function to add grade to students
    public void addGradeToStudent(int studentId, String subject, int grade) {
        /* This function uses the find student method and than add grade and println */
        try {
            Student student = findStudent(studentId);

            student.addGrade(subject, grade);

            System.out.println("Added grades for student: " + student.getStudentName());

        } catch (StudentNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void displayAllstudents() {
        System.out.println("All Students");
        System.out.println("Total Student: " + students.size());
        System.out.println("=======================");

        for (Student student : students.values()) {
            student.viewStudentDetails();
        }
    }

    // Method to find the top N best performing students
    public ArrayList<Student> findBestPerformingStudents(int count) {
        /*
         * This method makes new array lists consisting values from Student map
         * using bubble sort entire arra list is sorted
         * finally, count or size of the array list whichever is minimum we return that
         * many top students
         */
        System.out.println("Best Performing Studens");
        ArrayList<Student> allStudents = new ArrayList<>(students.values());

        for (int i = 0; i < allStudents.size() - 1; i++) {
            for (int j = 0; j < allStudents.size() - 1 - i; j++) {
                if (allStudents.get(j).getAverageGrades() < allStudents.get(j + 1).getAverageGrades()) {
                    Student temp = allStudents.get(j);
                    allStudents.set(j, allStudents.get(j + 1));
                    allStudents.set(j + 1, temp);
                }
            }
        }

        ArrayList<Student> bestPerformingStudents = new ArrayList<>();
        for (int i = 0; i < Math.min(count, allStudents.size()); i++) {
            bestPerformingStudents.add(allStudents.get(i));
        }

        return bestPerformingStudents;
    }

    public void generateReport() {
        System.out.println("Class report card");
        System.out.println("==========================");

        if (students.isEmpty()) {
            System.out.println("No student data available, Report generation cancel");
            return;
        }

        double totalAverage = 0;
        Student bestPerofrmingStudent = null;
        double highestAverage = -1;

        for (Student student : students.values()) {

            double average = student.getAverageGrades();
            totalAverage += average;
            if (student.getAverageGrades() > highestAverage) {
                highestAverage = average;
                bestPerofrmingStudent = student;
            }
        }

        System.out.println("Highest Performing student: " + bestPerofrmingStudent.getStudentName());
        System.out.println("Highest Average grades of class : " + highestAverage);
        System.out.println("Class total average: " + totalAverage / students.size());
    }
}
