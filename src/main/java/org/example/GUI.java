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
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

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

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(selectFile_Button, gbc);

        JLabel length_Label = new JLabel("Max Length:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(length_Label, gbc);

        JTextField length_TextField = new JTextField(20);
        length_TextField.setPreferredSize(new Dimension(200, 30));

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(length_TextField, gbc);

        JButton submit_Button = new JButton("Submit");
        submit_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(Main.checkAllCombinations("abcde", Integer.parseInt(length_TextField.getText()), selectFile.getSelectedFile().getAbsolutePath()));
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(submit_Button, gbc);
    }
}
