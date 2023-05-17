package edu.ntnu.g60;

import edu.ntnu.g60.models.passage.Passage;

public class TEMP_CURRENT_PASSAGE {
    private static TEMP_CURRENT_PASSAGE instance = null;
    private Passage passage;

    private TEMP_CURRENT_PASSAGE() {}

    public static TEMP_CURRENT_PASSAGE getInstance() {
        if(instance == null) {
            instance = new TEMP_CURRENT_PASSAGE();
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