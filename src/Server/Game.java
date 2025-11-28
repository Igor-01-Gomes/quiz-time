package Server;

public class Game {
    private Protocol p = new Protocol();
    private String currentCategory;
    private ServerPlayer currentPlayer;


    public Game() {
    }

    public void setCategory(String category) {
        currentCategory = category;
    }

    public String getCurrentCategory() {
        return p.getOutput("CATEGORY;" + currentCategory);
    }

    public void setCurrentPlayer(ServerPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;

    }

    public ServerPlayer getCurrentPlayer() {
        return currentPlayer;
    }
    public String sendQuestion() {
        String question = p.getOutput("QUESTION;");
        if(question.contains("INGA FLER FRÅGOR")){
            return null;
        }
        return question;
    }
    public String sendAnswer(String answer) {
        String qAnswer = p.getOutput(answer);
        return qAnswer;
    }

}
