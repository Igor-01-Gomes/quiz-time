package Server;

import java.io.*;
import java.net.Socket;

public class ServerPlayer extends Thread {

    private final Socket socket;
    private final Game game;
    private ServerPlayer opponent;

    private final BufferedReader in;
    private final PrintWriter out;

    public ServerPlayer(Socket socket, Game game) throws IOException {
        this.socket = socket;
        this.game = game;

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void setOpponent(ServerPlayer op) {
        this.opponent = op;
    }

    public void send(String msg) {
        out.println(msg);
    }

    @Override
    public void run() {
        try {
            send("CONNECTED");

            while (true) {
                String msg = in.readLine();
                if (msg == null) break;

                // Klient skickar: ROUND:poäng
                if (msg.startsWith("ROUND:")) {
                    String[] parts = msg.split(":");
                    int correct = Integer.parseInt(parts[1]);
                    int total = Integer.parseInt(parts[2]);

                    game.setRoundScore(this, correct, total);
                    game.addToTotal(this, correct);

                    if (game.bothRoundDone()) {
                        int myCorrect = game.getRoundCorrect(this);
                        int myTotal   = game.getRoundTotal(this);

                        int oppCorrect = game.getRoundCorrect(opponent);
                        int oppTotal   = game.getRoundTotal(opponent);

                        send("ROUNDUPDATE:" + myCorrect + ":" + myTotal + ":" + oppCorrect + ":" + oppTotal);
                        opponent.send("ROUNDUPDATE:" + oppCorrect + ":" + oppTotal + ":" + myCorrect + ":" + myTotal);

                        game.clearRound();
                    }
                }

                // Klient skickar: FINAL
                if (msg.equals("FINAL")) {
                    int myTotal = game.getTotal(this);
                    int oppTotal = game.getOpponentTotal(this);

                    send("FINAL:" + myTotal + ":" + oppTotal);
                    opponent.send("FINAL:" + oppTotal + ":" + myTotal);
                }
            }

        } catch (IOException e) {
            System.out.println("Player disconnected.");
        }
    }
}



//package Server;


//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//
//public class ServerPlayer extends Thread {
//    private ServerPlayer opponent;
//    private final Game game;
//    private final BufferedReader in;
//    private final PrintWriter out;
//
//    public ServerPlayer(Socket socket, Game game) {
//        this.game = game;
//        try {
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            out = new PrintWriter(socket.getOutputStream(), true);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public void opponentDone(){
//        out.println(game.sendQuestion());
//    }
//
//    public void run() {
//        try {
//            if (game.getCurrentPlayer() == this) {
//                out.println("DECIDE;");
//
//            }
//            while (true) {
//                String input1 = in.readLine();
//                if(!game.getCurrentPlayer().equals(this)) {
//                    return;
//                }
//                if (input1.startsWith("CATEGORY;")){
//                    game.setCategory(input1);
//                    out.println(game.sendQuestion());
//                }else if (input1.startsWith("ANSWER;")){
//                    String answer = game.sendAnswer(input1.split(";")[1]);
//                    out.println(answer);
//                    if(game.shallChooseNewCategory()) {
//                        out.println("DECIDE;");
//                    }else {
//                       game.changePlayer(opponent);
//                    }
//                    /*
//                    * OM DET ÄR SISTA RUNDAN ÄR SPELAD SKICKA "END;" SOM KEYWORD
//                    * */
//
//                }
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    public void setOpponent(ServerPlayer opponent) {
//        this.opponent = opponent;
//    }
//}
//
