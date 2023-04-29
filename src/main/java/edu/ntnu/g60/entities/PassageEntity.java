package edu.ntnu.g60.entities;

import java.util.List;


public class PassageEntity {

    private String title;
    private String content;
    private String background;
    private String player;
    private String enemy;
    private boolean isFight;
    private List<LinkEntity> links;

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<LinkEntity> getLinks() {
        return links;
    }

    public void setLinks(List<LinkEntity> links) {
        this.links = links;
    }
    
    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }


    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy;
    }

    public Boolean getIsFight() {
        return isFight;
    }

    public void setIsFight(Boolean isFight) {
        this.isFight = isFight;
    }

}