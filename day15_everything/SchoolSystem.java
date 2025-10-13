package day15_everything;

// SchoolSystem.java

import java.util.ArrayList;

public class SchoolSystem {
    public static void main(String[] args) {
        // STEP 5.1: Initialize system
        StudentManagementSystem sms = new StudentManagementSystem();

        System.out.println("=== School Management System ===\n");

        // STEP 5.2: Test adding students
        System.out.println("--- Adding Students ---");
        Student s1 = new Student(001, "Alice Johnson", "alice@school.com", "");
        Student s2 = new Student(002, "Bob Smith", "bob@school.com", "");
        Student s3 = new Student(003, "Charlie Brown", "charlie@school.com", "");

        sms.addStudent(s1);
        sms.addStudent(s2);
        sms.addStudent(s3);

        // STEP 5.3: Test adding grades
        System.out.println("\n--- Adding Grades ---");
        sms.addGradeToStudent(001, "Math", 95);
        sms.addGradeToStudent(001, "Science", 92);
        sms.addGradeToStudent(001, "English", 88);

        sms.addGradeToStudent(002, "Math", 78);
        sms.addGradeToStudent(002, "Science", 85);

        // STEP 5.4: Test error handling
        System.out.println("\n--- Testing Error Handling ---");
        sms.addGradeToStudent(001, "Physics", 150); // Invalid grade
        sms.addGradeToStudent(999, "Math", 90); // Student not found

        // STEP 5.5: Test display individual student
        System.out.println("\n--- Individual Student Details ---");
        try {
            Student alice = sms.findStudent(001);
            alice.displayGrades();
        } catch (StudentNotFoundException e) {
            System.err.println(e.getMessage());
        }

        // STEP 5.6: Test display all
        sms.displayAllStudents();

        // STEP 5.7: Test report generation
        sms.generateReport();

        // STEP 5.8: Test top performers
        System.out.println("\n--- Top Performers ---");
        ArrayList<Student> topPerformers = sms.getTopPerformers(2);
        for (int i = 0; i < topPerformers.size(); i++) {
            Student student = topPerformers.get(i);
            System.out.printf("%d. %s (Average: %.2f)\n",
                    (i + 1), student.getStudentName(), student.getAverageGrades());
        }
    }
}