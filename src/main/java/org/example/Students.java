package org.example;

public class Students {

    private final String id;
    private String name;
    private int age;
    private String studentClass;
    private String subjects;

    public Students(
            String id,
            String name,
            int age,
            String studentClass,
            String subjects
    ) {
        if (id == null || !id.matches("ST\\d{3}")) {
            throw new IllegalArgumentException(
                    "ID must follow the format ST001."
            );
        }

        validateName(name);
        validateAge(age);
        validateClass(studentClass);
        validateSubjects(subjects);

        this.id = id;
        this.name = name.trim();
        this.age = age;
        this.studentClass = studentClass.trim();
        this.subjects = subjects.trim();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name.trim();
    }

    public void setAge(int age) {
        validateAge(age);
        this.age = age;
    }

    public void setStudentClass(String studentClass) {
        validateClass(studentClass);
        this.studentClass = studentClass.trim();
    }

    public void setSubjects(String subjects) {
        validateSubjects(subjects);
        this.subjects = subjects.trim();
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Name cannot be empty."
            );
        }
    }

    private static void validateAge(int age) {
        if (age < 1 || age > 120) {
            throw new IllegalArgumentException(
                    "Age must be between 1 and 120."
            );
        }
    }

    private static void validateClass(String studentClass) {
        if (studentClass == null || studentClass.isBlank()) {
            throw new IllegalArgumentException(
                    "Class cannot be empty."
            );
        }
    }

    private static void validateSubjects(String subjects) {
        if (subjects == null || subjects.isBlank()) {
            throw new IllegalArgumentException(
                    "Subjects cannot be empty."
            );
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", studentClass='" + studentClass + '\'' +
                ", subjects='" + subjects + '\'' +
                '}';
    }
}