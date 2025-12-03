package gui;

import Server.Database;
import Server.Questions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class QuestionPanel extends JPanel {

    private MainFrame frame;
    private Database database;

    private List<String> categories;
    private int categoryIndex = 0;

    private Questions currentQuestion;

    private int currentRound = 1;
    private int questionsPerRound = 2;
    private int questionInRound = 0;

    private int correctAnswers = 0;
    private int totalAnswered = 0;

    private JLabel questionLabel = new JLabel("", SwingConstants.CENTER);
    private JButton[] answerButtons = new JButton[4];

    public QuestionPanel(MainFrame frame) {
        this.frame = frame;
        this.database = frame.getDatabase();

        // Hämta kategorier från databasen
        Set<String> categorySet = database.getData().keySet();
        categories = new ArrayList<>(categorySet);

        setLayout(new BorderLayout());

        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(questionLabel, BorderLayout.NORTH);

        JPanel answersPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JButton();
            answersPanel.add(answerButtons[i]);

            int finalI = i;
            answerButtons[i].addActionListener(e -> handleAnswer(finalI));
        }

        add(answersPanel, BorderLayout.CENTER);

        loadNextQuestion();
    }

    private void loadNextQuestion() {
        if (categoryIndex >= categories.size()) {
            categoryIndex = 0;
        }

        String currentCategory = categories.get(categoryIndex);

        currentQuestion = database.getNextQuestions(currentCategory);

        // Om kategorin är slut, hoppa till nästa kategori
//        if (currentQuestion == null) {
//            categoryIndex++;
//            loadNextQuestion();
//            return;
//        }

        questionLabel.setText(currentQuestion.getQuestionText());

        answerButtons[0].setText(currentQuestion.getOptionOne());
        answerButtons[1].setText(currentQuestion.getOptionTwo());
        answerButtons[2].setText(currentQuestion.getOptionThree());
        answerButtons[3].setText(currentQuestion.getOptionFour());
    }

    public void startRound() {
        // ladda nästa kategori om categoryIndex pekar på en ny kategori redan
        loadNextQuestion();
    }

    private void handleAnswer(int selectedIndex) {

        totalAnswered++;
        int correctIndex = currentQuestion.getCorrectIndex() - 1; // Vår DATA ÄR 1-indexerad

        // Lås knapparna
        for (JButton btn : answerButtons) {
            btn.setEnabled(false);
        }

        // Färga knappar
        for (int i = 0; i < 4; i++) {
            answerButtons[i].setOpaque(true);
            answerButtons[i].setBorderPainted(false);

            if (i == correctIndex) {
                answerButtons[i].setBackground(Color.GREEN);
            } else if (i == selectedIndex) {
                answerButtons[i].setBackground(Color.RED);
            }
        }

        if (selectedIndex == correctIndex) {
            correctAnswers++;
        }

        // Vänta lite innan nästa fråga
        Timer timer = new Timer(1000, e -> {
            resetButtons();
            nextQuestion();
        });

        timer.setRepeats(false);
        timer.start();
    }


    private void resetButtons() {
        for (JButton btn : answerButtons) {
            btn.setBackground(null);
            btn.setEnabled(true);
        }
    }

    private void nextQuestion() {
        questionInRound++;

        // Kolla om ronden är slut
        if (questionInRound >= questionsPerRound) {

            String summary = "<html><center>Rond " + currentRound + " klar!" +
                    "<br>Du fick " + correctAnswers + " av " + totalAnswered + " rätt.</center></html>";

            RoundPanel roundPanel = frame.getRoundPanel();
            roundPanel.setSummaryText(summary);

            // Förbered nästa rond
            questionInRound = 0;
            currentRound++;
            totalAnswered = 0;
            correctAnswers = 0;

            // Gå vidare till nästa kategori
            categoryIndex++;

            frame.showPanel("round");
            return;
        }

        // Annars ladda nästa fråga
        loadNextQuestion();
    }
}
