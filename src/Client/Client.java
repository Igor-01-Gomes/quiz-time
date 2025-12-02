package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public Client() {
        try {
            Socket socket = new Socket("localhost", 8888);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner sc = new Scanner(System.in);

            String categoryQuestion;
            String answer;

            categoryQuestion = in.readLine();
            System.out.println(categoryQuestion);

            while ((answer = sc.nextLine()) != null) {
                out.println(answer);
                System.out.println("Sent to Server: " + answer);

                String result = in.readLine();
                if (result == null) {
                    System.out.println("An error occurred.");
                    break;
                }
                System.out.println(result);
            }

        } catch (IOException e) {
            System.out.println("Unexpected error " + e.getMessage());
        }
    }

    public static void main() {
//        new gui.MainFrame();
        new Client();
    }

}
