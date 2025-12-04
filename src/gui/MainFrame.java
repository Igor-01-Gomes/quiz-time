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
        setSize(500, 400);
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
//        String[] categories = {"Bilar", "Musik", "Geografi"};
//        String choice = (String)
//                JOptionPane.showInputDialog(
//                        this,
//                        "Välj kategori:",
//                        "Kategori",
//                        JOptionPane.QUESTION_MESSAGE,
//                        null,
//                        categories,
//                        categories[0]);
//        if (choice != null) {
//            client.sendCategory(choice);
//        }
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
        roundPanel.setSummaryText("Slutresultat: " + summary);

        showPanel("round");

    }
}

