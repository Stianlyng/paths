package edu.ntnu.g60.models.passage;

/**
 * Singleton class that manages a Passage object.
 * @author Stian Lyng
 */
public class PassageManager {
    
    private static PassageManager instance = null;
    private Passage passage;

    private PassageManager() {}

    /**
     * Returns the single instance of PassageManager. If it does not exist yet, it creates one.
     *
     * @return The singleton instance of PassageManager.
     */
    public static PassageManager getInstance() {
        if(instance == null) {
            instance = new PassageManager();
        }
        return instance;
    }

    /**
     * Sets the current Passage object managed by this PassageManager.
     *
     * @param passage The Passage object to be managed.
     */
    public void setPassage(Passage passage) {
        this.passage = passage;
    }

    /**
     * Returns the current Passage object managed by this PassageManager.
     *
     * @return The current Passage object.
     */
    public Passage getPassage() {
        return passage;
    }
}