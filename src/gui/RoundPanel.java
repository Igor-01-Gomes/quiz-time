package gui;

import Client.Client;

import javax.swing.*;
import java.awt.*;

public class RoundPanel extends JPanel {

    private JLabel summaryLabel = new JLabel("", SwingConstants.CENTER);
    private JTextArea summaryArea = new JTextArea(5, 20);

    private int index = 1;
    private final Client client;

    public RoundPanel(MainFrame frame, Client client) {
        this.client = client;
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        summaryLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));

        summaryArea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        summaryArea.setEditable(false);
        summaryArea.setOpaque(false);

        JButton next = new JButton("Nästa rond");
        next.setFont(new Font("Segoe UI", Font.BOLD, 20));
        next.setBackground(new Color(180, 220, 180));
        next.setFocusPainted(false);

        next.addActionListener(e -> client.sendToNextRound());

        add(summaryLabel, BorderLayout.NORTH);
        add(summaryArea, BorderLayout.CENTER);
        add(next, BorderLayout.SOUTH);
    }

    public void setSummaryText(String text) {
        summaryLabel.setText(text);
    }

    public void setRoundScore(String score) {
        String[] parts = score.split("-");

        int my = Integer.parseInt(parts[0]);
        int opponent = Integer.parseInt(parts[1]);

        String text =
                "Rond " + index + ":\n" +
                        "  Dina poäng = " + my + "\n" +
                        "  Motståndarens poäng = " + opponent + "\n\n";
        summaryArea.append(text);
        index++;
    }

}
