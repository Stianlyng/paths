package edu.ntnu.g60.entities;

import java.util.List;

/**
 * This class represents the structure of a passage in the JSON file.
 * It is used by the StoryParser to parse the JSON file.
 *
 * @see StoryParser
 * @author Stian Lyng
 */
public class PassageEntity {

  /**
   * This holds the title of the passage
   */
  private String title;

  /**
   * This holds the content of the passage
   */
  private String content;

  /**
   * This holds the name of the background image
   */
  private String backgroundImage;

  /**
   * This holds the name of the player image
   */
  private String playerImage;

  /**
   * This holds the name of the enemy image
   */
  private String enemyImage;

  /**
   * This holds the fight status of the passage
   */
  private boolean isFight;

  /**
   * This holds the list of links associated with the passage
   */
  private List<LinkEntity> links;

  /**
   * Gets the title of the passage.
   *
   * @return A string representing the title of the passage.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the title of the passage.
   *
   * @param title A string representing the title of the passage.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Gets the content of the passage.
   *
   * @return A string representing the content of the passage.
   */
  public String getContent() {
    return content;
  }

  /**
   * Sets the content of the passage.
   *
   * @param content A string representing the content of the passage.
   */
  public void setContent(String content) {
    this.content = content;
  }

  /**
   * Gets the list of links associated with the passage.
   *
   * @return A list of LinkEntity objects representing the links associated with the passage.
   */
  public List<LinkEntity> getLinks() {
    return links;
  }

  /**
   * Sets the list of links associated with the passage.
   *
   * @param links A list of LinkEntity objects representing the links associated with the passage.
   */
  public void setLinks(List<LinkEntity> links) {
    this.links = links;
  }

  /**
   * Gets the backgroundImage of the passage.
   *
   * @return A string representing the backgroundImage of the passage.
   */
  public String getBackground() {
    return backgroundImage;
  }

  /**
   * Sets the backgroundImage of the passage.
   *
   * @param backgroundImage A string representing the backgroundImage of the passage.
   */
  public void setBackground(String backgroundImage) {
    this.backgroundImage = backgroundImage;
  }

  /**
   * Gets the playerImage associated with the passage.
   *
   * @return A string representing the playerImage associated with the passage.
   */
  public String getPlayer() {
    return playerImage;
  }

  /**
   * Sets the playerImage associated with the passage.
   *
   * @param playerImage A string representing the playerImage associated with the passage.
   */
  public void setPlayer(String playerImage) {
    this.playerImage = playerImage;
  }

  /**
   * Gets the enemyImage associated with the passage.
   *
   * @return A string representing the enemyImage associated with the passage.
   */
  public String getEnemy() {
    return enemyImage;
  }

  /**
   * Sets the enemyImage associated with the passage.
   *
   * @param enemyImage A string representing the enemyImage associated with the passage.
   */
  public void setEnemy(String enemyImage) {
    this.enemyImage = enemyImage;
  }

  /**
   * Gets the fight status of the passage.
   *
   * @return A Boolean value representing whether the passage has a fight or not.
   */
  public Boolean getIsFight() {
    return isFight;
  }

  /**
   * Sets the fight status of the passage.
   *
   * @param isFight A Boolean value representing whether the passage has a fight or not.
   */
  public void setIsFight(Boolean isFight) {
    this.isFight = isFight;
  }
}
