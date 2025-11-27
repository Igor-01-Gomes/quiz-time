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

    public ServerPlayer(Socket socket, Game game) {
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
            if (game.getCurrentPlayer() == this) {
                out.println("Välj kategori");
                String chosenCategory = in.readLine();
                game.setCategory(chosenCategory);
                out.println(game.getCurrentCategory());
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

    public void setOpponent(ServerPlayer opponent) {
        this.opponent = opponent;
    }
}

