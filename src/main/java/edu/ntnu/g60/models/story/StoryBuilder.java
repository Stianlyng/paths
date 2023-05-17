package edu.ntnu.g60.models.story;

import java.util.HashMap;

import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;

/**
 * The StoryBuilder class is used to build a story.
 * It contains the title, the opening passage, and the passages.
 *
 * @author Stian Lyng
 */
public class StoryBuilder {

  private String title;
  private Passage openingPassage;
  private final HashMap<Link, Passage> passages = new HashMap<>();

  public StoryBuilder setTitle(String title) {
    this.title = title;
    return this;
  }

  public StoryBuilder setOpeningPassage(Passage openingPassage) {
    this.openingPassage = openingPassage;
    return this;
  }

  public StoryBuilder addPassage(Passage passage) {
    Link link = new Link(passage.getTitle(), passage.getTitle());
    this.passages.put(link, passage);
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