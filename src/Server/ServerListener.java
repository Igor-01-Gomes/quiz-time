package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {
    public ServerListener() {

        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            while (true) {
                Socket socket = serverSocket.accept();
                Server server = new Server(socket);
                server.start();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    void main() {
    }
}
