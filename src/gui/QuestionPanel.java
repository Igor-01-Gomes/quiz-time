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

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        add(questionLabel, BorderLayout.NORTH);

        JPanel answersPanel = new JPanel(new GridLayout(4, 1, 15, 15));
        answersPanel.setOpaque(false);

        for (int i = 0; i < 4; i++) {
            JButton btn = new JButton();
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            btn.setFocusPainted(false);
            btn.setBackground(new Color(220, 235, 255));
            btn.setOpaque(true);

            int index = i;
            btn.addActionListener(e -> handleAnswer(index));

            answerButtons[i] = btn;
            answersPanel.add(btn);
        }

        add(answersPanel, BorderLayout.CENTER);
    }

    public void colorButton(String result) {
        for (int i = 0; i < 4; i++) {
            answerButtons[i].setEnabled(false);

            if (i == lastSelectedIndex) {
                answerButtons[i].setBackground(
                        result.equalsIgnoreCase("Rätt") ? new Color(90, 200, 90) : new Color(220, 80, 80));
            }
        }
    }

    public void showQuestion(String question, String[] options) {
        questionLabel.setText("<html><center>" + question + "</center></html>");

        for (int i = 0; i < 4; i++) {
            answerButtons[i].setText(options[i]);
            answerButtons[i].setEnabled(true);
            answerButtons[i].setBackground(new Color(220, 235, 255));
        }
    }

    private void handleAnswer(int selectedIndex) {
        lastSelectedIndex = selectedIndex;
        client.sendAnswer(selectedIndex + 1);
    }
}