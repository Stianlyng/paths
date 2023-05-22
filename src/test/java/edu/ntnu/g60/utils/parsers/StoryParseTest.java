package edu.ntnu.g60.utils.parsers;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.g60.exceptions.BrokenLinkException;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.story.Story;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test wether the story parser correctly parse JSON-formatted stories,
 *
 * @author Stian Lyng
 */
class StoryParseTest {

  private static final String STORY_JSON =
    """
            {
                "title":"Test Story",
                "goals": {
                  "score": 100,
                    "gold": 100,
                    "health": 100,
                    "inventory": [
                    		"sword",
                    		"knife"
                  ]
                },
                "passages":[
                    {
                       "title":"The First Passage",
                       "content":"This is the first passage",
                       "links":[
                          {
                             "text":"Go to the second passage",
                             "reference":"The Second Passage",
                              "actions": [
                                {
                                  "type": "HEALTH",
                                  "value": 10
                                },
                                {
                                  "type": "GOLD",
                                  "value": -10
                                }
                              ]
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

  private Story story;
  private StoryParser storyParser;

  @BeforeEach
  void setUp() {
    InputStream stream = new ByteArrayInputStream(STORY_JSON.getBytes(StandardCharsets.UTF_8));
    storyParser = new StoryParser(stream);
    try {
      story = storyParser.getStory();
    } catch (BrokenLinkException e) {
      e.printStackTrace();
    }
  }

  /**
   * Tests that the story title is correctly parsed from the JSON.
   */
  @Test
  void testStoryTitle() {
    String storyTitle = story.getTitle();
    assertEquals("Test Story", storyTitle);
  }

  /**
   * Tests that the count of passages in the story is correctly parsed from the JSON.
   */
  @Test
  void testPassageCount() {
    int passageCount = story.getPassages().size();
    assertEquals(2, passageCount);
  }

  /**
   * Tests that the title of the opening passage is correctly parsed from the JSON.
   */
  @Test
  void testPassageTitle() {
    String passageTitle = story.getOpeningPassage().getTitle();
    assertEquals("The First Passage", passageTitle);
  }

  /**
   * Tests that the content of the opening passage is correctly parsed from the JSON.
   */
  @Test
  void testPassageContent() {
    String passageContent = story.getOpeningPassage().getContent();
    assertEquals("This is the first passage", passageContent);
  }

  /**
   * Tests that the default images are assigned when no image is specified in the JSON.
   */
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

  /**
   * Tests that a BrokenLinkException is thrown when the JSON contains a broken link.
   */
  @Test
  void testStoryWithBrokenLink() {
    String JsonWithBrokenLink =
      """
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
    InputStream stream = new ByteArrayInputStream(
      JsonWithBrokenLink.getBytes(StandardCharsets.UTF_8)
    );
    StoryParser storyParser = new StoryParser(stream);
    assertThrows(BrokenLinkException.class, storyParser::getStory);
  }

  /**
   * Tests that the actions associated with the links are correctly parsed from the JSON.
   */
  @Test
  void testActions() {
    Passage openingPassage = story.getOpeningPassage();
    assertEquals(2, openingPassage.getLinks().get(0).getActions().size());
  }

  /**
   * Tests that no actions are assigned when none are specified in the JSON.
   */
  @Test
  void testActionsNotInJSON() {
    Link link = story.getOpeningPassage().getLinks().get(0);
    int secondPassage = story.getPassage(link).getLinks().get(0).getActions().size();
    assertEquals(0, secondPassage);
  }

  /**
   * Tests that the goals are correctly parsed from the JSON.
   */
  @Test
  void testWithGoals() {
    List<Goal> goals = storyParser.getGoals();
    assertEquals(4, goals.size());
  }

  /**
   * Tests that default goals are assigned when none are specified in the JSON.
   */
  @Test
  void testWitoutGoals() {
    String JsonWithoutGoals =
      """
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
    InputStream stream = new ByteArrayInputStream(
      JsonWithoutGoals.getBytes(StandardCharsets.UTF_8)
    );
    StoryParser storyParser = new StoryParser(stream);
    List<Goal> goals = storyParser.getGoals();
    assertEquals(4, goals.size());
  }

  /**
   * Tests that a RuntimeException is thrown when trying to parse from a non-existent file.
   */
  @Test
  void testIOException() {
    assertThrows(
      RuntimeException.class,
      () -> {
        StoryParser parser = new StoryParser("NonExistingFile.json");
        parser.getStory();
      }
    );
  }
}
