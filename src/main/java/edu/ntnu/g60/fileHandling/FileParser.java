package edu.ntnu.g60.fileHandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ntnu.g60.models.Link;
import edu.ntnu.g60.models.Passage;
import edu.ntnu.g60.models.Story;

public class FileParser {

    private String storyTitle;
    private String filePath;
    private List<textBlock> textBlocks;

    public FileParser(String filePath) {
        textBlocks = new ArrayList<>();
        this.filePath = filePath;
        readFile(filePath);
    }

    private void readFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Store the first line as storyTitle
            storyTitle = reader.readLine();
            textBlock currentBlock = null;
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("::")) {
                    if (currentBlock != null) {
                        textBlocks.add(currentBlock);
                    }
                    currentBlock = new textBlock();
                }
                if (!line.trim().isEmpty()) {
                    if (currentBlock == null) {
                        currentBlock = new textBlock();
                    }
                    currentBlock.setLine(line);
                }
            }

            // Add the last block if it exists
            if (currentBlock != null) {
                textBlocks.add(currentBlock);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public List<textBlock> getTextBlocks() {
        return textBlocks;
    }

    
    public Story buildStory() {
        FileParser parser = this;
        textBlock openingBlock = parser.getTextBlocks().get(0);
        Passage openingPassage = new Passage(openingBlock.getTitle(), openingBlock.getContent());

        openingBlock.getLinks().forEach((key, value) -> {
            Link link = new Link(key, value);
            //HealthAction action = new HealthAction(10);
            //link.addAction(action);
            openingPassage.addLink(link);
        });

        Story story = new Story(parser.getStoryTitle(), openingPassage);

        parser.getTextBlocks().forEach((block) -> {
            Passage passage = new Passage(block.getTitle(), block.getContent());
            block.getLinks().forEach((key, value) -> {
                passage.addLink(new Link(key, value));
            });
            story.addPassage(passage);
        });
        return story;
    }
    public static void main(String[] args) {

/*
        FileParser story = new FileParser("src/main/resources/textFiles/story.txt");
        Story s = story.buildStory();
        s.getPassages().forEach((passage) -> {
            System.out.println(passage.getTitle());
        });
        s.getOpeningPassage().getLinks().forEach((link) -> {
            System.out.println(link.getText());
        });
        
        System.out.println("Story Title: " + story.getStoryTitle());
        System.out.println("Text Blocks: ");
        for (textBlock block : story.getTextBlocks()) {
            System.out.println("-----------------");
            System.out.println("Title: " + block.getTitle());
            System.out.println("Content: " + block.getContent());
            System.out.println("Links: " + block.getLinks());
        }
 */
    }
}