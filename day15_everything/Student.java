package day15_everything;

import java.util.HashMap;
import java.util.Map;

class Student {
    private int studentId;
    private String name;
    private String email;
    private String standard;
    private HashMap<String, Integer> grades;

    public Student(int studentId, String name, String email, String standard) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.standard = standard;
        this.grades = new HashMap<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return name;
    }

    public String getStudentEmail() {
        return email;
    }

    public String getStudentStandard() {
        return standard;
    }

    public void addGrade(String subjectName, int grade) {
        /*
         * This method takes in Subject name and Grade.
         * After confirming Grades they are added to hashmap for current student
         */
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Grades can not be Negative or more than 100 for " + subjectName);
        }
        grades.put(subjectName, grade);
    }

    public double getAverageGrades() {
        // This method will first verify if we have grades for student and than
        // calculate total marks and devide it later by map size.
        if (grades.isEmpty()) {
            return 0.0;
        }
        double total = 0;
        for (int grade : grades.values()) {
            total += grade;
        }

        return total / grades.size();
    }

    public void viewStudentDetails() {
        double averageGrades = getAverageGrades();
        System.out.println("Viweing Student details");
        System.out.println("==============================");
        System.out.println("Student Name: " + name);
        System.out.println("Student Email: " + email);
        System.out.println("Student id: " + studentId);
        System.out.println("Average Grades : " + averageGrades);
    }

    public void displayGrades() {
        System.out.println("Show Grades of Student");

        for (Map.Entry<String, Integer> grade : grades.entrySet()) {
            System.out.println(grade.getKey() + " " + grade.getKey());

        }
        System.err.printf("Average Grades: %.2f\n", getAverageGrades());

    }
}
