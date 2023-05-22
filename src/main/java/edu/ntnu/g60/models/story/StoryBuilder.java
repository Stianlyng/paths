package edu.ntnu.g60.models.story;

import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import java.util.HashMap;

/**
 * The StoryBuilder class is used to build a story.
 * It contains the title, the opening passage, and the passages.
 *
 * @author Stian Lyng
 */
public class StoryBuilder {

  /**
   * The title of the story.
   */
  private String title;

  /**
   * The opening passage of the story.
   */
  private Passage openingPassage;

  /**
   * The passages of the story.
   */
  private final HashMap<Link, Passage> passages = new HashMap<>();

  /**
   * Sets the title of the story.
   * @param title The title of the story.
   * @return The StoryBuilder object.
   */
  public StoryBuilder setTitle(String title) {
    this.title = title;
    return this;
  }

  /**
   * Sets the opening passage of the story.
   * @param openingPassage The opening passage of the story.
   * @return The StoryBuilder object.
   */
  public StoryBuilder setOpeningPassage(Passage openingPassage) {
    this.openingPassage = openingPassage;
    return this;
  }

  /**
   * Adds a passage to the story.
   * For adding single passages.
   *
   * @param passage The passage to add.
   * @return The StoryBuilder object.
   */
  public StoryBuilder addPassage(Passage passage) {
    Link link = new Link(passage.getTitle(), passage.getTitle());
    this.passages.put(link, passage);
    return this;
  }

  /**
   * Builds the story.
   *
   * @throws IllegalArgumentException if title is null or blank.
   * @throws IllegalArgumentException if openingPassage is null.
   * @return The Story object.
   */
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
