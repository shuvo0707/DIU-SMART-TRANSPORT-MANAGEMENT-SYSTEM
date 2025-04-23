package com.vercity.transport.view.auth;

import com.vercity.transport.controller.AuthController;
import com.vercity.transport.view.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginForm extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeCombo;
    private final AuthController authController;

    public LoginForm() {
        this.authController = new AuthController();
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        setBackground(new Color(240, 245, 250));

        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        headerPanel.setBackground(new Color(240, 245, 250));

        JLabel welcomeLabel = new JLabel("WELCOME", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(0, 102, 204));

        JLabel diuLabel = new JLabel("DIU Smart Transport", SwingConstants.CENTER);
        diuLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        diuLabel.setForeground(new Color(0, 102, 204));

        headerPanel.add(welcomeLabel, BorderLayout.NORTH);
        headerPanel.add(diuLabel, BorderLayout.CENTER);

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 10, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Username field
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Username:"), gbc);

        gbc.gridy = 1;
        usernameField = new JTextField(20);
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        formPanel.add(usernameField, gbc);

        // Password field
        gbc.gridy = 2;
        formPanel.add(new JLabel("Password:"), gbc);

        gbc.gridy = 3;
        passwordField = new JPasswordField(20);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        formPanel.add(passwordField, gbc);

        // Login as dropdown
        gbc.gridy = 4;
        formPanel.add(new JLabel("Login as:"), gbc);

        gbc.gridy = 5;
        userTypeCombo = new JComboBox<>(new String[]{"Student", "Admin"});
        userTypeCombo.setBackground(Color.WHITE);
        userTypeCombo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formPanel.add(userTypeCombo, gbc);

        // Button panel with updated login button color
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        // Updated Login Button with vibrant green color (#4CAF50)
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(120, 35));
        loginButton.setBackground(new Color(76, 175, 80)); // Vibrant green color
        loginButton.setForeground(Color.blue);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(this::performLogin);

        JButton registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(120, 35));
        registerButton.setBackground(Color.WHITE);
        registerButton.setForeground(new Color(0, 102, 204));
        registerButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 102, 204)),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        registerButton.setFocusPainted(false);
        registerButton.addActionListener(e -> MainFrame.getInstance().showRegisterForm());

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        // Add components to main panel
        add(headerPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(formPanel, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        centerPanel.setBackground(Color.WHITE);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void performLogin(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String userType = (String) userTypeCombo.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SwingWorker<Object, Void> worker = new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                return switch (userType) {
                    case "Student" -> authController.authenticateStudent(username, password);
                    case "Admin" -> authController.authenticateAdmin(username, password);
                    default -> null;
                };
            }

            @Override
            protected void done() {
                try {
                    Object user = get();
                    if (user != null) {
                        switch (userType) {
                            case "Student" -> MainFrame.getInstance().showStudentDashboard();
                            case "Admin" -> MainFrame.getInstance().showAdminDashboard();
                        }
                    } else {
                        JOptionPane.showMessageDialog(LoginForm.this,
                                "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Authentication error", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute();
    }
}