package Server;

public class Launcher {
    public static void main(String[] args) {

        // Start server
        new Thread(new Server.ServerListener()).start();

        // Vänta så servern hinner starta
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}

        // Start 2 GUI–klienter
        new Thread(() -> new gui.MainFrame()).start();
        new Thread(() -> new gui.MainFrame()).start();
    }
}
