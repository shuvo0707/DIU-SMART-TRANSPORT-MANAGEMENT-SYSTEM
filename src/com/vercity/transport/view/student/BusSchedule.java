package com.vercity.transport.view.student;

import com.vercity.transport.controller.StudentController;
import com.vercity.transport.model.Bus;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.util.Properties;

public class BusSchedule extends JPanel {
    private StudentController studentController;
    private JTable busTable;
    private DefaultTableModel tableModel;
    private JDatePickerImpl datePicker;

    public BusSchedule() {
        studentController = new StudentController();
        initComponents();
        loadBuses();
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
        tableModel.addColumn("ID");
        tableModel.addColumn("Bus Number");
        tableModel.addColumn("Route");
        tableModel.addColumn("Departure Time");
        tableModel.addColumn("Arrival Time");
        tableModel.addColumn("Available Days");
        tableModel.addColumn("Capacity");

        // Table
        busTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(busTable);

        // Date picker and book button
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel dateLabel = new JLabel("Travel Date:");

        // JDatePicker implementation
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        JButton bookButton = new JButton("Book Selected Bus");
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookSelectedBus(datePicker.getJFormattedTextField().getText());
            }
        });

        controlPanel.add(dateLabel);
        controlPanel.add(datePicker);
        controlPanel.add(bookButton);

        // Add components
        add(scrollPane, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    private void loadBuses() {
        tableModel.setRowCount(0); // Clear table
        List<Bus> buses = studentController.getAllBuses();

        for (Bus bus : buses) {
            tableModel.addRow(new Object[]{
                    bus.getBusId(),
                    bus.getBusNumber(),
                    bus.getRoute(),
                    bus.getDepartureTime().toString(),
                    bus.getArrivalTime().toString(),
                    bus.getAvailableDays(),
                    bus.getCapacity()
            });
        }
    }

    private void bookSelectedBus(String dateString) {
        int selectedRow = busTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a bus to book",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int busId = (int) tableModel.getValueAt(selectedRow, 0);
            String busNumber = (String) tableModel.getValueAt(selectedRow, 1);
            Date travelDate = Date.valueOf(dateString);

            // For demo, we'll use student ID 1 - in real app, get from session
            boolean success = studentController.bookBus(1, busId, travelDate);

            if (success) {
                JOptionPane.showMessageDialog(this,
                        "Bus " + busNumber + " booked successfully for " + travelDate,
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to book bus",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Use YYYY-MM-DD",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Inner class for date formatting in JDatePicker
    private static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
        private String datePattern = "yyyy-MM-dd";
        private java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws java.text.ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws java.text.ParseException {
            if (value != null) {
                java.util.Calendar cal = (java.util.Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
    }
}