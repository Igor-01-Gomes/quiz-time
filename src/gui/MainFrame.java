package gui;

import Server.Database;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    private RoundPanel roundPanel;
    private QuestionPanel questionPanel;

    private Database database = new Database();

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

    // Getter så QuestionPanel kan få databasen
    public Database getDatabase() {
        return database;
    }
    public List<String> getCategories() {
        return new ArrayList<>(database.getAllCategories());
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

