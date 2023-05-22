package edu.ntnu.g60.models.passage;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.g60.models.actions.Action;
import edu.ntnu.g60.models.actions.GoldAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Link class.
 *
 * @author Stian Lyng
 */
class LinkTest {

  private Link link;

  @BeforeEach
  void setUp() {
    link = new Link("Link 1", "Passage 1");
  }

  /**
   * Checks if actions can be successfully added to a Link object.
   */
  @Test
  void testAddAction() {
    Action action = new GoldAction(10);
    link.addAction(action);
    assertEquals(1, link.getActions().size());
    assertEquals(action, link.getActions().get(0));
  }

  /**
   * Checks the equals method of the Link class.
   */
  @Test
  void testEquals() {
    Link link2 = new Link("Link 1", "Passage 1");
    assertEquals(link, link2);
  }

  /**
   * Checks that the equals method correctly distinguishes between different Link objects.
   */
  @Test
  void testNotEquals() {
    Link link2 = new Link("Link 2", "Passage 2");
    assertNotEquals(link, link2);
  }

  /**
   * Checks that the hashCode method returns the same value for two equal Link objects.
   */
  @Test
  void testHashCode() {
    Link link2 = new Link("Link 1", "Passage 1");
    assertEquals(link.hashCode(), link2.hashCode());
  }
}
