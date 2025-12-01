package gui;

import Server.Questions;
import Server.QuestionRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class QuestionPanel extends JPanel {

    private MainFrame frame;

    private List<Questions> questions = QuestionRepository.getQuestions();
    private int currentIndex = 0;

    private int correctAnswers = 0;
    private int totalAnswered = 0;
    private int questionInRound = 0;
    private int questionsPerRound = 2;
    private int currentRound = 1;

    private JLabel questionLabel = new JLabel("", SwingConstants.CENTER);
    private JButton[] answerButtons = new JButton[4];

    public QuestionPanel(MainFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());

        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(questionLabel, BorderLayout.NORTH);

        JPanel answersPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JButton();
            answersPanel.add(answerButtons[i]);

            int finalI = i;
            answerButtons[i].addActionListener(e -> handleAnswer(finalI));
        }

        add(answersPanel, BorderLayout.CENTER);

        loadQuestion();
    }

    private void handleAnswer(int selectedIndex) {
        Questions q = questions.get(currentIndex);
        int correct = q.getCorrectIndex();

        totalAnswered++;

        if (selectedIndex == correct) {
            correctAnswers++;
        }

        nextQuestion();
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
        questionInRound++;

        if (questionInRound >= questionsPerRound) {

            int total = totalAnswered;
            int correct = correctAnswers;

            String text = "<html><center>Rond " + currentRound + " klar!<br>" +
                    "Du fick " + correct + " av " + total + " rätt.</center></html>";

            RoundPanel round = frame.getRoundPanel();
            round.setSummaryText(text);

            currentRound++;
            questionInRound = 0;
            correctAnswers = 0;
            totalAnswered = 0;

            frame.showPanel("round");
            return;
        }

        currentIndex++;
        if (currentIndex >= questions.size()) {
            currentIndex = 0;
        }

        loadQuestion();
    }
}
