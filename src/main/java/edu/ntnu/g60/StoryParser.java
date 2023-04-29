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

/**
 * StoryParser is a class that parses a JSON file into a Story object.
 *
 * @version 1.0
 * @author Stian Lyng
 */
public class StoryParser {

    private String jsonFilePath;

    private ObjectMapper objectMapper = new ObjectMapper();

    public StoryParser(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    public Story build() {
        try {
            StoryEntity storyMap = objectMapper.readValue(new File(jsonFilePath), StoryEntity.class);

            StoryBuilder storyBuilder = new StoryBuilder()
                .setTitle(storyMap.getTitle());

            storyMap.getPassages().forEach(passageEntity -> {
                Passage passage = buildPassage(passageEntity);
                storyBuilder.addPassage(passage);
                if (storyMap.getPassages().indexOf(passageEntity) == 0) {
                    storyBuilder.setOpeningPassage(passage);
                }
            });

            return storyBuilder.build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Passage buildPassage(PassageEntity passageEntity) {
        Passage passage = new PassageBuilder()
            .setTitle(passageEntity.getTitle())
            .setContent(passageEntity.getContent())
            .build();

        passageEntity.getLinks().forEach(link -> {
            passage.addLink(new Link(link.getText(), link.getReference()));
        });

        return passage;
    }
}