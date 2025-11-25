package Server;

public class Protocol {
    private final int CONNECTED = 0;
    private final int INTHELOOP = 1;
    Database db = new Database();
    protected int state = CONNECTED;

    public String getOutput (String fromCLient) {
        if (state == CONNECTED) {
            state = INTHELOOP;
            return "Fråga :" + db.getQuestion() +"1." + db.getOptionOne() + "2." + db.getOptionTwo() +"\n"+ "3." + db.getOptionThree() + "4." + db.getOptionFour();
        } else if (state == INTHELOOP) {
            return db.getIfCorrect (fromClient.trim());

        }
        return "Something went wrong!";
    }

}
