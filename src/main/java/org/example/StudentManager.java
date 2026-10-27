package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentManager {

    private final Map<String, Students> students = new HashMap<>();

    public boolean registerStudent(Students student) {
        if (student == null) {
            return false;
        }

        if (students.containsKey(student.getId())) {
            return false;
        }

        students.put(student.getId(), student);
        return true;
    }

    public Students searchById(String id) {
        if (id == null || !id.matches("ST\\d{3}")) {
            return null;
        }

        return students.get(id);
    }

    public List<Students> searchByName(String name) {
        List<Students> results = new ArrayList<>();

        if (name == null || name.isBlank()) {
            return results;
        }

        for (Students student : students.values()) {
            if (student.getName().equalsIgnoreCase(name.trim())) {
                results.add(student);
            }
        }

        return results;
    }

    public boolean updateName(String id, String newName) {
        Students student = searchById(id);

        if (student == null) {
            return false;
        }

        student.setName(newName);
        return true;
    }

    public boolean updateAge(String id, int newAge) {
        Students student = searchById(id);

        if (student == null) {
            return false;
        }

        student.setAge(newAge);
        return true;
    }

    public boolean updateStudentClass(
            String id,
            String newClass
    ) {
        Students student = searchById(id);

        if (student == null) {
            return false;
        }

        student.setStudentClass(newClass);
        return true;
    }

    public boolean updateSubjects(
            String id,
            String newSubjects
    ) {
        Students student = searchById(id);

        if (student == null) {
            return false;
        }

        student.setSubjects(newSubjects);
        return true;
    }

    public boolean removeStudent(String id) {
        if (id == null || !students.containsKey(id)) {
            return false;
        }

        students.remove(id);
        return true;
    }

    public List<Students> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    public List<Students> sortByName() {
        List<Students> sortedStudents = getAllStudents();

        sortedStudents.sort(
                Comparator.comparing(
                        Students::getName,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        return sortedStudents;
    }

    public List<Students> sortByAge() {
        List<Students> sortedStudents = getAllStudents();

        sortedStudents.sort(
                Comparator.comparingInt(Students::getAge)
        );

        return sortedStudents;
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }

    public int getStudentCount() {
        return students.size();
    }
}
