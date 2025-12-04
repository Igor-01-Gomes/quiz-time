package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//FUNGERAR
public class ServerListener {
    public ServerListener() {

        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            while (true) {
                Game game = new Game();
                ServerPlayer player1 = new ServerPlayer(serverSocket.accept(), game);
                ServerPlayer player2 = new ServerPlayer(serverSocket.accept(), game);
                player1.setOpponent(player2);
                player2.setOpponent(player1);
                game.setCurrentPlayer(player2);
                player1.start();
                player2.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    void main() {
    }
}
