package gui;

import javax.swing.*;
import java.awt.*;

public class RoundPanel extends JPanel {

    private JLabel summaryLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel roundLabel = new JLabel("", SwingConstants.CENTER);

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

    public void setRoundResults(int myCorrect, int myTotal, int oppCorrect, int oppTotal) {
        summaryLabel.setText(
                "<html><center>" +
                        "Du fick " + myCorrect + "/" + myTotal + "<br>" +
                        "Andra spelaren fick " + oppCorrect + "/" + oppTotal +
                        "</center></html>"
        );
    }
    public void setSummaryText(String text) {
        summaryLabel.setText(text);
    }
}
