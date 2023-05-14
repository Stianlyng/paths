package edu.ntnu.g60.models.story;
import java.util.Map;

import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.utils.Save;

import java.util.HashMap;
import java.io.Serializable;
import java.util.Collection;

/**
 * The Story class represents the story of the game.
 * It contains the title, the opening passage, and the passages of the story.
 */
public class Story implements Serializable{

  private String title;
  private Map<String, Passage> passages;
  private Passage openingPassage;

  /**
   * Constructor for the Story class.
   * @param title The title of the story.
   * @param openingPassage The opening passage of the story.
   * @throws IllegalArgumentException if title is null or blank.
   * @throws IllegalArgumentException if openingPassage is null.
   */
  Story(String title, Passage openingPassage) throws IllegalArgumentException {
    if (title == null || title.isBlank()) throw new IllegalArgumentException("Title cannot be null or blank.");
    if (openingPassage == null) throw new IllegalArgumentException("Opening passage cannot be null.");
    this.title = title;
    this.openingPassage = openingPassage;
    this.passages = new HashMap<String, Passage>();
  }

  /**
   * Copy constructor for the Story class.
   *
   * @param other The story to copy.
   */
  public Story(Story other) {
      this.title = other.title;
      this.openingPassage = new Passage(other.openingPassage); // Assuming Passage has a copy constructor
      this.passages = new HashMap<>();
      for (Map.Entry<String, Passage> entry : other.passages.entrySet()) {
          this.passages.put(entry.getKey(), new Passage(entry.getValue())); // Assuming Passage has a copy constructor
      }
  }

  /**
   * Adds a passage to the story.
   * @param passage The passage to add.
   * @throws IllegalArgumentException if passage is null.
   */

  public void addPassage(Passage passage) throws IllegalArgumentException {
    if (passage == null) throw new IllegalArgumentException("Passage cannot be null.");
    String passageTitle = passage.getTitle();
    this.passages.put(passageTitle, passage);
  }

 
  public String getTitle() {
    return this.title;
  }

  public Passage getOpeningPassage() {
    return this.openingPassage;
  }

 public Passage getPassage(String title) {
      return this.passages.get(title);
  }

  public Collection<Passage> getPassages() {
    return this.passages.values();
  }

  void addAllPassages(Map<String, Passage> passages) {
    if (passages != null) {
      this.passages.putAll(passages);
    }
  }

  @Override
  public String toString() {
    return "Story{" +
      " title='" + getTitle() + "'" +
      ", openingPassage='" + getOpeningPassage() + "'" +
      ", passages='" + getPassages() + "'" +
      "}";
  }
  
}
