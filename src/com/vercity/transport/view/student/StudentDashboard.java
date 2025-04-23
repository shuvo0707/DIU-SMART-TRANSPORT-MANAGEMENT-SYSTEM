package com.vercity.transport.view.student;

import com.vercity.transport.view.MainFrame; // Import MainFrame
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentDashboard extends JPanel {
    public StudentDashboard() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Menu bar
        JMenuBar menuBar = new JMenuBar();

        JMenu transportMenu = new JMenu("Transport");
        JMenuItem scheduleItem = new JMenuItem("Bus Schedule");
        JMenuItem bookingItem = new JMenuItem("My Bookings");
        transportMenu.add(scheduleItem);
        transportMenu.add(bookingItem);

        JMenu accountMenu = new JMenu("Account");
        JMenuItem profileItem = new JMenuItem("My Profile");
        JMenuItem logoutItem = new JMenuItem("Logout");
        accountMenu.add(profileItem);
        accountMenu.add(logoutItem);

        menuBar.add(transportMenu);
        menuBar.add(accountMenu);

        // Add action listeners
        scheduleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBusSchedule();
            }
        });

        bookingItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBookingHistory();
            }
        });

        profileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show profile management
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

        // Show bus schedule by default
        showBusSchedule();
    }

    private void showBusSchedule() {
        removeAll();
        add(new BusSchedule(), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showBookingHistory() {
        removeAll();
        add(new BookingHistory(), BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}