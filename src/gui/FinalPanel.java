package gui;

import javax.swing.*;
import java.awt.*;

public class FinalPanel extends JPanel {

    private JLabel resultLabel = new JLabel("", SwingConstants.CENTER);

    public FinalPanel(MainFrame frame) {
        setLayout(new BorderLayout());

        resultLabel.setFont(new Font("Arial", Font.BOLD, 26));
        add(resultLabel, BorderLayout.CENTER);

        JButton exit = new JButton("Avsluta");
        exit.addActionListener(e -> System.exit(0));
        add(exit, BorderLayout.SOUTH);
    }

    public void setFinalResults(int my, int opp) {
        resultLabel.setText("<html><center>Slutresultat<br>Du: " +
                my + "<br>Motståndaren: " + opp + "</center></html>");
    }
}
