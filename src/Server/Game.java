package Server;

public class Game {

    private ServerPlayer player1;
    private ServerPlayer player2;

    private Integer roundScore1 = null;
    private Integer roundScore2 = null;

    private Integer roundCorrect1 = null;
    private Integer roundCorrect2 = null;

    private Integer roundTotal1 = null;
    private Integer roundTotal2 = null;


    private Integer totalScore1 = 0;
    private Integer totalScore2 = 0;

    public void setPlayer1(ServerPlayer p) { this.player1 = p; }
    public void setPlayer2(ServerPlayer p) { this.player2 = p; }

    public ServerPlayer getPlayer1() { return player1; }
    public ServerPlayer getPlayer2() { return player2; }

    public synchronized void setRoundScore(ServerPlayer p, int correct, int total) {
        if (p == player1) {
            roundCorrect1 = correct;
            roundTotal1 = total;
        } else {
            roundCorrect2 = correct;
            roundTotal2 = total;
        }
    }


    public synchronized int getRoundScore(ServerPlayer p) {
        return (p == player1) ? roundScore1 : roundScore2;
    }

    public synchronized int getOpponentRoundScore(ServerPlayer p) {
        return (p == player1) ? roundScore2 : roundScore1;
    }

    public synchronized void addToTotal(ServerPlayer p, int score) {
        if (p == player1) totalScore1 += score;
        else totalScore2 += score;
    }

    public synchronized int getTotal(ServerPlayer p) {
        return (p == player1) ? totalScore1 : totalScore2;
    }

    public synchronized int getOpponentTotal(ServerPlayer p) {
        return (p == player1) ? totalScore2 : totalScore1;
    }

    public synchronized int getRoundCorrect(ServerPlayer p) {
        return (p == player1) ? roundCorrect1 : roundCorrect2;
    }

    public synchronized int getRoundTotal(ServerPlayer p) {
        return (p == player1) ? roundTotal1 : roundTotal2;
    }

    public synchronized boolean bothRoundDone() {
        return roundCorrect1 != null && roundCorrect2 != null;
    }

    public synchronized void clearRound() {
        roundCorrect1 = roundCorrect2 = null;
        roundTotal1 = roundTotal2 = null;
    }
}



//
//package Server;
//
//public class Game {
//
//    private Protocol protocol = new Protocol();
//    private PropertiesC properties = new PropertiesC();
//    private ServerPlayer currentPlayer;
//    private String currentCategory;
//    private Questions currentQuestion;
//    private int currentQuestionNumber = 0;
//    private final int questionsPerRound;
//    private int answersOnCurrentQuestion = 0;
//
//    public Game() {
//        this.questionsPerRound = properties.getQuestionPerRound();
//    }
//
//    public void setCategory(String command) {
//        this.currentCategory = command.split(";")[1];
//        this.currentQuestionNumber = 0;
//        this.currentQuestion = null;
//        this.answersOnCurrentQuestion = 0;
//        protocol.OutputCategory(command);
//    }
//
//    public String getCurrentCategory() {
//        return currentCategory;
//    }
//
//    public void setCurrentPlayer(ServerPlayer currentPlayer) {
//        this.currentPlayer = currentPlayer;
//    }
//
//    public ServerPlayer getCurrentPlayer() {
//        return currentPlayer;
//    }
//
//    public String sendQuestion() {
//
//        if (currentQuestion == null) {
//
//            if (currentQuestionNumber >= questionsPerRound) {
//                return null;
//            }
//
//            String questionMessage = protocol.OutputGetQuestion();
//
//            if (questionMessage.startsWith("INGA FLER FRÅGOR")) {
//                return null;
//            }
//
//            currentQuestion = protocol.getCurrentQuestion();
//            currentQuestionNumber++;
//            answersOnCurrentQuestion = 0;
//
//            return questionMessage;
//        }
//
//        return protocol.formatCurrentQuestion();
//    }
//
//    public void clearCurrentQuestion() {
//        this.currentQuestion = null;
//        this.answersOnCurrentQuestion = 0;
//    }
//
//    public String sendAnswer(String answer) {
//        String result = protocol.outPutAnswer(answer);
//
//        if (currentQuestion != null) {
//            answersOnCurrentQuestion++;
//
//            if (answersOnCurrentQuestion >= 2) {
//                clearCurrentQuestion();
//            }
//        }
//
//        return result;
//    }
//    public Questions getCurrentQuestion() {
//        return currentQuestion;
//    }
//    public void changePlayer(ServerPlayer newPlayer) {
//        currentPlayer = newPlayer;
//        currentPlayer.opponentDone();
//    }
//    public boolean shallChooseNewCategory() {
//        return currentQuestionNumber >= questionsPerRound && currentQuestion == null;
//    }
//    /*
//    * KOLL OM SISTA RUNDAN ÄR SPELAD
//    * */
//}
