package gui;

import Client.Client;

import javax.swing.*;
import java.awt.*;

public class CategoryPanel extends JPanel {

    private final Client client;
    private final MainFrame frame;

    private final JLabel categoryLabel = new JLabel("Välj Kategori",SwingConstants.CENTER);

    private final JButton[] categoryButtons = new JButton[4];

    private final String[] Categories = {"Bilar","Musik","Geografi","Historia"};


    public CategoryPanel(MainFrame frame, Client client) {
        this.frame = frame;
        this.client = client;

        setLayout(new BorderLayout());

        categoryLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(categoryLabel, BorderLayout.NORTH);

        JPanel categoryButtonPanel = new JPanel(new GridLayout(4,1,10,10));

        for (int i = 0; i < 4; i++){
            categoryButtons[i] = new JButton(Categories[i]);
            int index = i;
            categoryButtons[i].addActionListener(e -> handleCategory(index));
            categoryButtonPanel.add(categoryButtons[i]);
        }
        add(categoryButtonPanel, BorderLayout.CENTER);
    }



    private void handleCategory(int selectedIndex) {

//        for (JButton btn : categoryButtons) {
//            btn.setEnabled(false);
//        }
        String category = categoryButtons[selectedIndex].getText();
        client.sendCategory(category);
    }
}
