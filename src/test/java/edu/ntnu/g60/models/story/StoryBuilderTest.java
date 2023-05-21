package edu.ntnu.g60.models.story;

import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.passage.PassageBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoryBuilderTest {

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
    }

    @Test
    void testSetTitle() {
        StoryBuilder builder = new StoryBuilder();
        builder.setTitle("Test Story");
        Story story = builder.setOpeningPassage(openingPassage).build();
        assertEquals("Test Story", story.getTitle());
    }

    @Test
    void testSetTitleNull() {
        StoryBuilder builder = new StoryBuilder();
        assertThrows(IllegalArgumentException.class, () -> builder.setTitle(null).setOpeningPassage(openingPassage).build());
    }

    @Test
    void testSetTitleBlank() {
        StoryBuilder builder = new StoryBuilder();
        assertThrows(IllegalArgumentException.class, () -> builder.setTitle("").setOpeningPassage(openingPassage).build());
    }

    @Test
    void testSetOpeningPassage() {
        StoryBuilder builder = new StoryBuilder();
        builder.setOpeningPassage(openingPassage);
        Story story = builder.setTitle("Test Story").build();
        assertEquals(openingPassage, story.getOpeningPassage());
    }

    @Test
    void testSetOpeningPassageNull() {
        StoryBuilder builder = new StoryBuilder();
        assertThrows(IllegalArgumentException.class, () -> builder.setOpeningPassage(null).setTitle("Test Story").build());
    }

    @Test
    void testAddPassage() {
        StoryBuilder builder = new StoryBuilder();
        builder.addPassage(additionalPassage);
        Story story = builder.setTitle("Test Story").setOpeningPassage(openingPassage).build();
        assertTrue(story.getPassages().contains(additionalPassage));
    }

    @Test
    void testBuild() {
        StoryBuilder builder = new StoryBuilder();
        builder.setTitle("Test Story");
        builder.setOpeningPassage(openingPassage);
        builder.addPassage(additionalPassage);
        Story story = builder.build();

        assertEquals("Test Story", story.getTitle());
        assertEquals(openingPassage, story.getOpeningPassage());
        assertTrue(story.getPassages().contains(additionalPassage));
    }
}