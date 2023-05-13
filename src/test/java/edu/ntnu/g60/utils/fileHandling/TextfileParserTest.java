package edu.ntnu.g60.utils.fileHandling;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class TextfileParserTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testParseStory() throws IOException {
        // Prepare a test story file
        String filename = "test_story";
        Path testStoryPath = Paths.get("src/main/resources/stories/" + filename + ".txt");
        Files.writeString(testStoryPath, "Haunted House\n\n:: Test Passage\nThis is a test passage.\n[Try to open the door](Another room)\n[Stay](Room 3)\n");

        TextfileParser.parseStory(filename);

        Path expectedJsonPath = Paths.get("src/main/resources/stories/" + filename + ".json");
        assertTrue(Files.exists(expectedJsonPath), "Expected output file was not created");

        JsonNode jsonNode = objectMapper.readTree(expectedJsonPath.toFile());
        assertEquals("Haunted House", jsonNode.get("title").asText(), "Title does not match");
        assertEquals("Test Passage", jsonNode.get("passages").get(0).get("title").asText(), "Title does not match");
        assertEquals("This is a test passage.\n", jsonNode.get("passages").get(0).get("content").asText(), "Content does not match");

        Files.deleteIfExists(testStoryPath);
        Files.deleteIfExists(expectedJsonPath);
    }
}