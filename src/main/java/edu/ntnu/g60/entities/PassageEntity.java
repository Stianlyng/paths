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

    private String title;
    private String content;
    private String background;
    private String player;
    private String enemy;
    private boolean isFight;
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
     * Gets the background of the passage.
     *
     * @return A string representing the background of the passage.
     */
    public String getBackground() {
        return background;
    }

    /**
     * Sets the background of the passage.
     *
     * @param background A string representing the background of the passage.
     */
    public void setBackground(String background) {
        this.background = background;
    }

    /**
     * Gets the player associated with the passage.
     *
     * @return A string representing the player associated with the passage.
     */
    public String getPlayer() {
        return player;
    }

    /**
     * Sets the player associated with the passage.
     *
     * @param player A string representing the player associated with the passage.
     */
    public void setPlayer(String player) {
        this.player = player;
    }

    /**
     * Gets the enemy associated with the passage.
     *
     * @return A string representing the enemy associated with the passage.
     */
    public String getEnemy() {
        return enemy;
    }

    /**
     * Sets the enemy associated with the passage.
     *
     * @param enemy A string representing the enemy associated with the passage.
     */
    public void setEnemy(String enemy) {
        this.enemy = enemy;
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