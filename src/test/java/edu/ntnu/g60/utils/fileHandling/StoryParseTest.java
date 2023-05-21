package edu.ntnu.g60.utils.fileHandling;

import edu.ntnu.g60.exceptions.BrokenLinkException;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.utils.parsers.StoryParser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class StoryParseTest {
    
    private static final String STORY_JSON = """
            {
                "title":"Test Story",
                "passages":[
                    {
                       "title":"The First Passage",
                       "content":"This is the first passage",
                       "links":[
                          {
                             "text":"Go to the second passage",
                             "reference":"The Second Passage"
                          },
                          {
                             "text":"End the game",
                             "reference":"End Game"
                          }
                        ]
                    },
                    {
                       "title":"The Second Passage",
                       "content":"This is the second passage",
                       "background":"Background1.png",
                       "player":"baby.png",
                       "enemy":"gender.png",
                       "isFight":false,
                       "links":[
                          {
                             "text":"Go back to the first passage",
                             "reference":"The First Passage"
                          },

                          {
                             "text":"Finish",
                             "reference":"Game Over"
                          }
                       ]
                    }
                ]
            }
            """;
    
    private Story story; // Declare story as a field in your test class
    private StoryParser storyParser;

    @BeforeEach
    void setUp() {
        InputStream stream = new ByteArrayInputStream(STORY_JSON.getBytes(StandardCharsets.UTF_8));
        storyParser = new StoryParser(stream);
        try {
            story = storyParser.getStory(); // Initialize the story field here
        } catch (BrokenLinkException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void testStoryTitle() {
        String storyTitle = story.getTitle();
        assertEquals("Test Story", storyTitle);
    }

    @Test
    void testPassageCount() {
        int passageCount = story.getPassages().size();
        assertEquals(2, passageCount);
    }
    
    @Test
    void testPassageTitle() {
        String passageTitle = story.getOpeningPassage().getTitle();
        assertEquals("The First Passage", passageTitle);
    }
    
    @Test
    void testPassageContent() {
        String passageContent = story.getOpeningPassage().getContent();
        assertEquals("This is the first passage", passageContent);
    }

    @Test
    void testDefaultImage() {
        Passage openingPassage = story.getOpeningPassage();
        String defaultBackground = openingPassage.getBackgroundImage();
        String defaultPlayer = openingPassage.getPlayerImage();
        String defaultEnemy = openingPassage.getEnemyImage();

        assertEquals("backgroundDefault.jpeg", defaultBackground); 
        assertEquals("playerDefault.png", defaultPlayer);
        assertEquals("enemyDefault.png", defaultEnemy);
    }

    @Test
    void testStoryWithBrokenLink() {
    String JsonWithBrokenLink = """
            {
                "title":"Test Story",
                "passages":[
                    {
                       "title":"The First Passage",
                       "content":"This is the first passage",
                       "links":[
                          {
                             "text":"Go to the second passage",
                             "reference":"The Second Passage"
                          },
                          {
                             "text":"End the game",
                             "reference":"End Game"
                          }
                        ]
                    }                
                ]
            }
            """;
        InputStream stream = new ByteArrayInputStream(JsonWithBrokenLink.getBytes(StandardCharsets.UTF_8)); 
        StoryParser storyParser = new StoryParser(stream);
        assertThrows(BrokenLinkException.class, storyParser::getStory);
    }
}