package org.example;

import java.io.*;
import java.util.ArrayList;

public class StudentRepo {
    private ArrayList<Student> students = new ArrayList<>();

    public StudentRepo(String fileName) {
        loadData(fileName);
    }

    private void loadData(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String lastName = parts[0];
                    String firstName = parts[1];
                    int grade = Integer.parseInt(parts[2]);
                    int classroom = Integer.parseInt(parts[3]);
                    int bus = Integer.parseInt(parts[4]);
                    String teacherLastName = parts[5];
                    String teacherFirstName = parts[6];

                    students.add(new Student(lastName, firstName, grade, classroom, bus, teacherLastName, teacherFirstName));
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні " + fileName);
        }
    }
    public String showStats() {
        StringBuilder result = new StringBuilder();
        result.append(students.size()).append("\n");
        return  result.toString();
    }


    public String searchStudentByLastName(String lastName) {
        StringBuilder result = new StringBuilder();
        boolean found = false;
        for (Student student : students) {
            if (student.getLastName().equalsIgnoreCase(lastName)) {
                result.append(student.toString()).append("\n");
                found = true;
            }
        }
        if (!found) {
            result.append("Немає таких студентів: ").append(lastName).append("\n");
        }
        return result.toString();
    }

    public String searchByTeacherLastName(String teacherLastName) {
        StringBuilder result = new StringBuilder();
        boolean found = false;
        for (Student student : students) {
            if (student.getTeacherLastName().equalsIgnoreCase(teacherLastName)) {
                result.append(student.getFirstName()).append(" ").append(student.getLastName()).append("\n");
                found = true;
            }
        }
        if (!found) {
            result.append("Немає такого викладача: ").append(teacherLastName).append("\n");
        }
        return result.toString();
    }

    public String searchByClassroom(int classroom) {
        StringBuilder result = new StringBuilder();
        boolean found = false;
        for (Student student : students) {
            if (student.getClassroom() == classroom) {
                result.append(student.getFirstName()).append(" ").append(student.getLastName()).append("\n");
                found = true;
            }
        }
        if (!found) {
            result.append("Немає студентів з таким кабінетом: ").append(classroom).append("\n");
        }
        return result.toString();
    }

    public String searchByBus(int bus) {
        StringBuilder result = new StringBuilder();
        boolean found = false;
        for (Student student : students) {
            if (student.getBus() == bus) {
                result.append(student.toString()).append("\n");
                found = true;
            }
        }
        if (!found) {
            result.append("Немає студентів з таким маршрутом: ").append(bus).append("\n");
        }
        return result.toString();
    }
}
