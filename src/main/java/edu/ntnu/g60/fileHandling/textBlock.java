package edu.ntnu.g60.fileHandling;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class textBlock {
    private String title;
    private String content;
    private Map<String, String> links;

    public textBlock() {
        this("", "", new HashMap<>());
    }

    public textBlock(String title, String content, Map<String, String> links) {
        this.title = title;
        this.content = content;
        this.links = links;
    }

    public void setLine(String line) {
        if (line.startsWith("::")) {
            setTitle(line);
        } else if (line.startsWith("[")) {
            addLink(line);
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

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getLinks() {
        return new HashMap<>(links);
    }

    public void setLinks(Map<String, String> links) {
        this.links = new HashMap<>(links);
    }

    public static void main(String[] args) {
        textBlock block = new textBlock();
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