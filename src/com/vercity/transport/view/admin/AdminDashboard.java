package com.vercity.transport.view.admin;

import com.vercity.transport.controller.AdminController;
import com.vercity.transport.view.MainFrame;  // THIS IS THE CRUCIAL IMPORT

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JPanel {
    private AdminController adminController;

    public AdminDashboard() {
        adminController = new AdminController();
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Menu bar
        JMenuBar menuBar = new JMenuBar();

        JMenu manageMenu = new JMenu("Manage");
        JMenuItem studentsItem = new JMenuItem("Students");
        JMenuItem busesItem = new JMenuItem("Buses");
        JMenuItem bookingsItem = new JMenuItem("Bookings");
        manageMenu.add(studentsItem);
        manageMenu.add(busesItem);
        manageMenu.add(bookingsItem);

        JMenu accountMenu = new JMenu("Account");
        JMenuItem logoutItem = new JMenuItem("Logout");
        accountMenu.add(logoutItem);

        menuBar.add(manageMenu);
        menuBar.add(accountMenu);

        // Add action listeners
        studentsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showStudentManagement();
            }
        });

        busesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBusManagement();
            }
        });

        bookingsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show bookings management
            }
        });

        logoutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().showLoginForm();
            }
        });

        // Add menu bar to frame
        add(menuBar, BorderLayout.NORTH);

        // Show student management by default
        showStudentManagement();
    }

    private void showStudentManagement() {
        removeAll();
        add(new StudentManagement(), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showBusManagement() {
        removeAll();
        add(new BusManagement(), BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}