package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client extends JFrame {
    PrintWriter out;
    BufferedReader in;
    Socket socket;

    public Client() {
        try {
            socket = new Socket("localhost", 8888);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


//            Scanner sc = new Scanner(System.in);

//            String categoryQuestion;
//            String answer;
//
//            categoryQuestion = in.readLine();
//            System.out.println(categoryQuestion);
//
//            while ((answer = sc.nextLine()) != null) {
//                out.println(answer);
//                System.out.println("Sent to Server: " + answer);
//
//                String result = in.readLine();
//                if (result == null) {
//                    System.out.println("An error occurred.");
//                    break;
//                }
//                System.out.println(result);
//            }

        } catch (IOException e) {
            System.out.println("Unexpected error " + e.getMessage());
        }
    }
    public void play (){
        String response;
        try {
            while (true) {
                response = in.readLine();
            }

        }catch (Exception e){
            System.out.println("Unexpected error ");
        }finally{
            out.close();
        }
    }

    public static void main() {
//        new gui.MainFrame();
        new Client().play();
    }
}
