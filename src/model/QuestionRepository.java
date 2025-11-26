package Server;

import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {

    public static List<Question> getQuestions() {
        List<Question> list = new ArrayList<>();

        list.add(new Question(
                "Vilket år startade andra världskriget?",
                "1914",
                "1939",
                "1945",
                "1962",
                1
        ));

        list.add(new Question(
                "Vilket djur är störst?",
                "Elefant",
                "Blåval",
                "Flodhäst",
                "Isbjörn",
                1
        ));

        return list;
    }
}
