package gui;

import Client.Client;

import javax.swing.*;
import java.awt.*;
public class CategoryPanel extends JPanel {

    private final Client client;
    private final MainFrame frame;

    private final String[] categories = {"Bilar", "Musik", "Geografi", "Historia"};

    public CategoryPanel(MainFrame frame, Client client) {
        this.frame = frame;
        this.client = client;

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JLabel title = new JLabel("Välj kategori", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));

        JPanel grid = new JPanel(new GridLayout(4, 1, 15, 15));
        grid.setOpaque(false);

        for (String cat : categories) {
            JButton btn = new JButton(cat);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 22));
            btn.setBackground(new Color(255, 230, 180));
            btn.setFocusPainted(false);

            btn.addActionListener(e -> client.sendCategory(cat));
            grid.add(btn);
        }

        add(title, BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);
    }
}
