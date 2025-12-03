package gui;

import javax.swing.*;
import java.awt.*;

public class RoundPanel extends JPanel {

    private JLabel summaryLabel = new JLabel("", SwingConstants.CENTER);

    public RoundPanel(MainFrame frame) {
        setLayout(new BorderLayout());

        summaryLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(summaryLabel, BorderLayout.CENTER);

        JButton nextButton = new JButton("Nästa rond");
        nextButton.setFont(new Font("Arial", Font.PLAIN, 18));

        nextButton.addActionListener(e -> {
            frame.getQuestionPanel().startRound();
            frame.showPanel("question");
        });

        add(nextButton, BorderLayout.SOUTH);
    }

    public void setSummaryText(String text) {
        summaryLabel.setText(text);
    }
}
