package edu.ntnu.g60.models.passage;

import edu.ntnu.g60.models.actions.Action;
import edu.ntnu.g60.models.actions.GoldAction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LinkTest {

    private Link link;

    @BeforeEach
    void setUp() {
        link = new Link("Link 1", "Passage 1");
    }

    @Test
    void testAddAction() {

        Action action = new GoldAction(10); 
        link.addAction(action);
        assertEquals(1, link.getActions().size());
        assertEquals(action, link.getActions().get(0));
    }

    @Test
    void testEquals() {
        Link link2 = new Link("Link 1", "Passage 1");
        assertEquals(link, link2);
    }

    @Test
    void testNotEquals() {
        Link link2 = new Link("Link 2", "Passage 1");
        assertNotEquals(link, link2);
    }

    @Test
    void testHashCode() {
        Link link2 = new Link("Link 1", "Passage 1");
        assertEquals(link.hashCode(), link2.hashCode());
    }
}