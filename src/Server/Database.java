package Server;

import java.util.Random;

public class Database {
    private Questions[] questions;
    private Questions currentQuestion;
    private Random rand = new Random();

    public Database() {
        questions = new Questions[]{
                new Questions("Var ligger alperna?", "Afrika", "USA", "Europa", "Asien", 3),
                new Questions("Vilket bilmärke är från Tyskland?", "Ferrari", "Lamborghini", "Volvo", "Porsche", 4)
        };

        }
        public String getQuestion() {
        currentQuestion = questions[rand.nextInt(questions.length)];
        return currentQuestion.getQuestionText();
    }
    public String getOptionOne() {
        return currentQuestion.getOptionOne();
    }
    public String getOptionTwo() {
        return currentQuestion.getOptionTwo();
    }
    public String getOptionThree() {
        return currentQuestion.getOptionThree();
    }
    public String getOptionFour() {
        return currentQuestion.getOptionFour();
    }
    public String getIfCorrect(String answer) {
        try {
            int ans = Integer.parseInt(answer.trim());
            if (ans == currentQuestion.getCorrectIndex()){
                return "Rätt!";
            } else {
                return "Fel! Rätt Svar: " + currentQuestion.getCorrectIndex();
            }
        } catch (Exception e) {
        return "Ange ett tal 1-4";
        }
    }
}
