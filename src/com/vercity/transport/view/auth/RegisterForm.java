package com.vercity.transport.view.auth;

import com.vercity.transport.controller.AuthController;
import com.vercity.transport.model.Student;
import com.vercity.transport.view.MainFrame; // Import MainFrame
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField matricField;
    private JTextField departmentField;
    private AuthController authController;

    public RegisterForm() {
        authController = new AuthController();
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header panel
        JPanel headerPanel = new JPanel();
        JLabel titleLabel = new JLabel("Student Registration");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerPanel.add(titleLabel);

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JLabel matricLabel = new JLabel("Matric Number:");
        matricField = new JTextField();

        JLabel departmentLabel = new JLabel("Department:");
        departmentField = new JTextField();

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(fullNameLabel);
        formPanel.add(fullNameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(matricLabel);
        formPanel.add(matricField);
        formPanel.add(departmentLabel);
        formPanel.add(departmentField);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerStudent();
            }
        });

        JButton backButton = new JButton("Back to Login");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().showLoginForm();
            }
        });

        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        // Add components to main panel
        add(headerPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void registerStudent() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String matric = matricField.getText().trim();
        String department = departmentField.getText().trim();

        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() ||
                email.isEmpty() || matric.isEmpty() || department.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student = new Student(username, password, fullName, email, matric, department);
        boolean success = authController.registerStudent(student);

        if (success) {
            JOptionPane.showMessageDialog(this, "Registration successful!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            MainFrame.getInstance().showLoginForm();
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed. Username or email may already exist.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}