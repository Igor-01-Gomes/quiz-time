package Client;

import gui.MainFrame;

import java.io.*;
import java.net.Socket;

public class QuizClient {

    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    private final MainFrame frame;

    public QuizClient(String host, int port, MainFrame frame) throws IOException {
        this.frame = frame;

        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        new Thread(this::listen).start();
    }

    public void sendRoundResult(int correct, int total) {
        out.println("ROUND:" + correct + ":" + total);
    }

    public void requestFinalResults() {
        out.println("FINAL");
    }

    private void listen() {
        try {
            while (true) {
                String msg = in.readLine();
                if (msg == null) break;

                // Ex: ROUNDUPDATE:3:2
                if (msg.startsWith("ROUNDUPDATE:")) {
                    String[] parts = msg.split(":");
//                    int my = Integer.parseInt(parts[1]);
//                    int opp = Integer.parseInt(parts[2]);
                    int myCorrect = Integer.parseInt(parts[1]);
                    int myTotal   = Integer.parseInt(parts[2]);
                    int oppCorrect = Integer.parseInt(parts[3]);
                    int oppTotal   = Integer.parseInt(parts[4]);

                    frame.getRoundPanel().setRoundResults(myCorrect, myTotal, oppCorrect, oppTotal);
                    frame.showPanel("round");
                }

                // Ex: FINAL:10:8
                if (msg.startsWith("FINAL:")) {
                    String[] parts = msg.split(":");
                    int my = Integer.parseInt(parts[1]);
                    int opp = Integer.parseInt(parts[2]);

                   // frame.showFinal(my, opp);
                    frame.showPanel("final");
                }
            }
        } catch (IOException e) {
            System.out.println("Lost connection");
        }
    }
}
