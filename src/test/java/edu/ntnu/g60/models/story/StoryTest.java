package edu.ntnu.g60.models.story;

import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.passage.PassageBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StoryTest {

    private Story story;
    private Passage openingPassage;
    private Passage additionalPassage;

    @BeforeEach
    void setUp() {

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
    }

    @Test
    void testAddPassage() {
        Passage newPassage = new PassageBuilder()
                .setTitle("New Passage")
                .setContent("This is a new passage")
                .build();

        story.addPassage(newPassage);
        assertEquals(2, story.getPassages().size());
        assertEquals(newPassage, story.getPassage("New Passage"));
    }

    @Test
    void testAddPassageNull() {
        assertThrows(IllegalArgumentException.class, () -> story.addPassage(null));
    }

    @Test
    void testGetTitle() {
        assertEquals("Test Story", story.getTitle());
    }

    @Test
    void testGetOpeningPassage() {
        assertEquals(openingPassage, story.getOpeningPassage());
    }

    @Test
    void testGetPassage() {
        assertEquals(additionalPassage, story.getPassage("Additional Passage"));
    }

    @Test
    void testGetPassages() {
        assertEquals(1, story.getPassages().size());
        assertTrue(story.getPassages().contains(additionalPassage));
    }
}