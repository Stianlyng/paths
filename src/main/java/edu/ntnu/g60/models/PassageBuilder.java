package edu.ntnu.g60.models;

public class PassageBuilder {
    
    /**
     * Default values for the passage.
     */
    private String title = "Untitled Passage";
    private String content = "No content";
    private String player = "beer.png"; //todo; add default img
    private String enemy = "beer.png"; //todo; add default img
    private String background = "background1.png"; //todo; add default img
    private boolean fightScene = false;

    public PassageBuilder() {
    }

    public PassageBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public PassageBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public PassageBuilder withPlayer(String player) {
        if (player != null) {
            this.player = player;
        }
        return this;
    }

    public PassageBuilder withEnemy(String enemy) {
        if (enemy != null) {
            this.enemy = enemy;
        }
        return this;
    }

    public PassageBuilder withBackground(String background) {
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
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title cannot be null or blank.");
        if (content == null || content.isBlank()) throw new IllegalArgumentException("Content cannot be null or blank.");

        return new Passage(title, content, player, enemy, background, fightScene);
    }
}