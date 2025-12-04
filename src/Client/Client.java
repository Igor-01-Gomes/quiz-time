package Client;

import gui.MainFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;
    private MainFrame gui;


    public Client() {
        try {
            socket = new Socket("localhost", 8888);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            System.out.println("Unexpected error " + e.getMessage());
        }
    }

    public void setGui(MainFrame gui) {
        this.gui = gui;
    }

    public void play() {

        if (socket == null || in == null || out == null) {
            System.out.println("Connection error");
            return;
        }

        try {
            String response;

            while ((response = in.readLine()) != null) {

                if (response.startsWith("DECIDE;")) {
                    if (gui != null) {
                        gui.showCategoryChoice();
                    }

                } else if (response.startsWith("QUESTION;")) {
                    String[] parts = response.split(";");
                    if (parts.length >= 6 && gui != null) {
                        String question = parts[1];
                        String[] options = new String[]{
                                parts[2], parts[3], parts[4], parts[5]
                        };

                        gui.showQuestion(question, options);
                    }

                } else if (response.startsWith("RESULTAT;")) { // Ska sätta färger på knappar
                    String result = response.split(";", 2)[1];
                    if (gui != null) {
                        gui.showResult(result);
                    }

                } else if (response.startsWith("ROUNDOVER;")) { // Ska visa roundpanel och poängställning efter runda
                    String roundresult = response.split(";", 2)[1];
                    if (gui != null) {
                        gui.showRoundResult(roundresult);
                    }

                } else if (response.startsWith("END;")) { // Ska visa spelets result
                    String summary = response.split(";", 2)[1];
                    if (gui != null) {
                        gui.showEnd(summary);
                    }
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Unexpected error: " + e.getMessage());

        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException ignored) {
            }

            if (out != null)
                out.close();

            try {
                if (socket != null)
                    socket.close();
            } catch (IOException ignored) {
            }
        }
    }

    public void sendCategory(String category) {
        out.println("CATEGORY;" + category);
        out.flush();
    }

    public void sendAnswer(int index) {
        out.println("ANSWER;" + index);
        out.flush();
    }

//    public void sendToNextRound() {
//        out.println("NEXTROUND;");
//        out.flush();
//    }

    public static void main(String[] args) {
        Client client = new Client();

        MainFrame frame = new MainFrame(client);
        client.setGui(frame);

        client.play();
    }
}