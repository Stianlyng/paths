package edu.ntnu.g60.models.story;

import java.util.HashMap;
import java.util.Map;

import edu.ntnu.g60.models.passage.Passage;

public class StoryBuilder {
    
  private String title;
  private Map<String, Passage> passages;
  private Passage openingPassage;

  public StoryBuilder setTitle(String title) {
    this.title = title;
    return this;
  }

  public StoryBuilder setOpeningPassage(Passage openingPassage) {
    this.openingPassage = openingPassage;
    return this;
  }

  public StoryBuilder addPassage(Passage passage) {
    if (this.passages == null) {
      this.passages = new HashMap<>();
    }
    this.passages.put(passage.getTitle(), passage);
    return this;
  }

  public Story build() {
    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("Title cannot be null or blank.");
    }
    if (openingPassage == null) {
      throw new IllegalArgumentException("Opening passage cannot be null.");
    }
  
    Story story = new Story(title, openingPassage);
    story.addAllPassages(passages);
    return story;
  }
  
}