package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerPlayer extends Thread {
    Protocol p = new Protocol();
    Game game;
    Socket socket;
    BufferedReader in;
    PrintWriter out;

    public ServerPlayer(Socket socket, Game game) {
        this.socket = socket;
        this.game = game;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        try {
            if (game.currentPlayer == this) {
                out.println("Välj kategori");
                String chosenCategory = in.readLine();
                game.setCategory();
                /*
                   0.Hämta frågor från Game av vald kategori
                   1.Svara på frågorna från nuvarande kategori
                   4.Skicka över spelet till motståndaren
                 */
            }
            while (true) {
               String input1 = in.readLine();
               if (input1.startsWith("Your turn")) {
                   /*
                   0.Hämta frågor från Game av vald kategori
                   1.Svara på frågorna från nuvarande kategori
                   2.Välja en ny kategori
                   3.Spela nya kategorin
                   4.Skicka över spelet till motståndaren
                   */
               }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

