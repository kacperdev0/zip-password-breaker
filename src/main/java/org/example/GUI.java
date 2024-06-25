package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.example.Main;

public class GUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Zip Password Breaker");
        frame.setSize(400, 300); // Adjusted size for better layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        placeComponents(panel);

        frame.setVisible(true);
    }

    public static void placeComponents(JPanel panel) {
        JButton selectFile_Button = new JButton("Select ZIP");
        JFileChooser selectFile = new JFileChooser();
        selectFile.setFileFilter(new FileNameExtensionFilter("ZIP file", "zip"));
        selectFile_Button.setAlignmentX(Component.CENTER_ALIGNMENT);
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

        JButton submit_Button = new JButton("Submit");
        submit_Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(Main.checkAllCombinations("abcde", 4, selectFile.getSelectedFile().getAbsolutePath()));
            }
        });

        Component padding = Box.createRigidArea(new Dimension(10,10));

        panel.add(padding);
        panel.add(selectFile_Button);
        panel.add(padding);
        panel.add(submit_Button);
    }
}
