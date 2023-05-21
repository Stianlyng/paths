package edu.ntnu.g60.models.passage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Passage class represents a passage in the story.
 * It contains the title, the content, and the links to other passages.
 *
 * @author Stian Lyng
 */
public class Passage implements Serializable {

  private String title;
  private String content;
  private List<Link> links;
  private String player;
  private String enemy;
  private String background;
  private boolean fightScene;

  /**
   * Constructor for the Passage class.
   * 
   * @param title   The title of the passage.
   * @param content The content of the passage.
   * @param links   The links to other passages from this passage.
   * @throws IllegalArgumentException if title or content is null or blank.
   */
  Passage(String title, String content, String player, String enemy, String background, boolean isFightScene)
      throws IllegalArgumentException {

    if (title == null || title.isBlank())
      throw new IllegalArgumentException("Title cannot be null or blank.");
    if (content == null || content.isBlank())
      throw new IllegalArgumentException("Content cannot be null or blank.");
    this.title = title;
    this.content = content;
    this.player = player;
    this.enemy = enemy;
    this.background = background;
    this.fightScene = isFightScene;
    this.links = new ArrayList<>();
  }

  /**
   * Copy constructor for the Passage class.
   * 
   * @param other The other passage to copy.
   *              todo; add exception
   */
  public Passage(Passage other) {
    this.title = other.title;
    this.content = other.content;
    this.player = other.player;
    this.enemy = other.enemy;
    this.background = other.background;
    this.fightScene = other.fightScene;
    this.links = new ArrayList<>(other.links); // Assuming Link has a suitable copy constructor or is immutable
  }

  /**
   * The player image file
   *
   * @return the name of the player image file
   */
  public String getPlayerImage() {
    return player;
  }

  /**
   * The enemy image file
   *
   * @return the name of the enemy image file
   */
  public String getEnemyImage() {
    return enemy;
  }

  /**
   * The background image file
   *
   * @return the name of the background image file
   */
  public String getBackgroundImage() {
    return background;
  }

  /**
   * Checks if the passage is a fight scene.
   * 
   * @return True if the passage is a fight scene, false otherwise.
   */
  public boolean isFightScene() {
    return fightScene;
  }

  /**
   * Gets the title of the passage.
   * 
   * @return The title of the passage.
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Gets the content of the passage.
   * 
   * @return The content of the passage.
   */
  public String getContent() {
    return this.content;
  }

  /**
   * Gets the links of the passage.
   * 
   * @return The links of the passage.
   */
  public List<Link> getLinks() {
    return this.links;
  }

  /**
   * Adds a link to the passage.
   * 
   * @param link The link to add.
   * @return True if the link was added, false otherwise.
   * @throws IllegalArgumentException if link is null.
   *                                  todo: check if link is already in list
   *                                  todo: check if link is null
   *                                  todo: check if link is to self
   */
  public boolean addLink(Link link) throws IllegalArgumentException {
    if (link == null)
      throw new IllegalArgumentException("Link cannot be null.");
    return this.links.add(link);
  }

  /**
   * Checks if the passage has links.
   * 
   * @return True if the passage has links, false otherwise.
   */
  public boolean hasLinks() {
    return !this.links.isEmpty();
  }

  /**
   * Returns the title, content and number of links of the passage
   * 
   * @return A string representation of the passage.
   */
  @Override
  public String toString() {
    return "Passage{" +
        "title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", links=" + links +
        '}';
  }

  /**
   * Compares this passage to another object.
   * 
   * @param obj The object to compare with this passage.
   * @return True if the object is equal to this passage, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (!(obj instanceof Passage))
      return false;
    Passage passage = (Passage) obj;
    return passage.getTitle().equals(this.title) && passage.getContent().equals(this.content)
        && passage.getLinks().equals(this.links);
  }

  /**
   * The hash code of this passage.
   * 
   * @return The hash code of this passage.
   */
  @Override
  public int hashCode() {
    return Objects.hash(title, content, links);
  }

}
