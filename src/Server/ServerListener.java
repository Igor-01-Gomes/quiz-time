package Server;

import java.net.ServerSocket;

public class ServerListener implements Runnable {

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server started");

            while (true) {
                Game game = new Game();

                System.out.println("Waiting for player 1");
                ServerPlayer p1 = new ServerPlayer(serverSocket.accept(), game);
                game.setPlayer1(p1);

                System.out.println("Waiting for player 2");
                ServerPlayer p2 = new ServerPlayer(serverSocket.accept(), game);
                game.setPlayer2(p2);

                p1.setOpponent(p2);
                p2.setOpponent(p1);

                p1.start();
                p2.start();

                System.out.println("Game started!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
