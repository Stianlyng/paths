package edu.ntnu.g60;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ntnu.g60.entities.PassageEntity;
import edu.ntnu.g60.entities.StoryEntity;
import edu.ntnu.g60.models.Link;
import edu.ntnu.g60.models.Passage;
import edu.ntnu.g60.models.Story;
import edu.ntnu.g60.models.StoryBuilder;

import edu.ntnu.g60.models.PassageBuilder;

public class StoryParser {
    
    private String jsonFilePath;
    private ObjectMapper objectMapper = new ObjectMapper();
    
    public StoryParser(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }
    
    public Story build() {
        try {

            StoryEntity storyMap = objectMapper.readValue(new File(jsonFilePath), StoryEntity.class);

            PassageEntity openingPassageMap = storyMap.getPassages().get(0);

            Passage openingPassage = new PassageBuilder()
                .setTitle(openingPassageMap.getTitle())
                .setContent(openingPassageMap.getContent())
                .build();
            
            openingPassageMap.getLinks().forEach(link -> {
                openingPassage.addLink(new Link(link.getText(), link.getReference()));
            });

            Story story = new StoryBuilder()
                .setTitle(storyMap.getTitle())
                .setOpeningPassage(openingPassage)
                .build();



            storyMap.getPassages().forEach(passageMap -> {

                Passage passage = new PassageBuilder()
                    .setTitle(passageMap.getTitle())
                    .setContent(passageMap.getContent())
                    .build();
                
                passageMap.getLinks().forEach(link -> {
                    passage.addLink(new Link(link.getText(), link.getReference()));
                });

                story.addPassage(passage);
            });

            return story;
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }
}
