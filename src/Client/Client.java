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
    JPanel panel = new JPanel();
    JTextArea text = new JTextArea();
    JTextArea questions = new JTextArea();
    PrintWriter out;
    BufferedReader in;
    Socket socket;

    public Client() {
        text.setBackground(Color.GRAY);
        panel.setLayout (new BorderLayout());
        panel.add (questions, BorderLayout.CENTER);
        panel.add (text, BorderLayout.SOUTH);
        add(panel);
        setVisible(true);
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        text.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    out.println(text.getText());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


        try {
            socket = new Socket("localhost", 8888);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        } catch (IOException e) {
            System.out.println("Unexpected error " + e.getMessage());
        }
    }
    public void play (){
        String response;
        try {
            while (true) {
                response = in.readLine();
                questions.append(response + "\n");
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