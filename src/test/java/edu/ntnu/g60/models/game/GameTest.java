package edu.ntnu.g60.models.game;

import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.passage.PassageBuilder;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.models.story.StoryBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        player = new PlayerBuilder()
                .setName("Test Player")
                .build();

        openingPassage = new PassageBuilder()
                .setTitle("Opening Passage")
                .setContent("This is the opening passage")
                .build();

        additionalPassage = new PassageBuilder()
                .setTitle("Additional Passage")
                .setContent("This is an additional passage")
                .build();


        link = new Link("Next Passage", "Additional Passage");

        story = new StoryBuilder()
                .setTitle("Test Story")
                .setOpeningPassage(openingPassage)
                .addPassage(additionalPassage)
                .build();

        goals = new ArrayList<>(); 
        game = new Game(player, story, goals, "TestGame");
    }

    @Test
    void testGetPlayer() {
        assertEquals(player, game.getPlayer());
    }

    @Test
    void testGetStory() {
        assertEquals(story, game.getStory());
    }

    @Test
    void testGetGoals() {
        assertEquals(goals, game.getGoals());
    }

    @Test
    void testBegin() {
        assertEquals(openingPassage, game.begin());
    }

    @Test
    void testGo() {
        assertEquals(additionalPassage, game.go(link));
    }
}