package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerPlayer extends Thread {
    private ServerPlayer opponent;
    private final Game game;
    private final BufferedReader in;
    private final PrintWriter out;
    private int score = 0;


    public ServerPlayer(Socket socket, Game game) {
        this.game = game;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void opponentDone(){
        out.println(game.sendQuestion());
    }

    public void run() {
        try {
            if (game.getCurrentPlayer() == this) {
                out.println("DECIDE;");

            }
            while (true) {
                String input1 = in.readLine();
                if(!game.getCurrentPlayer().equals(this)) {
                    return;
                }
                if (input1.startsWith("CATEGORY;")){
                    game.setCategory(input1);
                    out.println(game.sendQuestion());
                }else if (input1.startsWith("ANSWER;")){
                    String answer = game.sendAnswer(input1.split(";")[1]);

                    if (answer.equals("RESULTAT;Rätt")) {
                        score++;
                    }

                    out.println(answer);

                    if (game.isGameOver()) {
                        out.println("GAME;SLUT;" + score + ";" + opponent.score);
                        if (opponent != null) {
                            opponent.out.println("GAME;SLUT;" + opponent.score + ";" + score);
                        }
                        break;
                    }

                    if(game.shallChooseNewCategory()) {
                        out.println("DECIDE;");
                    }else {
                       game.changePlayer(opponent);
                    }
                    /*
                    * OM DET ÄR SISTA RUNDAN ÄR SPELAD SKICKA "END;" SOM KEYWORD
                    * */
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void setOpponent(ServerPlayer opponent) {
        this.opponent = opponent;
    }
}

