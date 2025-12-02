package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    private RoundPanel roundPanel;
    private QuestionPanel questionPanel;

    public MainFrame() {
        setTitle("Quiz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        StartPanel startPanel = new StartPanel(this);

        roundPanel = new RoundPanel(this);
        questionPanel = new QuestionPanel(this);

        mainPanel.add(startPanel, "start");
        mainPanel.add(questionPanel, "question");
        mainPanel.add(roundPanel, "round");

        add(mainPanel);
        setVisible(true);
    }

    public RoundPanel getRoundPanel() {
        return roundPanel;
    }

    public QuestionPanel getQuestionPanel() {
        return questionPanel;
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }
}
