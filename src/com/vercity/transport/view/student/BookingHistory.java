package com.vercity.transport.view.student;

import com.vercity.transport.controller.StudentController;
import com.vercity.transport.model.Booking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BookingHistory extends JPanel {
    private StudentController studentController;
    private JTable bookingTable;
    private DefaultTableModel tableModel;

    public BookingHistory() {
        studentController = new StudentController();
        initComponents();
        loadBookings();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Table model
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        tableModel.addColumn("Booking ID");
        tableModel.addColumn("Bus ID");
        tableModel.addColumn("Booking Date");
        tableModel.addColumn("Travel Date");
        tableModel.addColumn("Status");

        // Table
        bookingTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookingTable);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadBookings();
            }
        });

        JButton cancelButton = new JButton("Cancel Booking");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelSelectedBooking();
            }
        });

        buttonPanel.add(refreshButton);
        buttonPanel.add(cancelButton);

        // Add components
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadBookings() {
        tableModel.setRowCount(0); // Clear table
        // For demo, we'll use student ID 1 - in real app, get from session
        List<Booking> bookings = studentController.getStudentBookings(1);

        for (Booking booking : bookings) {
            tableModel.addRow(new Object[]{
                    booking.getBookingId(),
                    booking.getBusId(),
                    booking.getBookingDate().toString(),
                    booking.getTravelDate().toString(),
                    booking.getStatus()
            });
        }
    }

    private void cancelSelectedBooking() {
        int selectedRow = bookingTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a booking to cancel",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int bookingId = (int) tableModel.getValueAt(selectedRow, 0);
        String status = (String) tableModel.getValueAt(selectedRow, 4);

        if (!status.equals("confirmed")) {
            JOptionPane.showMessageDialog(this, "Only confirmed bookings can be cancelled",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to cancel this booking?",
                "Confirm Cancellation", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = studentController.cancelBooking(bookingId);
            if (success) {
                JOptionPane.showMessageDialog(this, "Booking cancelled successfully");
                loadBookings();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to cancel booking",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
