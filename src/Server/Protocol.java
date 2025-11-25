package Server;

public class Protocol {
    private final int CONNECTED = 0;
    private final int INTHELOOP = 1;

    protected int state = CONNECTED;

    public String getOutput (String fromCLient) {
        if (state == CONNECTED) {
            state = INTHELOOP;
            return "Fråga: ";
        } else if (state == INTHELOOP) {
            return db.getIfCorrect (fromClient.trim());

        }
        return "Something went wrong!";
    }

}
