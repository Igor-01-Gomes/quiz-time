package Server;

import java.util.*;

public class Database {
    private Map<String, List<Questions>> data = new HashMap<>();
    private Map<String, Integer> categoryIndex = new HashMap<>();

    public Database() {
        data.put("Bilar", List.of(
                new Questions("Vilket bilmärke är från Tyskland?","Toyota", "Volvo", "Porsche", "Ford", 3),
                new Questions("Vilket land tillverkar världens snabbaste bil?", "Tyskland", "Italien", "USA", "Sverige", 4)
                ));
        data.put("Musik", List.of(
                new Questions("Vilken artist är känd för låten Nights?", "Frank Ocean", "Dominic Fike", "Billie Eilish", "Olivia Dean", 1),
                new Questions("Vad heter Fleetwood Macs mest populära album?", "Californication", "Tango in the night", "Rumours", "The Dance", 3)
                ));
        data.put("Geografi", List.of(
                new Questions("I vilken världsdel ligger Alperna?", "Nordamerika","Sydamerika", "Asien", "Europa", 4),
                new Questions("Vilket land är mest känt för surfing?", "Costa Rica", "Austrailen", "Sri Lanka", "Portugal", 2)
                ));

        for (String category : data.keySet()) {
            categoryIndex.put(category, 0);
        }
    }
    public Questions getNextQuestions(String category) {

        List<Questions> questions = data.get(category);
        int index = categoryIndex.get(category);

        if (index >= questions.size()) {
            return null;
        }

        Questions q = questions.get(index);
        categoryIndex.put(category, index + 1);
        return q;
    }
    public String getIfCorrect(String answer, Questions q) {
        int guess = Integer.parseInt(answer);
        return (guess == q.getCorrectIndex()) ? "Rätt" : "Fel";
    }

    public Set<String> getAllCategories() {
        return data.keySet();
    }

    public Map<String, List<Questions>> getData() {
        return data;
    }
}