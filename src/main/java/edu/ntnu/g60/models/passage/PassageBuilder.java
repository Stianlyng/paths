package edu.ntnu.g60.models.passage;

import edu.ntnu.g60.utils.DefaultValues;

/**
 * This class is used to build a passage.
 * 
 * @author Stian Lyng
 */
public class PassageBuilder {

    /**
     * Default values for the passage.
     */
    private String title;
    private String content;
    private String player = DefaultValues.PLAYER_IMAGE; 
    private String enemy = DefaultValues.ENEMY_IMAGE; 
    private String background = DefaultValues.BACKGROUND_IMAGE; 
    private boolean fightScene = false;

    public PassageBuilder() {
    }

    public PassageBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public PassageBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public PassageBuilder setPlayer(String player) {
        if (player != null) {
            this.player = player;
        }
        return this;
    }

    public PassageBuilder setEnemy(String enemy) {
        if (enemy != null) {
            this.enemy = enemy;
        }
        return this;
    }

    public PassageBuilder setBackground(String background) {
        if (background != null) {
            this.background = background;
        }
        return this;
    }

    public PassageBuilder isFightScene(boolean fightScene) {
        this.fightScene = fightScene;
        return this;
    }

    public Passage build() {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Title cannot be null or blank.");
        if (content == null || content.isBlank())
            throw new IllegalArgumentException("Content cannot be null or blank.");

        return new Passage(title, content, player, enemy, background, fightScene);
    }
}