package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Server extends Thread {
    Protocol p = new Protocol();
    Socket socket;

    private User user;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {

            user = new User("Gäst");
            System.out.println("Ny användare ansluten: " + user);

            out.println(p.getOutput(null));
            String answer;
            while ((answer = in.readLine()) != null) {
                String qAnswer = p.getOutput(answer);
                out.println(qAnswer);

                if (qAnswer.startsWith("Rätt!")) {
                    user.addScore(1);
                }

                String message = qAnswer + " | Poäng: " + user.getScore();
                out.println(message);

                System.out.println("Uppdaterad användare: " + user);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (user != null) {
                user.disconnect();
                System.out.println("Användare frånkopplad: " + user.getUsername());
            }
        }
    }
}



