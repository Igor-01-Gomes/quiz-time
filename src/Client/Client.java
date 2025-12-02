package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public Client() {

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        Scanner sc = null;


        try {
            socket = new Socket("localhost", 8888);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sc = new Scanner(System.in);

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


        } finally {
            try {
                if (out != null)
                    out.close();

                if (in != null)
                    in.close();
                } catch (IOException ignored) {}

                try {
                    if (socket != null)
                        socket.close();
                } catch (IOException ignored) {


            }
        }
    }


    public static void main() throws IOException {
//        new gui.MainFrame();
        new Client();
    }
}

