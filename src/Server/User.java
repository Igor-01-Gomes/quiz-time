package Server;

public class User {

    private String username;
    private int score;
    private boolean online;

    public User(String username) {
        this.username = username;
        this.score = 0;
        this.online = true;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public boolean isOnline() {
        return online;
    }

    public void addScore(int amount) {
        this.score += amount;
    }

    public void disconnect() {
        this.online = false;
    }

    @Override
    public String toString() {
        return username + " | Poäng: " + score;
    }
}


