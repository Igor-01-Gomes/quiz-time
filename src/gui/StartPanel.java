package gui;

import Client.Client;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {

    public StartPanel(MainFrame frame, Client client) {
        setLayout(new BorderLayout());

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));

        startButton.addActionListener(e -> frame.showPanel("question"));

        add(startButton, BorderLayout.CENTER);
    }
}
