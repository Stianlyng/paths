package edu.ntnu.g60.models.story;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.passage.PassageBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Story class.
 *
 * @author Stian Lyng
 */
class StoryTest {

  private Story story;
  private Passage openingPassage;
  private Passage additionalPassage;
  private Link link;
  private Link additionaLink;

  @BeforeEach
  void setUp() {
    openingPassage =
      new PassageBuilder()
        .setTitle("Opening Passage")
        .setContent("This is the opening passage")
        .build();

    additionalPassage =
      new PassageBuilder()
        .setTitle("Additional Passage")
        .setContent("This is an additional passage")
        .build();

    story =
      new StoryBuilder()
        .setTitle("Test Story")
        .setOpeningPassage(openingPassage)
        .addPassage(additionalPassage)
        .build();

    link = new Link("Go to Additional Passage", "Additional Passage");
    additionaLink = new Link("Go to Opening Passage", "Opening Passage");
  }

  /**
   * Tests adding a new passage to the story.
   */
  @Test
  void testAddPassage() {
    Passage newPassage = new PassageBuilder()
      .setTitle("New Passage")
      .setContent("This is a new passage")
      .build();

    story.addPassage(newPassage);
    assertEquals(2, story.getPassages().size());
    assertEquals(newPassage, story.getPassage(new Link("go to", "New Passage")));
  }

  /**
   * Tests the addPassage method with null input.
   */
  @Test
  void testAddPassageNull() {
    assertThrows(IllegalArgumentException.class, () -> story.addPassage(null));
  }

  /**
   * Tests the getTitle method.
   */
  @Test
  void testGetTitle() {
    assertEquals("Test Story", story.getTitle());
  }

  /**
   * Tests the getOpeningPassage method.
   */
  @Test
  void testGetOpeningPassage() {
    assertEquals(openingPassage, story.getOpeningPassage());
  }

  /**
   * Tests the getPassage method with valid Link.
   */
  @Test
  void testGetPassage() {
    assertEquals(additionalPassage, story.getPassage(link));
  }

  /**
   * Tests the getPassages method.
   */
  @Test
  void testGetPassages() {
    assertEquals(1, story.getPassages().size());
    assertTrue(story.getPassages().contains(additionalPassage));
  }

  /**
   * Tests the deletePassage method.
   */
  @Test
  void testDeletePassage() {
    story.deletePassage(link);
    assertNull(story.getPassage(link));
  }

  /**
   * Tests the getBrokenLinks method with valid inpur.
   */
  @Test
  void testGetBrokenLinks() {
    List<Link> brokenLinks = story.getBrokenLinks();
    assertTrue(brokenLinks.isEmpty());
  }

  /**
   * Tests the constructor of the Story class with various invalid inputs.
   */
  @Test
  void testStoryConstructor() {
    assertThrows(IllegalArgumentException.class, () -> new Story(null, openingPassage));
    assertThrows(IllegalArgumentException.class, () -> new Story("", openingPassage));
    assertThrows(IllegalArgumentException.class, () -> new Story("Test Story", null));
  }

  /**
   * Tests the copy constructor.
   */
  @Test
  void testStoryCopyConstructor() {
    Story copyStory = new Story(story);
    assertEquals(story.getTitle(), copyStory.getTitle());
    assertEquals(story.getOpeningPassage(), copyStory.getOpeningPassage());
    assertEquals(story.getPassages(), copyStory.getPassages());
  }

  /**
   * Tests the addAllPassages method with a collection of passages.
   */
  @Test
  void testAddAllPassages() {
    Map<Link, Passage> passages = new HashMap<>();
    Passage anotherPassage = new PassageBuilder()
      .setTitle("Another Passage")
      .setContent("This is another passage")
      .build();
    Link anotherLink = new Link("Go to Another Passage", "Another Passage");
    passages.put(anotherLink, anotherPassage);

    story.addAllPassages(passages);
    assertEquals(2, story.getPassages().size());
    assertTrue(story.getPassages().contains(anotherPassage));
  }

  /**
   * Tests the deletePassage method with an invalid link.
   */
  @Test
  void testDeletePassageWithLink() {
    Passage linkedPassage = new PassageBuilder()
      .setTitle("Linked Passage")
      .setContent("This is a linked passage")
      .build();

    linkedPassage.addLink(link);
    story.addPassage(linkedPassage);

    assertThrows(IllegalArgumentException.class, () -> story.deletePassage(link));
  }

  /**
   * Tests the getBrokenLinks method under special cases, such as game end scenarios.
   */
  @Test
  void testGetBrokenLinksSpecialCases() {
    Passage endGamePassage = new PassageBuilder()
      .setTitle("End Game Passage")
      .setContent("This is the end game passage")
      .build();

    endGamePassage.addLink(new Link("End Game", "End Game"));
    story.addPassage(endGamePassage);

    Passage gameOverPassage = new PassageBuilder()
      .setTitle("Game Over Passage")
      .setContent("This is the game over passage")
      .build();

    gameOverPassage.addLink(new Link("Game Over", "Game Over"));
    story.addPassage(gameOverPassage);

    Passage brokenLinkPassage = new PassageBuilder()
      .setTitle("Broken Link Passage")
      .setContent("This is the broken link passage")
      .build();

    brokenLinkPassage.addLink(new Link("Non-existent Passage", "Non-existent Passage"));

    story.addPassage(brokenLinkPassage);

    List<Link> brokenLinks = story.getBrokenLinks();
    assertEquals(1, brokenLinks.size());
    assertEquals("Non-existent Passage", brokenLinks.get(0).getReference());
  }

  /**
   * Tests the toString method.
   */
  @Test
  void testToString() {
    String expectedString =
      "Story{" +
      " title='" +
      story.getTitle() +
      "'" +
      ", openingPassage='" +
      story.getOpeningPassage() +
      "'" +
      ", passages='" +
      story.getPassages() +
      "'" +
      "}";
    assertEquals(expectedString, story.toString());
  }
}
