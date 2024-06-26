package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Zip Password Breaker");
        frame.setSize(400, 300); // Adjusted size for better layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        frame.add(panel);

        placeComponents(panel);

        frame.setVisible(true);
    }

    public static void placeComponents(JPanel panel) {
        // Set up GridBagConstraints for layout management
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Button to select ZIP file
        JButton selectFile_Button = new JButton("Select ZIP");
        JFileChooser selectFile = new JFileChooser();
        selectFile.setFileFilter(new FileNameExtensionFilter("ZIP file", "zip"));
        selectFile_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = selectFile.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    System.out.println(selectFile.getSelectedFile().getAbsolutePath());
                } else {
                    System.out.println("No file selected");
                }
            }
        });

        // Add the selectFile_Button to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(selectFile_Button, gbc);

        // Label for the maximum length input field
        JLabel length_Label = new JLabel("Max Length:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(length_Label, gbc);

        // Text field to input the maximum length
        JTextField length_TextField = new JTextField(20);
        length_TextField.setPreferredSize(new Dimension(200, 30));
        length_TextField.setText("6");
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(length_TextField, gbc);

        // Label for the characters to use input field
        JLabel chars_Label = new JLabel("Chars to use:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(chars_Label, gbc);

        // Text field to input the characters to use
        JTextField chars_TextField = new JTextField(20);
        chars_TextField.setPreferredSize(new Dimension(200, 60));
        chars_TextField.setText("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(chars_TextField, gbc);

        // Label to display the result of the password search
        JLabel result_Label = new JLabel("");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(result_Label, gbc);

        // Submit button to start the password search process
        JButton submit_Button = new JButton("Submit");
        submit_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result_Label.setText(Main.findCorrectPasswordInCombiantions("abcde", selectFile.getSelectedFile().getAbsolutePath(), Integer.parseInt(length_TextField.getText())));
            }
        });

        // Add the submit_Button to the panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(submit_Button, gbc);
    }
}
