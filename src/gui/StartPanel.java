package gui;

import Client.Client;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {

    public StartPanel(MainFrame frame, Client client) {
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));

        JLabel title = new JLabel("Nya Quizkampen woo", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 36));

        JButton startButton = new JButton("Starta spelet");
        startButton.setFont(new Font("Segoe UI", Font.BOLD, 24));
        startButton.setBackground(new Color(80, 150, 255));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);

        startButton.addActionListener(e -> frame.showPanel("question"));

        add(title, BorderLayout.NORTH);
        add(startButton, BorderLayout.CENTER);
    }
}
