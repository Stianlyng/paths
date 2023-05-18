package edu.ntnu.g60.models.passage;

public class PassageManager {
    
    private static PassageManager instance = null;
    private Passage passage;

    private PassageManager() {}

    public static PassageManager getInstance() {
        if(instance == null) {
            instance = new PassageManager();
        }
        return instance;
    }

    public void setPassage(Passage passage) {
        this.passage = passage;
    }

    public Passage getPassage() {
        return passage;
    }
}