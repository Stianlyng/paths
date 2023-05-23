package edu.ntnu.g60.utils.fileHandling;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.g60.models.game.Game;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.GoldGoal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.passage.PassageBuilder;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.models.story.StoryBuilder;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

/**
 * Tests the functionality of the saving and filehandling utilities.
 *
 * @author Stian Lyng
 */
class SaveFileHandlerTest {

  private Game game;

  /**
   * The setup includes a Player, Passage, Story and a list of Goals.
   */
  @BeforeEach
  void setUp() {
    Player player = new PlayerBuilder().setName("TestPlayer").build();
    Passage openingPassage = new PassageBuilder()
      .setTitle("Opening Passage")
      .setContent("This is the opening passage")
      .build();
    Story story = new StoryBuilder()
      .setTitle("TestStory")
      .setOpeningPassage(openingPassage)
      .build();
    List<Goal> goals = List.of(new HealthGoal(0), new GoldGoal(0));

    game = new Game(player, story, goals);
  }

  /**
   * Tests whether the game can be saved to a file without throwing an exception.
   * Also tests if saving a null game results in an IllegalStateException.
   */
  @Test
  public void testSaveGameToFile() {
    String saveName = "TestSave";
    String currentPassage = "Intro";
    assertDoesNotThrow(() -> SaveFileHandler.saveGameToFile(game, saveName, currentPassage));
    assertThrows(
      NullPointerException.class,
      () -> SaveFileHandler.saveGameToFile(null, saveName, currentPassage)
    );
  }

  /**
   * Tests if a game can be successfully loaded from a file.
   * Also checks if loading from an invalid file correctly returns an empty Optional.
   */
  @Test
  public void testLoadGameFromFile() {
    String fileName = "TestPlayer_TestStory_TestSave.ser";
    SaveFileHandler.saveGameToFile(game, "TestSave", "Intro");
    Optional<SerializedGameState> loadedGame = SaveFileHandler.loadGameFromFile(fileName);
    assertTrue(loadedGame.isPresent());
    loadedGame = SaveFileHandler.loadGameFromFile("invalid_file.ser");
    assertFalse(loadedGame.isPresent());
  }

  /**
   * Tests whether the correct saves associated with a specific player are retrieved.
   * Also tests if querying saves of a non-existent player returns an empty set.
   */
  @Test
  public void testGetPlayerSaves() throws URISyntaxException {
    String playerIdentifier = "TestPlayer";

    SaveFileHandler.saveGameToFile(game, "TestSave", "Intro");

    Set<String> playerSaves = SaveFileHandler.getPlayerSaves(playerIdentifier);
    assertTrue(playerSaves.contains("TestPlayer_TestStory_TestSave.ser"));

    playerSaves = SaveFileHandler.getPlayerSaves("invalid_player");
    assertTrue(playerSaves.isEmpty());
  }

  /**
   * Tests whether the saved files associated with a specific player can be deleted.
   * Verifies that after deletion, querying the player's saves returns an empty set.
   */
  @Test
  public void testDeletePlayerSaves() throws IOException, URISyntaxException {
    String playerIdentifier = "TestPlayer";
    SaveFileHandler.saveGameToFile(game, "TestSave", "Intro");
    assertDoesNotThrow(() -> SaveFileHandler.deletePlayerSaves(playerIdentifier));
    Set<String> playerSaves = SaveFileHandler.getPlayerSaves(playerIdentifier);
    assertTrue(playerSaves.isEmpty());
  }

  /**
   * Tests if listFilesInFolder method works without throwing an exception.
   */
  @Test
  public void testListFilesInFolder() throws URISyntaxException {
    assertDoesNotThrow(() -> SaveFileHandler.listFilesInFolder());
  }

  /**
   * Tests whether the correct players who have saves are returned.
   * Also checks if a player with a save is correctly included in the returned set.
   */
  @Test
  public void testGetAvailablePlayers() throws URISyntaxException {
    String playerIdentifier = "TestPlayer";
    SaveFileHandler.saveGameToFile(game, "TestSave", "Intro");
    Set<String> availablePlayers = SaveFileHandler.getAvailablePlayers();
    assertTrue(availablePlayers.contains(playerIdentifier));
  }
}
