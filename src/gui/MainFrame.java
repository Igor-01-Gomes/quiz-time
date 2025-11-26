package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    public MainFrame() {
        setTitle("Quiz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        StartPanel startPanel = new StartPanel(this);
        QuestionPanel questionPanel = new QuestionPanel(this);

        mainPanel.add(startPanel, "start");
        mainPanel.add(questionPanel, "question");

        add(mainPanel);
        setVisible(true);
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }
}