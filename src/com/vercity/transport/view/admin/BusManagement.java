package com.vercity.transport.view.admin;

import com.vercity.transport.controller.AdminController;
import com.vercity.transport.model.Bus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.List;

public class BusManagement extends JPanel {
    private AdminController adminController;
    private JTable busTable;
    private DefaultTableModel tableModel;

    public BusManagement() {
        adminController = new AdminController();
        initComponents();
        loadBuses();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Table model
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Bus Number");
        tableModel.addColumn("Capacity");
        tableModel.addColumn("Route");
        tableModel.addColumn("Departure Time");
        tableModel.addColumn("Arrival Time");
        tableModel.addColumn("Available Days");

        // Table
        busTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(busTable);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton addButton = new JButton("Add Bus");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddBusDialog();
            }
        });

        JButton editButton = new JButton("Edit Bus");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editSelectedBus();
            }
        });

        JButton deleteButton = new JButton("Delete Bus");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedBus();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Add components
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadBuses() {
        tableModel.setRowCount(0); // Clear table
        List<Bus> buses = adminController.getAllBuses();

        for (Bus bus : buses) {
            tableModel.addRow(new Object[]{
                    bus.getBusId(),
                    bus.getBusNumber(),
                    bus.getCapacity(),
                    bus.getRoute(),
                    bus.getDepartureTime().toString(),
                    bus.getArrivalTime().toString(),
                    bus.getAvailableDays()
            });
        }
    }

    private void showAddBusDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Add New Bus");
        dialog.setModal(true);
        dialog.setSize(500, 300);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField busNumberField = new JTextField();
        JTextField capacityField = new JTextField();
        JTextField routeField = new JTextField();
        JTextField departureField = new JTextField();
        JTextField arrivalField = new JTextField();
        JTextField daysField = new JTextField();

        panel.add(new JLabel("Bus Number:"));
        panel.add(busNumberField);
        panel.add(new JLabel("Capacity:"));
        panel.add(capacityField);
        panel.add(new JLabel("Route:"));
        panel.add(routeField);
        panel.add(new JLabel("Departure Time (HH:MM:SS):"));
        panel.add(departureField);
        panel.add(new JLabel("Arrival Time (HH:MM:SS):"));
        panel.add(arrivalField);
        panel.add(new JLabel("Available Days (e.g., Mon-Fri):"));
        panel.add(daysField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Bus bus = new Bus(
                            busNumberField.getText(),
                            Integer.parseInt(capacityField.getText()),
                            routeField.getText(),
                            Time.valueOf(departureField.getText()),
                            Time.valueOf(arrivalField.getText()),
                            daysField.getText()
                    );

                    boolean success = adminController.addBus(bus);
                    if (success) {
                        JOptionPane.showMessageDialog(dialog, "Bus added successfully");
                        loadBuses();
                        dialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Failed to add bus",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid time format. Use HH:MM:SS",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(saveButton, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void editSelectedBus() {
        int selectedRow = busTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a bus to edit",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int busId = (int) tableModel.getValueAt(selectedRow, 0);
        String busNumber = (String) tableModel.getValueAt(selectedRow, 1);
        int capacity = (int) tableModel.getValueAt(selectedRow, 2);
        String route = (String) tableModel.getValueAt(selectedRow, 3);
        String departure = (String) tableModel.getValueAt(selectedRow, 4);
        String arrival = (String) tableModel.getValueAt(selectedRow, 5);
        String days = (String) tableModel.getValueAt(selectedRow, 6);

        JDialog dialog = new JDialog();
        dialog.setTitle("Edit Bus");
        dialog.setModal(true);
        dialog.setSize(500, 300);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField busNumberField = new JTextField(busNumber);
        JTextField capacityField = new JTextField(String.valueOf(capacity));
        JTextField routeField = new JTextField(route);
        JTextField departureField = new JTextField(departure);
        JTextField arrivalField = new JTextField(arrival);
        JTextField daysField = new JTextField(days);

        panel.add(new JLabel("Bus Number:"));
        panel.add(busNumberField);
        panel.add(new JLabel("Capacity:"));
        panel.add(capacityField);
        panel.add(new JLabel("Route:"));
        panel.add(routeField);
        panel.add(new JLabel("Departure Time (HH:MM:SS):"));
        panel.add(departureField);
        panel.add(new JLabel("Arrival Time (HH:MM:SS):"));
        panel.add(arrivalField);
        panel.add(new JLabel("Available Days (e.g., Mon-Fri):"));
        panel.add(daysField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Bus bus = new Bus(
                            busNumberField.getText(),
                            Integer.parseInt(capacityField.getText()),
                            routeField.getText(),
                            Time.valueOf(departureField.getText()),
                            Time.valueOf(arrivalField.getText()),
                            daysField.getText()
                    );
                    bus.setBusId(busId);

                    boolean success = adminController.updateBus(bus);
                    if (success) {
                        JOptionPane.showMessageDialog(dialog, "Bus updated successfully");
                        loadBuses();
                        dialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Failed to update bus",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid time format. Use HH:MM:SS",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(saveButton, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void deleteSelectedBus() {
        int selectedRow = busTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a bus to delete",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int busId = (int) tableModel.getValueAt(selectedRow, 0);
        String busNumber = (String) tableModel.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete bus: " + busNumber + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = adminController.deleteBus(busId);
            if (success) {
                JOptionPane.showMessageDialog(this, "Bus deleted successfully");
                loadBuses();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete bus",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
