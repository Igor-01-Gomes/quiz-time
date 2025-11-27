package gui;

import Server.Questions;
import Server.QuestionRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class QuestionPanel extends JPanel {

    private List<Questions> questions = QuestionRepository.getQuestions();
    private int currentIndex = 0;

    private JLabel questionLabel = new JLabel("", SwingConstants.CENTER);
    private JButton[] answerButtons = new JButton[4];

    public QuestionPanel(MainFrame frame) {
        setLayout(new BorderLayout());

        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(questionLabel, BorderLayout.NORTH);

        JPanel answersPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JButton();
            answersPanel.add(answerButtons[i]);

            int finalI = i;
            answerButtons[i].addActionListener(e -> {
                handleAnswer(finalI);
            });
        }

        add(answersPanel, BorderLayout.CENTER);

        loadQuestion();
    }

    private void handleAnswer(int selectedIndex) {
        Questions q = questions.get(currentIndex);

        int correct = q.getCorrectIndex();

        // Färga knappar
        for (int i = 0; i < 4; i++) {
            answerButtons[i].setOpaque(true);
            answerButtons[i].setBorderPainted(false);
            if (i == correct) {
                answerButtons[i].setBackground(Color.GREEN);
            } else if (i == selectedIndex) {
                answerButtons[i].setBackground(Color.RED);
            }
        }

        // Vänta 1 sekund innan nästa fråga så att färgen syns
        Timer timer = new Timer(1000, e -> {
            resetButtonColors();
            nextQuestion();
        });
        timer.setRepeats(false);
        timer.start();


    }

    private void resetButtonColors() {
        for (JButton button : answerButtons) {
            button.setBackground(null); // återställ färg
        }
    }


    private void loadQuestion() {
        Questions q = questions.get(currentIndex);
        questionLabel.setText(q.getQuestionText());

        answerButtons[0].setText(q.getOptionOne());
        answerButtons[1].setText(q.getOptionTwo());
        answerButtons[2].setText(q.getOptionThree());
        answerButtons[3].setText(q.getOptionFour());
    }

    private void nextQuestion() {
        currentIndex++;
        if (currentIndex >= questions.size()) {
            currentIndex = 0;
        }
        loadQuestion();
    }
}
