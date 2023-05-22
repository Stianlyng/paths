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

  /**
   * The title of the passage.
   */
  private String title;

  /**
   * The content of the passage.
   */
  private String content;

  /**
   * The links to other passages.
   */
  private List<Link> links;

  /**
   * The player image of the passage.
   */
  private String playerImage;

  /**
   * The enemy image of the passage.
   */
  private String enemyImage;

  /**
   * The background image of the passage.
   */
  private String backgroundImage;

  /**
   * The fight status of the passage.
   */
  private boolean isFightScene;

  /**
   * Constructor for the Passage class.
   *
   * @param title   The title of the passage.
   * @param content The content of the passage.
   * @param links   The links to other passages from this passage.
   * @throws IllegalArgumentException if title or content is null or blank.
   */
  Passage(
    String title,
    String content,
    String playerImage,
    String enemyImage,
    String backgroundImage,
    boolean isFightScene
  ) throws IllegalArgumentException {
    if (title == null || title.isBlank()) throw new IllegalArgumentException(
      "Title cannot be null or blank."
    );
    if (content == null || content.isBlank()) throw new IllegalArgumentException(
      "Content cannot be null or blank."
    );
    this.title = title;
    this.content = content;
    this.playerImage = playerImage;
    this.enemyImage = enemyImage;
    this.backgroundImage = backgroundImage;
    this.isFightScene = isFightScene;
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
    this.playerImage = other.playerImage;
    this.enemyImage = other.enemyImage;
    this.backgroundImage = other.backgroundImage;
    this.isFightScene = other.isFightScene;
    this.links = new ArrayList<>(other.links); // Assuming Link has a suitable copy constructor or is immutable
  }

  /**
   * The playerImage image file
   *
   * @return the name of the playerImage image file
   */
  public String getPlayerImage() {
    return playerImage;
  }

  /**
   * The enemyImage image file
   *
   * @return the name of the enemyImage image file
   */
  public String getEnemyImage() {
    return enemyImage;
  }

  /**
   * The backgroundImage image file
   *
   * @return the name of the backgroundImage image file
   */
  public String getBackgroundImage() {
    return backgroundImage;
  }

  /**
   * Checks if the passage is a fight scene.
   *
   * @return True if the passage is a fight scene, false otherwise.
   */
  public boolean isFightScene() {
    return isFightScene;
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
    if (link == null) throw new IllegalArgumentException("Link cannot be null.");
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
    return (
      "Passage{" +
      "title='" +
      title +
      '\'' +
      ", content='" +
      content +
      '\'' +
      ", links=" +
      links +
      '}'
    );
  }

  /**
   * Compares this passage to another object.
   *
   * @param obj The object to compare with this passage.
   * @return True if the object is equal to this passage, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (!(obj instanceof Passage)) return false;
    Passage passage = (Passage) obj;
    return (
      passage.getTitle().equals(this.title) &&
      passage.getContent().equals(this.content) &&
      passage.getLinks().equals(this.links)
    );
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