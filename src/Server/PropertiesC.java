package Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesC {

    private Properties propertiesC = new Properties();

    public PropertiesC() {
        try(FileInputStream fs = new FileInputStream("quiz.properties")) {
            propertiesC.load(fs);
        } catch (IOException e) {
            throw new RuntimeException("Kunde inte läsa fil", e);
        }
    }
    public int getRounds() {
        return Integer.parseInt(propertiesC.getProperty("runda", "1"));
    }
    public int getQuestionPerRound() {
        return Integer.parseInt(propertiesC.getProperty("frågaPerRunda", "1"));
    }
}
