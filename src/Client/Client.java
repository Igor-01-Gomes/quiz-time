package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public Client() {
        try (Socket socket = new Socket("localhost", 8888);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner sc = new Scanner(System.in);

        ){

            String question;
            String answer;

            question = in.readLine();
            System.out.println(question);
            while ((answer = sc.nextLine()) != null) {
                out.println(answer);
                System.out.println("Sent to Server: " + answer);

                String result = in.readLine();
                System.out.println("Rätt svar : " + result);
            }

        } catch(
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    void main() {
    }

}
