package edu.ntnu.g60.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.ntnu.g60.models.Game;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.passage.PassageBuilder;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.models.story.StoryBuilder;
import edu.ntnu.g60.utils.fileHandling.StoryParser;

public class GameControllerTest {
    private Game game;
    private Player player;

    private Story story;
    private Passage openingPassage;
    private Passage additionalPassage;

    @BeforeEach
    public void setup() {
        StoryParser parser = new StoryParser("life_story.json");
        openingPassage = new PassageBuilder()
            .setTitle("Opening Passage")
            .setContent("This is the opening passage")
            .build();

        additionalPassage = new PassageBuilder()
            .setTitle("Additional Passage")
            .setContent("This is an additional passage")
            .build();

        story = new StoryBuilder()
            .setTitle("Test Story")
            .setOpeningPassage(openingPassage)
            .addPassage(additionalPassage)
            .build();
        player = new PlayerBuilder().setName("John").build();
        List<Goal> goals = new ArrayList<Goal>();
        goals.add(new HealthGoal(4));

        game = new Game(player, story, goals);
    }

    @Test
    public void testSetAndGetGame() {
        GameController.setCurrentGame(game);
        assertEquals(game, GameController.getCurrentGame());
    }


    @Test
    public void testDelay() {
        long start = System.currentTimeMillis();
        Runnable continuation = () -> {
            long end = System.currentTimeMillis();
            assertTrue(end - start >= 500);
        };
        GameController.delay(500, continuation);
    }
}
