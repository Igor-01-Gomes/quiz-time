package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public Client() {
        try (Socket socket = new Socket("localhost", 8888)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String question = in.readLine();
            System.out.println("Question: " + question);

            for (int i = 0; i < 4; i++) {
                String s = in.readLine();
                System.out.println(s);
            }



        } catch(
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    void main() {

    }



}
