package edu.ntnu.g60.models.story;

import java.util.Map;

import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;

import java.util.HashMap;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The Story class represents the story of the game.
 * It contains the title, the opening passage, and the passages of the story.
 *
 * @author Stian Lyng
 */
public class Story implements Serializable {

  private final String title;
  private final HashMap<Link, Passage> passages;
  private final Passage openingPassage;

  /**
   * Constructor for the Story class.
   * 
   * @param title          The title of the story.
   * @param openingPassage The opening passage of the story.
   * @throws IllegalArgumentException if title is null or blank.
   * @throws IllegalArgumentException if openingPassage is null.
   */
  Story(String title, Passage openingPassage) throws IllegalArgumentException {
    if (title == null || title.isBlank())
      throw new IllegalArgumentException("Title cannot be null or blank.");
    if (openingPassage == null)
      throw new IllegalArgumentException("Opening passage cannot be null.");
    this.title = title;
    this.openingPassage = openingPassage;
    this.passages = new HashMap<Link, Passage>();
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
    for (Map.Entry<Link, Passage> entry : other.passages.entrySet()) {
      this.passages.put(entry.getKey(), new Passage(entry.getValue())); // Assuming Passage has a copy constructor
    }
  }

  /**
   * The title of the story.
   * 
   * @return The title of the story.
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * The opening passage of the story.
   * 
   * @return The opening passage of the story.
   */
  public Passage getOpeningPassage() {
    return this.openingPassage;
  }

  /**
   * Gets the passage of the story with the given link
   * 
   * @param link The link of the passage to get.
   * @return The passage with the given link.
   */
  public Passage getPassage(Link link) {
    return this.passages.get(link);
  }

  /**
   * Gets all the passages of the story.
   * 
   * @return All the passages of the story.
   */
  public Collection<Passage> getPassages() {
    return new ArrayList<>(this.passages.values());
  }

  /**
   * Adds a passage to the story.
   * 
   * @param passage The passage to add.
   * @throws IllegalArgumentException if passage is null.
   */

  public void addPassage(Passage passage) throws IllegalArgumentException {
    if (passage == null)
      throw new IllegalArgumentException("Passage cannot be null.");
    Link link = new Link(passage.getTitle(), passage.getTitle());
    this.passages.put(link, passage);
  }

  /**
   * Adds all the passages to the story.
   * 
   * @param passages The passages to add.
   * @throws IllegalArgumentException if passages is null.
   */
  void addAllPassages(Map<Link, Passage> passages) {
    if (passages != null) {
      this.passages.putAll(passages);
    }
  }

  /**
   * Checks if any passage in this story links to the given passage.
   *
   * @param passage the passage to check.
   * @return true if any passage links to the given passage, false otherwise.
   */
  private boolean passageHasLink(Passage passage) {
    for (Passage otherPassage : passages.values()) {
      for (Link link : otherPassage.getLinks()) {
        if (link.getReference().equals(passage.getTitle())) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Deletes a passage from the story.
   * It should not be possible to remove the passage if there are other passages
   * that link to it.
   * 
   * @param link The link of the passage to delete.
   * @throws IllegalArgumentException if the passage cannot be removed.
   */
  public void deletePassage(Link link) {
    Passage passage = getPassage(link);
    if (passageHasLink(passage)) {
      throw new IllegalArgumentException("Cannot delete a passage that is linked to by other passages.");
    }
    passages.remove(link);
  }

  /**
   * Finds and returns a list of dead links. A link is dead if it refers to a
   * passage that does not exist in passages.
   *
   * It ignores the links to the passages "End Game" and "Game Over".
   * @return a list of dead links.
   */
  public List<Link> getBrokenLinks() {
    List<Link> brokenLinks = new ArrayList<>();

    for (Passage passage : passages.values()) {
        for (Link link : passage.getLinks()) {
            if (link.getReference().equalsIgnoreCase("End Game")) {
                continue;
            }
            if (link.getReference().equalsIgnoreCase("Game Over")) {
                continue;
            }
            boolean linkExists = passages.keySet().stream()
                .anyMatch(mapLink -> mapLink.getReference().equals(link.getReference()));

            if (!linkExists) {
                brokenLinks.add(link);
            }
        }
    }
    return brokenLinks;
  }

  /**
   * Overrides the toString method.
   * 
   * @return A string representation of the story.
   */
  @Override
  public String toString() {
    return "Story{" +
        " title='" + getTitle() + "'" +
        ", openingPassage='" + getOpeningPassage() + "'" +
        ", passages='" + getPassages() + "'" +
        "}";
  }

}