package edu.ntnu.g60.utils.parsers;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.g60.exceptions.StoryNotFoundException;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing the TextfileParser.
 *
 * @author Stian Lyng
 */
public class TextfileParserTest {

  private File invalidFile;
  private File nonExistentFile;
  private File directory;

  @BeforeEach
  void setUp() {
    invalidFile = new File("src/test/resources/imports/incorrectFileName.jpg");
    nonExistentFile = new File("src/test/resources/imports/nonExistentFileName.txt");
    directory = new File("src/test/resources/imports/");
  }

  /**
   * Tests the parsing on an invalid file.
   */
  void testParseStoryInvalidFile() {
    assertThrows(IllegalArgumentException.class, () -> TextfileParser.parseStory(invalidFile));
  }

  /**
   * Tests the parsing on a non-existent file.
   */
  @Test
  void testParseStoryNonExistentFile() {
    assertThrows(StoryNotFoundException.class, () -> TextfileParser.parseStory(nonExistentFile));
  }

  /**
   * Tests the parsing when a null file is passed.
   */
  @Test
  void testParseStoryNullFile() {
    assertThrows(IllegalArgumentException.class, () -> TextfileParser.parseStory(null));
  }

  /**
   * Tests the parsing when a directory is passed instead of a file.
   */
  @Test
  void testParseStoryDirectory() {
    assertThrows(IllegalArgumentException.class, () -> TextfileParser.parseStory(directory));
  }

  /**
   * Tests the when an empty story title is encountered.
   */
  @Test
  void emptyStoryTitleShouldThrow() {
    // Setup a file that would result in an error while parsing, for example by having invalid characters
    File errorFile = new File("src/test/resources/imports/emptyTitle.paths");
    assertThrows(IllegalArgumentException.class, () -> TextfileParser.parseStory(errorFile));
  }
}
