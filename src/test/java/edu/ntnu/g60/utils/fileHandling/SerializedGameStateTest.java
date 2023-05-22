package edu.ntnu.g60.utils.fileHandling;

import edu.ntnu.g60.models.game.Game;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.GoldGoal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.passage.PassageBuilder;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.models.story.StoryBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SerializedGameStateTest {
    private Game game;
    private Link link;
    private SerializedGameState serializedGameState;
    
    @BeforeEach
    public void setUp() {

        Player player = new PlayerBuilder()
                .setName("TestPlayer")
                .build();
        Passage openingPassage = new PassageBuilder()
                .setTitle("Opening Passage")
                .setContent("This is the opening passage")
                .build();

        link = new Link("go to second passage", "Second Passage");
        openingPassage.addLink(link);

        Passage secondPassage = new PassageBuilder()
                .setTitle("Second Passage")
                .setContent("This is the second passage")
                .build();
        Story story = new StoryBuilder()
                .setTitle("TestStory")
                .setOpeningPassage(openingPassage)
                .build();
        
        story.addPassage(secondPassage);

        List<Goal> goals = List.of(new HealthGoal(0), new GoldGoal(0));

        game = new Game(player, story, goals);
        serializedGameState = new SerializedGameState(game, link);
    }
    
    @Test
    public void testConstructor() {
        assertNotNull(serializedGameState, "Should be instantiated.");
        assertNotNull(serializedGameState.getGame(), "SerializedGameState should contain a game object.");
        assertEquals(game.getPlayer().getName(), serializedGameState.getGame().getPlayer().getName(), "Should contain the correct Player name.");
        assertNotNull(serializedGameState.getCurrentLink(), "Should contain a Link object.");
    }

    @Test
    public void testCurrentLink() {
        Link currentLink = serializedGameState.getCurrentLink();
        assertEquals(link, currentLink, "getCurrentLink should return the correct Link target.");
    }

    @Test
    public void testGetGame() {
        Game gameFromSerialized = serializedGameState.getGame();
        assertEquals(game.getPlayer().getName(), gameFromSerialized.getPlayer().getName(), "getGame should return the correct Player name.");
    }
}