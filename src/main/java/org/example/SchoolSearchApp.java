package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchoolSearchApp extends JFrame {
    private JTextArea outputArea;
    private JTextField inputField;
    private JTextArea resultArea;
    private StudentRepo studentRepository;

    public SchoolSearchApp(StudentRepo repository) {
        this.studentRepository = repository;

        setTitle("Schoolsearch");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        inputField = new JTextField();
        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleCommand(inputField.getText());
                inputField.setText("");
            }
        });
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        add(scrollPane, BorderLayout.CENTER);
        add(inputField, BorderLayout.NORTH);
        add(resultArea, BorderLayout.SOUTH);
    }

    private void handleCommand(String command) {
        String[] parts = command.split(":");
        String keyword = parts[0].trim().toUpperCase();
        long startTime = System.currentTimeMillis();

        switch (keyword) {
            case "S":
                String lastName = parts[1].trim();
                outputArea.append(studentRepository.searchStudentByLastName(lastName));
                break;
            case "T":
                String teacherLastName = parts[1].trim();
                outputArea.append(studentRepository.searchByTeacherLastName(teacherLastName));
                break;
            case "C":
                int classroom = Integer.parseInt(parts[1].trim());
                outputArea.append(studentRepository.searchByClassroom(classroom));
                break;
            case "B":
                int bus = Integer.parseInt(parts[1].trim());
                outputArea.append(studentRepository.searchByBus(bus));
                break;
            case "I":
                outputArea.append(studentRepository.showStats());
                break;
            case "Q":
                System.exit(0);
                break;
            default:
                outputArea.append("Спробуйте знову\n");
        }

        long endTime = System.currentTimeMillis();
        long searchTime = endTime - startTime;
        resultArea.setText("");
        resultArea.append("Час пошуку: " + searchTime + "мс\n");
    }

    public static void main(String[] args) {
        StudentRepo repository = new StudentRepo("students.txt");
        SwingUtilities.invokeLater(() -> {
            SchoolSearchApp app = new SchoolSearchApp(repository);
            app.setVisible(true);
        });
    }
}
