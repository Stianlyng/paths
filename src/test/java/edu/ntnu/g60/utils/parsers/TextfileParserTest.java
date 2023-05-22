package edu.ntnu.g60.utils.parsers;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ntnu.g60.entities.StoryNotFoundException;

public class TextfileParserTest {
    
    private File validFile;
    private File invalidFile;
    private File nonExistentFile;
    private File directory;

    @BeforeEach
    void setUp() {
        validFile = new File("src/test/resources/imports/testStory.paths");
        invalidFile = new File("src/test/resources/imports/incorrectFileName.jpg");
        nonExistentFile = new File("src/test/resources/imports/nonExistentFileName.txt");
        directory = new File("src/test/resources/imports/");
    }



    void testParseStoryInvalidFile() {
        assertThrows(IllegalArgumentException.class, () -> TextfileParser.parseStory(invalidFile));
    }

    @Test
    void testParseStoryNonExistentFile() {
        assertThrows(StoryNotFoundException.class, () -> TextfileParser.parseStory(nonExistentFile));
    }

    @Test
    void testParseStoryNullFile() {
        assertThrows(IllegalArgumentException.class, () -> TextfileParser.parseStory(null));
    }

    @Test
    void testParseStoryDirectory() {
        assertThrows(IllegalArgumentException.class, () -> TextfileParser.parseStory(directory));
    }

    @Test
    void emptyStoryTitleShouldThrow() {
        // Setup a file that would result in an error while parsing, for example by having invalid characters
        File errorFile = new File("src/test/resources/imports/emptyTitle.paths");
        assertThrows(IllegalArgumentException.class, () -> TextfileParser.parseStory(errorFile));
    }
    
}