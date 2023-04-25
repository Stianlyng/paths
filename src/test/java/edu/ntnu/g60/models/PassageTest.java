package edu.ntnu.g60.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PassageTest {

    @Test
    void testPassageBuilder() {
        Passage passage = new PassageBuilder()
                .withTitle("Opening Passage")
                .withContent("This is the opening passage")
                .withPlayer("player1.png")
                .withEnemy("enemy1.png")
                .withBackground("background2.png")
                .isFightScene(true)
                .build();

        assertNotNull(passage);
        assertEquals("Opening Passage", passage.getTitle());
        assertEquals("This is the opening passage", passage.getContent());
        assertEquals("player1.png", passage.getPlayer());
        assertEquals("enemy1.png", passage.getEnemy());
        assertEquals("background2.png", passage.getBackground());
        assertTrue(passage.hasFightScene());
        assertEquals(new ArrayList<>(), passage.getLinks());
    }

    @Test
    void testPassageBuilderWithInvalidArguments() {
        assertThrows(IllegalArgumentException.class, () -> new PassageBuilder().withTitle("").build());
        assertThrows(IllegalArgumentException.class, () -> new PassageBuilder().withContent("").build());
        assertThrows(IllegalArgumentException.class, () -> new PassageBuilder().withTitle(null).build());
        assertThrows(IllegalArgumentException.class, () -> new PassageBuilder().withContent(null).build());
    }

    @Test
    void testAddLink() {
        Passage passage = new PassageBuilder()
                .withTitle("Opening Passage")
                .withContent("This is the opening passage")
                .build();

        Link link = new Link("Next Passage", "next");
        assertTrue(passage.addLink(link));
        assertEquals(1, passage.getLinks().size());
        assertEquals(link, passage.getLinks().get(0));
    }

    @Test
    void testAddLinkWithInvalidArguments() {
        Passage passage = new PassageBuilder()
                .withTitle("Opening Passage")
                .withContent("This is the opening passage")
                .build();

        assertThrows(IllegalArgumentException.class, () -> passage.addLink(null));
    }
}