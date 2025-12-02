package Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesC {

    private Properties propertiesC = new Properties();

    public PropertiesC() {

        try (FileInputStream fs = new FileInputStream("quiz.properties")) {
            propertiesC.load(fs);
            return;
        } catch (IOException e1) {
        }

        try (FileInputStream fs = new FileInputStream("src/Server/quiz.properties")) {
            propertiesC.load(fs);
        } catch (IOException e2) {
            throw new RuntimeException(
                    "Kunde inte läsa quiz.properties varken i '.' eller 'src/Server/'",
                    e2
            );
        }
    }

    public int getRounds() {
        return Integer.parseInt(propertiesC.getProperty("runda", "1"));
    }

    public int getQuestionPerRound() {
        return Integer.parseInt(propertiesC.getProperty("frågaPerRunda", "1"));
    }
}
