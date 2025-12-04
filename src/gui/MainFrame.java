package gui;

import Server.Database;
import Client.QuizClient;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class MainFrame extends JFrame {

    private QuizClient client;

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    private RoundPanel roundPanel;
    private QuestionPanel questionPanel;
    private FinalPanel finalPanel;

    private Database database = new Database();

    public MainFrame() {
        setTitle("Quiz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        try {
            client = new QuizClient("localhost", 8888, this);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Kunde inte ansluta till servern.");
            System.exit(0);
        }

        StartPanel startPanel = new StartPanel(this);

        roundPanel = new RoundPanel(this);
        questionPanel = new QuestionPanel(this);
        finalPanel = new FinalPanel(this);


        mainPanel.add(startPanel, "start");
        mainPanel.add(questionPanel, "question");
        mainPanel.add(roundPanel, "round");
        mainPanel.add(finalPanel, "final");

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

    public QuizClient getClient() {
        return client;
    }
}

