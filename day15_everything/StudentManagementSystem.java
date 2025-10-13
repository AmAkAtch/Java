package day15_everything;

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
}
