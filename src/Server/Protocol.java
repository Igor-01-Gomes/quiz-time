package Server;

public class Protocol {
    Database db = new Database();
    private String currentCategory = null;
    private Questions currentQuestions = null;

    public String OutputCategory (String fromClient) {
                if (fromClient == null || !fromClient.startsWith("CATEGORY;"))
                    return "ERROR: Expected CATEGORY";
                currentCategory = fromClient.split(";")[1].trim();
                    return "KATEGORI OK;" + currentCategory;
    }

    public String OutputGetQuestion() {
                if (currentCategory == null)
                    return "Error: no category selected";

                currentQuestions = db.getNextQuestions(currentCategory);
                if (currentQuestions == null)
                    return "INGA FLER FRÅGOR";

                return "QUESTION;" + currentQuestions.getQuestionText() + ";"
                        + currentQuestions.getOptionOne() + ";"
                        + currentQuestions.getOptionTwo() + ";"
                        + currentQuestions.getOptionThree() + ";"
                        + currentQuestions.getOptionFour();
    }

    public String outPutAnswer(String fromClient) {
                    if (currentQuestions == null)
                        return "Error: No question";
                String result = db.getIfCorrect(fromClient.trim(), currentQuestions);
                return "RESULTAT;" + result;
    }

    public Questions getCurrentQuestion() {
        return currentQuestions;
    }

    public String formatCurrentQuestion() {
        if (currentQuestions == null) {
            return "Error: No question";
        }

        return "QUESTION;" + currentQuestions.getQuestionText() + ";"
                + currentQuestions.getOptionOne() + ";"
                + currentQuestions.getOptionTwo() + ";"
                + currentQuestions.getOptionThree() + ";"
                + currentQuestions.getOptionFour();
    }
}
