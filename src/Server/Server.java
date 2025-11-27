package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Server extends Thread {
    Protocol p = new Protocol();
    Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            out.println(p.getOutput(null));
            String answer;
            while ((answer = in.readLine()) != null) {
                String qAnswer = p.getOutput(answer);
                out.println(qAnswer);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

