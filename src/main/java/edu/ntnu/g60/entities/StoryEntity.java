package edu.ntnu.g60.entities;

import java.util.List;

/**
 * This class represents the structure of a story in the JSON file.
 * It is used by the StoryParser to parse the JSON file.
 *
 * @see StoryParser
 * @author Stian Lyng
 */
public class StoryEntity {
    private String title;
    private List<PassageEntity> passages;

    /**
     * Gets the title of the story.
     *
     * @return A string representing the title of the story.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the story.
     *
     * @param title A string representing the title of the story.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the list of passages in the story.
     *
     * @return A list of PassageEntity objects representing the passages in the story.
     */
    public List<PassageEntity> getPassages() {
        return passages;
    }

    /**
     * Sets the list of passages in the story.
     *
     * @param passages A list of PassageEntity objects representing the passages in the story.
     */
    public void setPassages(List<PassageEntity> passages) {
        this.passages = passages;
    }
}