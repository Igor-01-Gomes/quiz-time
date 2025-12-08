package gui;

import Client.Client;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private RoundPanel roundPanel;
    private QuestionPanel questionPanel;
    private StartPanel startPanel;
    private CategoryPanel categoryPanel;

    private final Client client;

    public MainFrame(Client client) {
        this.client = client;

        setTitle("Quiz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        // Initiera paneler
        startPanel = new StartPanel(this, client);
        roundPanel = new RoundPanel(this, client);
        questionPanel = new QuestionPanel(this, client);
        categoryPanel = new CategoryPanel(this, client);

        // Lägg till paneler i CardLayout
        mainPanel.add(startPanel, "start");
        mainPanel.add(questionPanel, "question");
        mainPanel.add(roundPanel, "round");
        mainPanel.add(categoryPanel, "category");

        add(mainPanel);
        setVisible(true);
    }

    public RoundPanel getRoundPanel() {
        return roundPanel;
    }

    public QuestionPanel getQuestionPanel() {
        return questionPanel;
    }

    public CategoryPanel getCategoryPanel() {
        return categoryPanel;
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }

    public void colorButton(String color) {
        questionPanel.colorButton(color);
    }


    public void showCategoryChoice() {
        showPanel("category");
    }

    public void showQuestion(String question, String[] options) {
        questionPanel.showQuestion(question, options);
        showPanel("question");
    }

    public void showResult(String result) {
        colorButton(result);
        showPanel("question");
    }

    public void showRoundResult(String result) {
        roundPanel.setRoundScore(result);
        showPanel("round");
    }


    public void showEnd(String summary) {

        String[] parts = summary.split("-");

        int my = Integer.parseInt(parts[0]);
        int opponent = Integer.parseInt(parts[1]);

        String finalText =
                "<html>Slutresultat:<br>" +
                        "Dina slutpoäng = " + my + "<br>" +
                        "Motståndarens slutpoäng = " + opponent +
                        "</html>";

        roundPanel.setSummaryText(finalText);

        showPanel("round");
    }
}

