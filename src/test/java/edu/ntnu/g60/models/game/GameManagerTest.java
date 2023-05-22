package edu.ntnu.g60.models.game;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.GoldGoal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.passage.PassageBuilder;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.models.story.StoryBuilder;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the GameManager class.
 *
 * @author Stian Lyng
 */
public class GameManagerTest {

  private GameManager gameManager;
  private Player player;
  private Story story;
  private Passage openingPassage;
  private List<Goal> goals;

  /**
   * Sets up the test environment before each test method.
   */
  @BeforeEach
  public void setUp() {
    gameManager = GameManager.getInstance();
    player = new PlayerBuilder().setName("Test Player").build();
    openingPassage =
      new PassageBuilder()
        .setTitle("Opening Passage")
        .setContent("This is the opening passage")
        .build();
    story = new StoryBuilder().setTitle("Test Story").setOpeningPassage(openingPassage).build();
    goals = List.of(new HealthGoal(0), new GoldGoal(0));
  }

  @AfterEach
  public void tearDown() {
    gameManager.endGame();
  }

  /**
   * Tests that the GameManager class is a singleton.
   */
  @Test
  public void testSingleton() {
    GameManager gameManager2 = GameManager.getInstance();
    assertSame(gameManager, gameManager2, "GameManager instances should be the same");
  }

  /**
   * Tests that a Game can be created and retrieved correctly.
   */
  @Test
  public void testCreateAndGetGame() {
    gameManager.setPlayer(player);
    gameManager.setStory(story);
    gameManager.setGoals(goals);
    gameManager.createGame();
    Game game = gameManager.getGame();
    assertNotNull(game, "Game should not be null");
    assertSame(player, game.getPlayer(), "Player should be the same");
    assertSame(story, game.getStory(), "Story should be the same");
    assertSame(goals, game.getGoals(), "Goals should be the same");
  }

  @Test
  public void testCreateGameInProgress() {
    gameManager.setPlayer(player);
    gameManager.setStory(story);
    gameManager.setGoals(goals);
    gameManager.createGame();
    assertThrows(
      IllegalStateException.class,
      () -> gameManager.createGame(),
      "Should throw exception when a game is already in progress"
    );
  }

  @Test
  public void testCreateGameNotSet() {
    gameManager.setPlayer(null);
    gameManager.setStory(null);
    gameManager.setGoals(null);
    assertThrows(
      IllegalStateException.class,
      () -> gameManager.createGame(),
      "Should throw exception when Player, Story, and Goals have not been set"
    );
  }

  @Test
  public void testGetGameNotCreated() {
    assertThrows(
      IllegalStateException.class,
      () -> gameManager.getGame(),
      "Should throw exception when no game has been created"
    );
  }

  /**
   * Tests that a Game can be ended correctly.
   */
  @Test
  public void testEndGame() {
    gameManager.setPlayer(player);
    gameManager.setStory(story);
    gameManager.setGoals(goals);
    gameManager.createGame();
    gameManager.endGame();
    assertThrows(
      IllegalStateException.class,
      () -> gameManager.getGame(),
      "Should throw exception when no game has been created"
    );
  }
}
