import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Student {
    private String name;
    private int id;
    private Map<String, Double> grades;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.grades = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Map<String, Double> getGrades() {
        return grades;
    }

    public void addGrade(String course, double grade) {
        grades.put(course, grade);
    }

    public double calculateGPA() {
        if (grades.isEmpty()) {
            return 0.0;
        }

        double totalGradePoints = 0.0;
        int totalCredits = 0;

        for (Map.Entry<String, Double> entry : grades.entrySet()) {
            // Assuming each course has a credit value of 3
            int credits = 3;
            totalGradePoints += entry.getValue() * credits;
            totalCredits += credits;
        }

        return totalGradePoints / totalCredits;
    }
}

public class StudentManagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Record Grades");
            System.out.println("4. Calculate GPA");
            System.out.println("5. Generate Reports");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    removeStudent(scanner);
                    break;
                case 3:
                    recordGrades(scanner);
                    break;
                case 4:
                    calculateGPA(scanner);
                    break;
                case 5:
                    generateReports();
                    break;
                case 6:
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.next();
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();

        Student student = new Student(name, id);
        students.add(student);
        System.out.println("Student added successfully!");
    }

    private static void removeStudent(Scanner scanner) {
        System.out.print("Enter student ID to remove: ");
        int id = scanner.nextInt();

        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                System.out.println("Student removed successfully!");
                return;
            }
        }

        System.out.println("Student not found with ID " + id);
    }

    private static void recordGrades(Scanner scanner) {
        System.out.print("Enter student ID to record grades: ");
        int id = scanner.nextInt();

        for (Student student : students) {
            if (student.getId() == id) {
                System.out.print("Enter course name: ");
                String course = scanner.next();
                System.out.print("Enter grade for " + course + ": ");
                double grade = scanner.nextDouble();

                student.addGrade(course, grade);
                System.out.println("Grade recorded successfully!");
                return;
            }
        }

        System.out.println("Student not found with ID " + id);
    }

    private static void calculateGPA(Scanner scanner) {
        System.out.print("Enter student ID to calculate GPA: ");
        int id = scanner.nextInt();

        for (Student student : students) {
            if (student.getId() == id) {
                double gpa = student.calculateGPA();
                System.out.println("GPA for student " + student.getName() + " (ID: " + student.getId() + "): " + gpa);
                return;
            }
        }

        System.out.println("Student not found with ID " + id);
    }

    private static void generateReports() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }

        System.out.println("Student Reports:");
        for (Student student : students) {
            System.out.println("Name: " + student.getName() + ", ID: " + student.getId());
            System.out.println("Grades: " + student.getGrades());
            System.out.println("GPA: " + student.calculateGPA());
            System.out.println("-------------");
        }
    }
}
