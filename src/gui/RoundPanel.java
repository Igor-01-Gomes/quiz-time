package gui;

import Client.Client;

import javax.swing.*;
import java.awt.*;

public class RoundPanel extends JPanel {

    private JLabel summaryLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel roundLabel = new JLabel("", SwingConstants.CENTER);
    private JTextArea summaryTextArea = new JTextArea(3,SwingConstants.CENTER);
    private int index = 1;
    private final Client client;

    public RoundPanel(MainFrame frame, Client client) {
        this.client = client;
        setLayout(new BorderLayout());

        summaryLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(summaryLabel, BorderLayout.CENTER);

        summaryTextArea.setFont(new Font("Arial", Font.BOLD, 24));
        add(summaryTextArea, BorderLayout.NORTH);

//        JButton nextButton = new JButton("Nästa rond");
//        nextButton.setFont(new Font("Arial", Font.PLAIN, 18));

//        nextButton.addActionListener(e -> {
//            goToNextRound();
//        });

//        add(nextButton, BorderLayout.SOUTH);
    }

    public void setSummaryText(String summaryText) {
        summaryLabel.setText(summaryText);
    }
    public void setRoundScore(String score){

        summaryTextArea.append("Rond " + index +" "+ score + "\n");
        index++;
    }

}
