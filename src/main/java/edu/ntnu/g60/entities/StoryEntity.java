package edu.ntnu.g60.entities;

import java.util.List;

public class StoryEntity {
    private String title;
    private List<PassageEntity> passages;

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PassageEntity> getPassages() {
        return passages;
    }

    public void setPassages(List<PassageEntity> passages) {
        this.passages = passages;
    }
}