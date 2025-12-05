package gui;

import Client.Client;

import javax.swing.*;
import java.awt.*;

public class QuestionPanel extends JPanel {

    private final MainFrame frame;
    private final Client client;

    private final JLabel questionLabel = new JLabel("", SwingConstants.CENTER);
    private final JButton[] answerButtons = new JButton[4];

    private int lastSelectedIndex = -1;

    public QuestionPanel(MainFrame frame, Client client) {
        this.frame = frame;
        this.client = client;

        setLayout(new BorderLayout());

        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(questionLabel, BorderLayout.NORTH);

        JPanel answersPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JButton();
            int index = i;
            answerButtons[i].addActionListener(e -> handleAnswer(index));
            answersPanel.add(answerButtons[i]);
        }

        add(answersPanel, BorderLayout.CENTER);
    }

    public void colorButton(String color) {
        for (int i = 0; i < 4; i++) {

            answerButtons[i].setOpaque(true);
            answerButtons[i].setEnabled(false);

            if (i == lastSelectedIndex) {
                answerButtons[i].setBackground(
                        color.equalsIgnoreCase("Rätt") ? Color.GREEN : Color.RED);
            }
        }
    }

    public void showQuestion(String question, String[] options) {
        questionLabel.setText(question);

        for (int i = 0; i < 4; i++) {
            answerButtons[i].setText(options[i]);
            answerButtons[i].setEnabled(true);
            answerButtons[i].setBorderPainted(false);
            answerButtons[i].setOpaque(true);
            answerButtons[i].setBackground(null);
        }

    }

    private void handleAnswer(int selectedIndex) {
        lastSelectedIndex = selectedIndex;
//        for (JButton btn : answerButtons) {
//            btn.setEnabled(false);
//        }

        int answerNumber = selectedIndex + 1;
        client.sendAnswer(answerNumber);
    }
}