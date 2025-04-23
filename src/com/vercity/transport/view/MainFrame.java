package com.vercity.transport.view;

import com.vercity.transport.view.admin.AdminDashboard;
import com.vercity.transport.view.auth.LoginForm;
import com.vercity.transport.view.auth.RegisterForm;
import com.vercity.transport.view.student.StudentDashboard;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static MainFrame instance;
    private JPanel currentPanel;

    private MainFrame() {
        setTitle("DIU Transport Management System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set layout
        setLayout(new BorderLayout());

        // Show login form by default
        showLoginForm();
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    public void showPanel(JPanel panel) {
        if (currentPanel != null) {
            remove(currentPanel);
        }
        currentPanel = panel;
        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void showLoginForm() {
        showPanel(new LoginForm());
    }

    public void showAdminDashboard() {
        showPanel(new AdminDashboard());
    }

    public void showStudentDashboard() {
        showPanel(new StudentDashboard());
    }

    public void showRegisterForm() {
        showPanel(new RegisterForm());
    }
}