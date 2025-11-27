package Server;

public class Questions {
    private String questionText;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;
    private int correctIndex;

    public Questions(String questionText, String optionOne, String optionTwo, String optionThree, String optionFour, int correctIndex) {
        this.questionText = questionText;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
        this.correctIndex = correctIndex;
    }

    public String getQuestionText() {
        return questionText;
    }
    public String getOptionOne() {
        return optionOne;
    }
    public String getOptionTwo() {
        return optionTwo;
    }
    public String getOptionThree() {
        return optionThree;
    }
    public String getOptionFour() {
        return optionFour;
    }
    public int getCorrectIndex() {
        return correctIndex;
    }

}
