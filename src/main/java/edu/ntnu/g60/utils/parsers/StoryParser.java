package edu.ntnu.g60.utils.parsers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ntnu.g60.entities.ActionEntity;
import edu.ntnu.g60.entities.LinkEntity;
import edu.ntnu.g60.entities.PassageEntity;
import edu.ntnu.g60.entities.StoryEntity;
import edu.ntnu.g60.models.actions.Action;
import edu.ntnu.g60.models.actions.ActionFactory;
import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.passage.PassageBuilder;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.models.story.StoryBuilder;

/**
 * StoryParser is a class that parses a JSON file into a Story object.
 *
 * @author Stian Lyng
 */
public class StoryParser {

    /**
     * The JSON file to be parsed.
     */
    private final File jsonFile;

    /**
     * The ObjectMapper used to parse the JSON file.
     */
    private final ObjectMapper objectMapper;

    /**
     * Constructs a StoryParser object.
     * 
     * @param jsonFilePath the path to the JSON file to be parsed.
     */
    public StoryParser(String jsonFilePath) {
        this.jsonFile = Paths.get("src/main/resources/stories/" + jsonFilePath + ".json").toFile();
        this.objectMapper = new ObjectMapper();
    }
    
    /**
     * Builds a Passage object from a PassageEntity object.
     *
     * @param passageEntity the PassageEntity object to be parsed.
     * @return a Passage object.
     */
    private Passage buildPassage(PassageEntity passageEntity) {
        Passage passage = new PassageBuilder()
            .setTitle(passageEntity.getTitle())
            .setContent(passageEntity.getContent())
            .setBackground(passageEntity.getBackground())
            .setPlayer(passageEntity.getPlayer())
            .setEnemy(passageEntity.getEnemy())
            .isFightScene(passageEntity.getIsFight())
            .build();
    
        passageEntity.getLinks().forEach(linkEntity -> {
            passage.addLink(buildLink(linkEntity));
        });
    
        return passage;
    }


    /**
     * Builds a Link object from a LinkEntity object.
     *
     * @param linkEntity the LinkEntity object to be parsed.
     * @return a Link object.
     */
    private Link buildLink(LinkEntity linkEntity) {
        Link link = new Link(linkEntity.getText(), linkEntity.getReference());

        linkEntity.getActions().forEach(actionEntity -> {
            Action action = buildAction(actionEntity);
            link.addAction(action);
        });

        return link;
    }

    /**
     * Builds an Action object from an ActionEntity object.
     *
     * @param actionEntity the ActionEntity object to be parsed.
     * @return an Action object.
     */
    private Action buildAction(ActionEntity actionEntity) {
        Action action = ActionFactory.createAction(actionEntity);
        return action;
    }

    /**
     * Builds the Story object from the JSON file.
     *
     * @return a Story object.
     */
    public Story build() {
        try {
            StoryEntity storyMap = objectMapper.readValue(jsonFile, StoryEntity.class);
            StoryBuilder storyBuilder = new StoryBuilder().setTitle(storyMap.getTitle());

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

}