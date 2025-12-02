package Server;

public class Game {
    private Protocol p = new Protocol();
    private ServerPlayer currentPlayer;



    public Game() {
    }

    public void setCategory(String category) {
        p.getOutput("CATEGORY;" +  category);
    }

    public String getCurrentCategory() {
        return p.getCurrentCategory();
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
        return p.getOutput(answer);
    }
}
