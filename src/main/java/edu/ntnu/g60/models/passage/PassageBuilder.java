package edu.ntnu.g60.models.passage;

import java.net.URL;

/**
 * This class is used to build a passage.
 * 
 * @author Stian Lyng
 */
public class PassageBuilder {

    /**
     * Default values for the passage.
     */
    public static final String PLAYER_IMAGE = "playerDefault.png"; 
    public static final String ENEMY_IMAGE = "EnemyDefault.png"; 
    public static final String BACKGROUND_IMAGE = "defaultBackground.jpeg"; 
    
    /**
     * The title of the passage.
     */
    private String title;

    /**
     * The content of the passage.
     */
    private String content;
    
    /**
     * The image of the player.
     */
    private String playerImage;
    
    /**
     * The image of the enemy.
     */
    private String enemyImage;

    /**
     * The background image of the passage.
     */
    private String backgroundImage;
    
    /**
     * Whether or not the passage is a fight scene.
     */
    private boolean isFightScene;

    /**
     * Constructor for the PassageBuilder class.
     * Sets default values for the passage.
     */
    public PassageBuilder() {
        this.playerImage = PLAYER_IMAGE;
        this.enemyImage = ENEMY_IMAGE;
        this.backgroundImage = BACKGROUND_IMAGE;
        this.isFightScene = false;
    
    }

    /**
     * Sets the title of the passage.
     *
     * @param title
     * @return the PassageBuilder object.
     */
    public PassageBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Sets the content of the passage.
     *
     * @param content
     * @return the PassageBuilder object.
     */
    public PassageBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * Sets the image of the player.
     *
     * @param playerImage
     * @return the PassageBuilder object.
     */
    public PassageBuilder setPlayerImage(String filename) {
        this.playerImage = setImage(filename, PLAYER_IMAGE, "/images/characters/");
        return this;
    }
    
    /**
     * Sets the image of the enemy.
     *
     * @param enemyImage
     * @return the PassageBuilder object.
     */
    public PassageBuilder setEnemyImage(String filename) {
        this.enemyImage = setImage(filename, ENEMY_IMAGE, "/images/characters/");
        return this;
    }
    
    /**
     * Sets the background image of the passage.
     *
     * @param backgroundImage
     * @return the PassageBuilder object.
     */
    public PassageBuilder setBackgroundImage(String filename) {
        this.backgroundImage = setImage(filename, BACKGROUND_IMAGE, "/images/backgrounds/");
        return this;
    }

    /**
     * Sets the image of the player.
     *
     * @param filename
     * @param defaultImage
     * @param imagePath
     * @return the filename if it exists, otherwise the defaultImage.
     */
    private String setImage(String filename, String defaultImage, String imagePath) {
        URL imageExists = PassageBuilder.class.getResource(imagePath + filename);
        if (filename != null && imageExists != null) {
            return filename;
        }
        return defaultImage;
    }

    /**
     * Sets whether or not the passage is a fight scene.
     *
     * @param fightScene
     * @return the PassageBuilder object.
     */
    public PassageBuilder isFightScene(boolean fightScene) {
        this.isFightScene = fightScene;
        return this;
    }

    /**
     * Builds a Passage object from the values in the PassageBuilder object.
     *
     * @return a Passage object.
     */
    public Passage build() {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Title cannot be null or blank.");
        if (content == null || content.isBlank())
            throw new IllegalArgumentException("Content cannot be null or blank.");

        return new Passage(title, content, playerImage, enemyImage, backgroundImage, isFightScene);
    }
}