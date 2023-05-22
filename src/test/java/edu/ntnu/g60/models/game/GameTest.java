package edu.ntnu.g60.models.game;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.g60.exceptions.InvalidLinkException;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.passage.PassageBuilder;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.models.story.StoryBuilder;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Game class.
 *
 * @Author Stian Lyng
 */
class GameTest {

  private Game game;
  private Player player;
  private Story story;
  private List<Goal> goals;
  private Passage openingPassage;
  private Passage additionalPassage;
  private Link link;

  @BeforeEach
  void setUp() {
    player = new PlayerBuilder().setName("Test Player").build();

    openingPassage =
      new PassageBuilder()
        .setTitle("Opening Passage")
        .setContent("This is the opening passage")
        .build();

    additionalPassage =
      new PassageBuilder()
        .setTitle("Additional Passage")
        .setContent("This is an additional passage")
        .build();

    link = new Link("Next Passage", "Additional Passage");

    story =
      new StoryBuilder()
        .setTitle("Test Story")
        .setOpeningPassage(openingPassage)
        .addPassage(additionalPassage)
        .build();

    goals = new ArrayList<>();
    game = new Game(player, story, goals);
  }

  /**
   * checks that the  constructor throws an IllegalArgumentException when a null player, goal or story is passed.
   */
  @Test
  void testConstructorNullPlayer() {
    assertThrows(IllegalArgumentException.class, () -> new Game(null, story, goals));
    assertThrows(IllegalArgumentException.class, () -> new Game(player, null, goals));
    assertThrows(IllegalArgumentException.class, () -> new Game(player, story, null));
  }

  /**
   * verifies that method throws an InvalidLinkException when a non-existent link is passed.
   */
  @Test
  void testGoNonexistentLink() {
    Link nonexistentLink = new Link("Nonexistent Passage", "Nonexistent Passage");
    assertThrows(InvalidLinkException.class, () -> game.go(nonexistentLink));
  }

  /**
   * verifies that the getPlayer method returns the correct player object.
   */
  @Test
  void testGetPlayer() {
    assertEquals(player, game.getPlayer());
  }

  /**
   * verifies thath getStory method returns the correct story object.
   */
  @Test
  void testGetStory() {
    assertEquals(story, game.getStory());
  }

  /**
   * checks wether getGoals method returns the correct goal list.
   */
  @Test
  void testGetGoals() {
    assertEquals(goals, game.getGoals());
  }

  /**
   * checks whether begin method returns the correct opening passage.
   */
  @Test
  void testBegin() {
    assertEquals(openingPassage, game.begin());
  }

  /**
   * Tests that the go method in the game class correctly navigates to the linked passage.
   */
  @Test
  void testGo() throws InvalidLinkException {
    assertEquals(additionalPassage, game.go(link));
  }
}
