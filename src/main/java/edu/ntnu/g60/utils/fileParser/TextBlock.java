package edu.ntnu.g60.utils.fileParser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextBlock {
    private String title;
    private String content;
    private String playerImg;
    private String enemyImg;
    private String backgroundImg;
    private boolean isFightScene;

    private Map<String, String> links;

    public TextBlock() {
        this("", "", new HashMap<>());
    }

    public TextBlock(String title, String content, Map<String, String> links) {
        this.title = title;
        this.content = content;
        this.links = links;
    }

    public void setLine(String line) {
        if (line.startsWith("::")) {
            setTitle(line);
        } else if (line.startsWith("[")) {
            addLink(line);
        } else if (line.startsWith("#background:")) {
            setBackgroundImg(line.replace("#background:", ""));
        } else if (line.startsWith("#enemy:")) {
            setEnemyImg(line.replace("#enemy:", ""));
        } else if (line.startsWith("#player:")) {
            setPlayerImg(line.replace("#player:", ""));
        } else if (line.startsWith("#isFight:")) {
            //todo; this needs a better solution
            if (line.endsWith("true")) {
                isFightScene(true);
            } else {
                isFightScene(false);
            }
        } else {
            setContent(line);
        }
    }

    public void addLink(String link) {
        Pattern pattern = Pattern.compile("\\[(.+)]\\((.+)\\)");
        Matcher matcher = pattern.matcher(link);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid link format: " + link);
        }

        String key = matcher.group(1);
        String value = matcher.group(2);
        this.links.put(key, value);
    }

    // Getters and setters for the fields
    public String getTitle() {
        // Remove the leading "::" from the title and trim whitespace
        return title.substring(2).trim();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getPlayerImg(){
        return playerImg;
    }

    public String getEnemyImg(){
        return enemyImg;
    }   

    public String getBackgroundImg() {
        return backgroundImg;
    }
    
    public boolean isFightScene() {
        return isFightScene;
    }

    public void setPlayerImg(String path){
        this.playerImg = path;
    }

    public void setEnemyImg(String path){
        this.enemyImg = path;
    }   

    public void setBackgroundImg(String path) {
        this.backgroundImg = path;
    }
    
    public void isFightScene(boolean fightScene) {
        this.isFightScene = fightScene;
    }

    public void setContent(String content) {
        this.content += content + "\n";
    }

    public Map<String, String> getLinks() {
        return new HashMap<>(links);
    }

    public void setLinks(Map<String, String> links) {
        this.links = new HashMap<>(links);
    }

    public static void main(String[] args) {
        TextBlock block = new TextBlock();
        block.setLine("::My Block");
        block.setLine("This is some content");
        block.setLine("[Google](https://www.google.com)");
        block.setLine("[Wikipedia](https://www.wikipedia.org)");

        System.out.println("Block title: " + block.getTitle());
        System.out.println("Block content: " + block.getContent());
        System.out.println("Block links: " + block.getLinks());
        
        // print each link
        block.getLinks().forEach((key, value) -> {
            System.out.println("Link: " + key + " -> " + value);
        });

    }
}