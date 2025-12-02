package Server;

public class Game {

    private Protocol protocol = new Protocol();
    private PropertiesC properties = new PropertiesC();
    private ServerPlayer currentPlayer;
    private String currentCategory;
    private Questions currentQuestion;
    private int currentQuestionNumber = 0;
    private final int questionsPerRound;
    private int answersOnCurrentQuestion = 0;

    public Game() {
        this.questionsPerRound = properties.getQuestionPerRound();
    }

    public void setCategory(String category) {
        this.currentCategory = category;
        this.currentQuestionNumber = 0;
        this.currentQuestion = null;
        this.answersOnCurrentQuestion = 0;
        protocol.OutputCategory("CATEGORY;" + category);
    }

    public String getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentPlayer(ServerPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public ServerPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public String sendQuestion() {

        if (currentQuestion == null) {

            if (currentQuestionNumber >= questionsPerRound) {
                return null;
            }

            String questionMessage = protocol.OutputGetQuestion();

            if (questionMessage.startsWith("INGA FLER FRÅGOR")) {
                return null;
            }

            currentQuestion = protocol.getCurrentQuestion();
            currentQuestionNumber++;
            answersOnCurrentQuestion = 0;

            return questionMessage;
        }

        return protocol.formatCurrentQuestion();
    }

    public void clearCurrentQuestion() {
        this.currentQuestion = null;
        this.answersOnCurrentQuestion = 0;
    }

    public String sendAnswer(String answer) {
        String result = protocol.outPutAnswer(answer);

        if (currentQuestion != null) {
            answersOnCurrentQuestion++;

            if (answersOnCurrentQuestion >= 2) {
                clearCurrentQuestion();
            }
        }

        return result;
    }
    public Questions getCurrentQuestion() {
        return currentQuestion;
    }
}
