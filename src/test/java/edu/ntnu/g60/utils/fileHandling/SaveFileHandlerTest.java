package edu.ntnu.g60.utils.fileHandling;

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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SaveFileHandlerTest {
   
    private Game game;

    @BeforeEach
    void setUp() {

        Player player = new PlayerBuilder()
                .setName("TestPlayer")
                .build();
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
    
    @Test
    public void testSaveGameToFile() {
        String saveName = "TestSave";
        String currentPassage = "Intro";

        assertDoesNotThrow(() -> SaveFileHandler.saveGameToFile(game, saveName, currentPassage));
        assertThrows(IllegalStateException.class, () -> SaveFileHandler.saveGameToFile(null, saveName, currentPassage));
    }

    @Test
    public void testLoadGameFromFile() {
        String fileName = "TestPlayer_TestStory_TestSave.ser";

        SaveFileHandler.saveGameToFile(game, "TestSave", "Intro");

        Optional<SerializedGameState> loadedGame = SaveFileHandler.loadGameFromFile(fileName);
        assertTrue(loadedGame.isPresent());
        loadedGame = SaveFileHandler.loadGameFromFile("invalid_file.ser");
        assertFalse(loadedGame.isPresent());
    }

    @Test
    public void testGetPlayerSaves() throws URISyntaxException {
        String playerIdentifier = "TestPlayer";
        
        SaveFileHandler.saveGameToFile(game, "TestSave", "Intro");

        Set<String> playerSaves = SaveFileHandler.getPlayerSaves(playerIdentifier);
        assertTrue(playerSaves.contains("TestPlayer_TestStory_TestSave.ser"));

        playerSaves = SaveFileHandler.getPlayerSaves("invalid_player");
        assertTrue(playerSaves.isEmpty());
    }

    @Test
    public void testDeletePlayerSaves() throws IOException, URISyntaxException {
        String playerIdentifier = "TestPlayer";
        SaveFileHandler.saveGameToFile(game, "TestSave", "Intro");
        assertDoesNotThrow(() -> SaveFileHandler.deletePlayerSaves(playerIdentifier));
        Set<String> playerSaves = SaveFileHandler.getPlayerSaves(playerIdentifier);
        assertTrue(playerSaves.isEmpty());
    }

    @Test
    public void testListFilesInFolder() throws URISyntaxException {
        assertDoesNotThrow(() -> SaveFileHandler.listFilesInFolder());
    }

    @Test
    public void testGetAvailablePlayers() throws URISyntaxException {
        String playerIdentifier = "TestPlayer";
        SaveFileHandler.saveGameToFile(game, "TestSave", "Intro");
        Set<String> availablePlayers = SaveFileHandler.getAvailablePlayers();
        assertTrue(availablePlayers.contains(playerIdentifier));
    }
}