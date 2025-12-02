package Server;

public class Protocol {
    private final int CONNECTED = 0;
    private final int ASKING = 1;
    private final int ANSWERING = 2;
    protected int state = CONNECTED;

    Database db = new Database();
    private String currentCategory = null;
    private Questions currentQuestions = null;

    public String getOutput (String fromClient) {
        switch (state) {
            case CONNECTED: {
                if (fromClient != null && fromClient.startsWith("CATEGORY;")) {
                    currentCategory = fromClient.split(";")[1].trim();
                    state = ASKING;
                    return "KATEGORI OK;" + currentCategory;
                }
                return "ERROR: Expected CATEGORY";
            }
            case ASKING: {
                currentQuestions = db.getNextQuestions(currentCategory);
                if (currentQuestions == null) {
                    return "INGA FLER FRÅGOR";
                }

                state = ANSWERING;

                return "QUESTION;" + currentQuestions.getQuestionText() + ";"
                        + currentQuestions.getOptionOne() + ";"
                        + currentQuestions.getOptionTwo() + ";"
                        + currentQuestions.getOptionThree() + ";"
                        + currentQuestions.getOptionFour();
            }
            case ANSWERING: {
                String result = db.getIfCorrect(fromClient.trim(), currentQuestions);
                state = ASKING;
                return "RESULTAT;" + result;
            }
        }
        return "ERROR";
    }
    public String getCurrentCategory() {
        return currentCategory;
    }
}


