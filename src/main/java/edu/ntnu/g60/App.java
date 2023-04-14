package edu.ntnu.g60;

import edu.ntnu.g60.fileHandling.FileParser;
import edu.ntnu.g60.models.Passage;
import edu.ntnu.g60.models.Story;

public class App {
    public static void main(String[] args) {
        
        FileParser fileParser = new FileParser("src/main/resources/textFiles/story.txt");
        Story story = fileParser.buildStory();

        System.out.println("Story title: " + story.getTitle());
        
        Passage openingPassage = story.getOpeningPassage();
        System.out.println("Opening passage title: " + openingPassage.getTitle());
        System.out.println("Opening passage content: " + openingPassage.getContent());
        System.out.println("Opening passage links: " + openingPassage.getLinks());
        
        story.getPassages().forEach((passage) -> {
            System.out.println("Passage title: " + passage.getTitle());
            System.out.println("Passage content: " + passage.getContent());
            System.out.println("Passage links: " + passage.getLinks());
        });
        
        
    }
}