package org.example;

import java.util.List;
import java.util.Scanner;

public class mainclass {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManager manager = new StudentManager();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            displayMenu();

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> registerStudent();
                case "2" -> searchByIdMenu();
                case "3" -> searchByNameMenu();
                case "4" -> displayStudents();
                case "5" -> sortStudents();
                case "6" -> updateStudent();
                case "7" -> removeStudent();
                case "8" -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println(
                        "Invalid option. Please try again."
                );
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n=== STUDENT RECORD SYSTEM ===");
        System.out.println("1. Register Student");
        System.out.println("2. Search Student by ID");
        System.out.println("3. Search Student by Name");
        System.out.println("4. Display All Students");
        System.out.println("5. Sort Students");
        System.out.println("6. Update Student");
        System.out.println("7. Remove Student");
        System.out.println("8. Exit");
        System.out.print("Choose an option: ");
    }

    private static void registerStudent() {
        try {
            System.out.print("Enter student ID (ST001): ");
            String id = scanner.nextLine().trim();

            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            System.out.print("Enter student age: ");
            int age = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter class: ");
            String studentClass = scanner.nextLine();

            System.out.print("Enter subjects: ");
            String subjects = scanner.nextLine();

            Students student = new Students(
                    id, name, age, studentClass, subjects
            );

            if (manager.registerStudent(student)) {
                System.out.println(
                        "Student registered successfully."
                );
            } else {
                System.out.println(
                        "Registration failed. The ID may already exist."
                );
            }

        } catch (NumberFormatException e) {
            System.out.println("Age must be a valid integer.");
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Registration failed: " + e.getMessage()
            );
        }
    }

    private static void searchByIdMenu() {
        boolean searching = true;

        while (searching) {
            System.out.println("\n=== SEARCH STUDENT BY ID ===");
            System.out.print("Enter student ID: ");

            String id = scanner.nextLine().trim();

            Students student = manager.searchById(id);

            if (!id.matches("ST\\d{3}")) {
                System.out.println("Invalid ID format.");
            } else if (student == null) {
                System.out.println(
                        "No student found with ID " + id
                );
            } else {
                System.out.println("Student found:");
                System.out.println(student);
            }

            System.out.println("\n1. Search Again");
            System.out.println("2. Return to Main Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> searching = true;
                case "2" -> searching = false;
                default -> {
                    System.out.println(
                            "Invalid option. Returning to Main Menu."
                    );
                    searching = false;
                }
            }
        }
    }

    private static void searchByNameMenu() {
        boolean searching = true;

        while (searching) {
            System.out.println("\n=== SEARCH STUDENT BY NAME ===");
            System.out.print("Enter student name: ");

            String name = scanner.nextLine();

            List<Students> results = manager.searchByName(name);

            if (results.isEmpty()) {
                System.out.println("No matching students found.");
            } else {
                System.out.println("Matching students:");

                for (Students student : results) {
                    System.out.println(student);
                }
            }

            System.out.println("\n1. Search Again");
            System.out.println("2. Return to Main Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> searching = true;
                case "2" -> searching = false;
                default -> {
                    System.out.println(
                            "Invalid option. Returning to Main Menu."
                    );
                    searching = false;
                }
            }
        }
    }

    private static void updateStudent() {
        System.out.println("\n=== UPDATE STUDENT ===");
        System.out.print("Enter the student's ID: ");

        String id = scanner.nextLine().trim();

        if (!id.matches("ST\\d{3}")) {
            System.out.println("Invalid ID format.");
            return;
        }

        Students student = manager.searchById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        boolean updating = true;

        while (updating) {
            System.out.println("\nCurrent student record:");
            System.out.println(student);

            System.out.println("\n=== UPDATE STUDENT FIELD ===");
            System.out.println("1. Update Name");
            System.out.println("2. Update Age");
            System.out.println("3. Update Class");
            System.out.println("4. Update Subjects");
            System.out.println("5. Finish Updating");
            System.out.print("Choose a field: ");

            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();

                        if (manager.updateName(id, newName)) {
                            System.out.println(
                                    "Name updated successfully."
                            );
                        } else {
                            System.out.println("Student not found.");
                        }
                    }

                    case "2" -> {
                        System.out.print("Enter new age: ");
                        int newAge = Integer.parseInt(
                                scanner.nextLine().trim()
                        );

                        if (manager.updateAge(id, newAge)) {
                            System.out.println(
                                    "Age updated successfully."
                            );
                        } else {
                            System.out.println("Student not found.");
                        }
                    }

                    case "3" -> {
                        System.out.print("Enter new class: ");
                        String newClass = scanner.nextLine();

                        if (manager.updateStudentClass(id, newClass)) {
                            System.out.println(
                                    "Class updated successfully."
                            );
                        } else {
                            System.out.println("Student not found.");
                        }
                    }

                    case "4" -> {
                        System.out.print("Enter new subjects: ");
                        String newSubjects = scanner.nextLine();

                        if (manager.updateSubjects(id, newSubjects)) {
                            System.out.println(
                                    "Subjects updated successfully."
                            );
                        } else {
                            System.out.println("Student not found.");
                        }
                    }

                    case "5" -> {
                        System.out.println("Finished updating.");
                        updating = false;
                    }

                    default -> System.out.println(
                            "Invalid option. Please try again."
                    );
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid number. Please enter a valid age."
                );
            } catch (IllegalArgumentException e) {
                System.out.println(
                        "Update failed: " + e.getMessage()
                );
            }
        }
    }

    private static void displayStudents() {
        List<Students> students = manager.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }

        for (Students student : students) {
            System.out.println(student);
        }
    }

    private static void sortStudents() {
        System.out.println("\n=== SORT STUDENTS ===");
        System.out.println("1. Sort by Name");
        System.out.println("2. Sort by Age");
        System.out.print("Choose sorting option: ");

        String choice = scanner.nextLine().trim();

        List<Students> sortedStudents;

        switch (choice) {
            case "1" -> sortedStudents = manager.sortByName();
            case "2" -> sortedStudents = manager.sortByAge();
            default -> {
                System.out.println("Invalid sorting option.");
                return;
            }
        }

        for (Students student : sortedStudents) {
            System.out.println(student);
        }
    }

    private static void removeStudent() {
        System.out.print("Enter student ID to remove: ");

        String id = scanner.nextLine().trim();

        if (manager.removeStudent(id)) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println(
                    "Student not found or invalid ID."
            );
        }
    }
}

