package edu.ntnu.g60.models.passage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Passage class.
 *
 * @author Stian Lyng
 */
class PassageTest {

  private Passage passage;
  private Link link;

  @BeforeEach
  void setUp() {
    passage =
      new PassageBuilder()
        .setTitle("Opening Passage")
        .setContent("This is the opening passage")
        .setPlayerImage("player1.png")
        .setEnemyImage("enemy1.png")
        .setBackgroundImage("background2.png")
        .isFightScene(true)
        .build();

    link = new Link("Link 1", "Passage 1");
  }

  /**
   * Checks that passage object has the correct attributes.
   */
  @Test
  void testPassageBuilder() {
    assertNotNull(passage);
    assertEquals("Opening Passage", passage.getTitle());
    assertEquals("This is the opening passage", passage.getContent());
    assertEquals("playerDefault.png", passage.getPlayerImage());
    assertEquals("enemyDefault.png", passage.getEnemyImage());
    assertEquals("backgroundDefault.jpeg", passage.getBackgroundImage());
    assertTrue(passage.isFightScene());
    assertEquals(new ArrayList<>(), passage.getLinks());
  }

  /**
   * Tests the PassageBuilder with invalid arguments.
   */
  @Test
  void testPassageBuilderWithInvalidArguments() {
    assertThrows(IllegalArgumentException.class, () -> new PassageBuilder().setTitle("").build());
    assertThrows(IllegalArgumentException.class, () -> new PassageBuilder().setContent("").build());
    assertThrows(IllegalArgumentException.class, () -> new PassageBuilder().setTitle(null).build());
    assertThrows(
      IllegalArgumentException.class,
      () -> new PassageBuilder().setContent(null).build()
    );
  }

  /**
   * Tests the method to add a Link to a Passage.
   */
  @Test
  void testAddLink() {
    assertTrue(passage.addLink(link));
    assertEquals(1, passage.getLinks().size());
    assertEquals(link, passage.getLinks().get(0));
  }

  /**
   * checks that the addLink method with invalid arguments throws an IllegalArgumentException.
   */
  @Test
  void testAddLinkWithInvalidArguments() {
    assertThrows(IllegalArgumentException.class, () -> passage.addLink(null));
  }

  /**
   * Tests if the Passage has links method works as intended.
   */
  @Test
  void testHasLinks() {
    assertFalse(passage.hasLinks());
    passage.addLink(link);
    assertTrue(passage.hasLinks());
  }

  /**
   * Tests the equals method of a Passage.
   */
  @Test
  void testEquals() {
    Passage theOtherPassage = new PassageBuilder()
      .setTitle("Opening Passage")
      .setContent("This is the opening passage")
      .setPlayerImage("player1.png")
      .setEnemyImage("enemy1.png")
      .setBackgroundImage("background2.png")
      .isFightScene(true)
      .build();

    assertEquals(passage, theOtherPassage);
  }

  /**
   * Tests the equals method of a Passage with a different Passage. should return false.
   */
  @Test
  void testNotEquals() {
    Passage passage2 = new PassageBuilder()
      .setTitle("Different Title")
      .setContent("This is the opening passage")
      .build();
    assertNotEquals(passage, passage2);
  }

  /**
   * Tests the hashCode method of a Passage.
   */
  @Test
  void testHashCode() {
    Passage theOtherPassage = new PassageBuilder()
      .setTitle("Opening Passage")
      .setContent("This is the opening passage")
      .setPlayerImage("player1.png")
      .setEnemyImage("enemy1.png")
      .setBackgroundImage("background2.png")
      .isFightScene(true)
      .build();

    assertEquals(passage.hashCode(), theOtherPassage.hashCode());
  }
}
