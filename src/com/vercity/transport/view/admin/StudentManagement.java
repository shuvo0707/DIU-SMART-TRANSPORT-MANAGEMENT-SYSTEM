package com.vercity.transport.view.admin;

import com.vercity.transport.controller.AdminController;
import com.vercity.transport.model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentManagement extends JPanel {
    private AdminController adminController;
    private JTable studentTable;
    private DefaultTableModel tableModel;

    public StudentManagement() {
        adminController = new AdminController();
        initComponents();
        loadStudents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Table model
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Username");
        tableModel.addColumn("Full Name");
        tableModel.addColumn("Email");
        tableModel.addColumn("Matric Number");
        tableModel.addColumn("Department");

        // Table
        studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddStudentDialog();
            }
        });

        JButton editButton = new JButton("Edit Student");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editSelectedStudent();
            }
        });

        JButton deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedStudent();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Add components
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadStudents() {
        tableModel.setRowCount(0); // Clear table
        List<Student> students = adminController.getAllStudents();

        for (Student student : students) {
            tableModel.addRow(new Object[]{
                    student.getStudentId(),
                    student.getUsername(),
                    student.getFullName(),
                    student.getEmail(),
                    student.getMatricNumber(),
                    student.getDepartment()
            });
        }
    }

    private void showAddStudentDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Add New Student");
        dialog.setModal(true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField fullNameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField matricField = new JTextField();
        JTextField departmentField = new JTextField();

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Full Name:"));
        panel.add(fullNameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Matric Number:"));
        panel.add(matricField);
        panel.add(new JLabel("Department:"));
        panel.add(departmentField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student student = new Student(
                        usernameField.getText(),
                        new String(passwordField.getPassword()),
                        fullNameField.getText(),
                        emailField.getText(),
                        matricField.getText(),
                        departmentField.getText()
                );

                boolean success = adminController.addStudent(student);
                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Student added successfully");
                    loadStudents();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Failed to add student",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(saveButton, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void editSelectedStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to edit",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int studentId = (int) tableModel.getValueAt(selectedRow, 0);
        String username = (String) tableModel.getValueAt(selectedRow, 1);
        String fullName = (String) tableModel.getValueAt(selectedRow, 2);
        String email = (String) tableModel.getValueAt(selectedRow, 3);
        String matric = (String) tableModel.getValueAt(selectedRow, 4);
        String department = (String) tableModel.getValueAt(selectedRow, 5);

        JDialog dialog = new JDialog();
        dialog.setTitle("Edit Student");
        dialog.setModal(true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField usernameField = new JTextField(username);
        JPasswordField passwordField = new JPasswordField();
        JTextField fullNameField = new JTextField(fullName);
        JTextField emailField = new JTextField(email);
        JTextField matricField = new JTextField(matric);
        JTextField departmentField = new JTextField(department);

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password (leave blank to keep current):"));
        panel.add(passwordField);
        panel.add(new JLabel("Full Name:"));
        panel.add(fullNameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Matric Number:"));
        panel.add(matricField);
        panel.add(new JLabel("Department:"));
        panel.add(departmentField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student student = new Student(
                        usernameField.getText(),
                        new String(passwordField.getPassword()),
                        fullNameField.getText(),
                        emailField.getText(),
                        matricField.getText(),
                        departmentField.getText()
                );
                student.setStudentId(studentId);

                boolean success = adminController.updateStudent(student);
                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Student updated successfully");
                    loadStudents();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Failed to update student",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(saveButton, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void deleteSelectedStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int studentId = (int) tableModel.getValueAt(selectedRow, 0);
        String fullName = (String) tableModel.getValueAt(selectedRow, 2);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete student: " + fullName + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = adminController.deleteStudent(studentId);
            if (success) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully");
                loadStudents();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete student",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
