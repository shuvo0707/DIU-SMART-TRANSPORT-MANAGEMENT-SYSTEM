package com.vercity.transport;

import com.vercity.transport.view.MainFrame;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety
        SwingUtilities.invokeLater(() -> {
            try {
                // Set system look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                // Create and show the main frame
                MainFrame mainFrame = MainFrame.getInstance();
                mainFrame.setVisible(true);
            } catch (UnsupportedLookAndFeelException e) {
                JOptionPane.showMessageDialog(null,
                        "System look and feel not supported. Using default.",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                // Continue with default look and feel
                MainFrame mainFrame = MainFrame.getInstance();
                mainFrame.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Failed to start application: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }
}