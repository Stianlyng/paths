package edu.ntnu.g60.fileHandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import edu.ntnu.g60.models.Link;
import edu.ntnu.g60.models.Passage;
import edu.ntnu.g60.models.PassageBuilder;
import edu.ntnu.g60.models.Story;
import edu.ntnu.g60.models.StoryBuilder;

public class FileParser {

    private String storyTitle;
    private Path filePath;
    private List<TextBlock> textBlocks;

    public FileParser(String filePath) {
        textBlocks = new ArrayList<>();
        this.filePath = Paths.get(filePath);
        readFile();
    }

    private void readFile() {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            // Store the first line as storyTitle
            storyTitle = reader.readLine();
            TextBlock currentBlock = null;
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("::")) {
                    if (currentBlock != null) {
                        textBlocks.add(currentBlock);
                    }
                    currentBlock = new TextBlock();
                }
                if (!line.trim().isEmpty()) {
                    if (currentBlock == null) {
                        currentBlock = new TextBlock();
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

    public List<TextBlock> getTextBlocks() {
        return textBlocks;
    }

    public Story buildStory() {
        FileParser parser = this;
        TextBlock openingBlock = parser.getTextBlocks().get(0);
        Passage openingPassage = new PassageBuilder()
                .withTitle(openingBlock.getTitle())
                .withContent(openingBlock.getContent())
                .withPlayer(openingBlock.getPlayerImg())
                .withEnemy(openingBlock.getEnemyImg())
                .withBackground(openingBlock.getBackgroundImg())
                .isFightScene(openingBlock.isFightScene())
                .build();
        openingBlock.getLinks().forEach((key, value) -> {
            Link link = new Link(key, value);
            openingPassage.addLink(link);
        });

        Story story = new StoryBuilder()
                        .setTitle(parser.getStoryTitle())
                        .setOpeningPassage(openingPassage)
                        .build();

        parser.getTextBlocks().forEach((block) -> {
            Passage passage = new PassageBuilder()
                    .withTitle(block.getTitle())
                    .withContent(block.getContent())
                    .withPlayer(block.getPlayerImg())
                    .withEnemy(block.getEnemyImg())
                    .withBackground(block.getBackgroundImg())
                    .isFightScene(block.isFightScene())
                    .build();

            block.getLinks().forEach((key, value) -> {
                passage.addLink(new Link(key, value));
            });
            story.addPassage(passage);
        });
        return story;
    }

    public static void main(String[] args) {

        FileParser story = new FileParser("src/main/resources/textFiles/haunted_house.txt");
        Story s = story.buildStory();
        s.getPassages().forEach((passage) -> {
            System.out.println(passage.getTitle());
        });
        s.getOpeningPassage().getLinks().forEach((link) -> {
            System.out.println(link.getText());
        });

        System.out.println("Story Title: " + story.getStoryTitle());
        System.out.println("Text Blocks: ");
        for (TextBlock block : story.getTextBlocks()) {
            System.out.println("-----------------");
            System.out.println("Title: " + block.getTitle());
            System.out.println("Content: " + block.getContent());
            System.out.println("Links: " + block.getLinks());
        }
    }
}