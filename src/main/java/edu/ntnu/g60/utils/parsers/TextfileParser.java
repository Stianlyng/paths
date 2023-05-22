package edu.ntnu.g60.utils.parsers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import edu.ntnu.g60.entities.StoryNotFoundException;
import edu.ntnu.g60.exceptions.StoryParsingException;


/**
 * A utility class for parsing text stories and converting them to JSON format.
 * 
 * @author Stian Lyng
 */
public class TextfileParser {

    private static final Logger LOGGER = Logger.getLogger(TextfileParser.class.getName());
    private static final Pattern LINK_PATTERN = Pattern.compile("\\[(.+?)\\]\\((.+?)\\)");
    private static final Path STORY_PATH = Paths.get("src/main/resources/stories/");
    private static final String PASSAGE_IDENTIFIER = "::";

    /**
     * Parses a text story and saves it in JSON format.
     *
     * @param filename The name of the input text file (without the extension)
     * @throws IOException If there is a problem reading or writing the files
     */
    public static boolean parseStory(File file) {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }

        if (file.isDirectory()) {
            throw new IllegalArgumentException("File cannot be a directory: " + file.getAbsolutePath());
        }
        if (!file.exists() || !file.isFile()) {
            LOGGER.severe("Story file not found: " + file.getAbsolutePath());
            throw new StoryNotFoundException("Story file not found: " + file.getAbsolutePath());
        }

        String fileName = file.getName();

        if (!fileName.endsWith(".paths") && !fileName.endsWith(".txt")) {
            throw new IllegalArgumentException("File must be a .paths or .txt file: " + file.getAbsolutePath());
        }

        if (file.length() == 0) {
            throw new IllegalArgumentException("File is empty: " + file.getAbsolutePath());
        }

        
    
        try {
            LOGGER.info("Parsing story: " + file.getAbsolutePath());
            Path inputPath = Paths.get(file.getAbsolutePath());
            int pos = fileName.lastIndexOf(".");
            if (pos > 0) {
                fileName = fileName.substring(0, pos);
            }
    
            Path outputPath = STORY_PATH.resolve(fileName + ".json");
    
            if (Files.notExists(STORY_PATH)) {
                Files.createDirectories(STORY_PATH);
            }
    
            List<String> lines = Files.readAllLines(inputPath);
            ObjectMapper mapper = new ObjectMapper();
            JsonNodeFactory nodeFactory = JsonNodeFactory.instance;
    
            ObjectNode storyNode = createStoryNode(lines, nodeFactory);
            mapper.writerWithDefaultPrettyPrinter().writeValue(outputPath.toFile(), storyNode);
            return true;
        } catch (IOException e) {
            LOGGER.severe("Error occurred while parsing the story: " + e.getMessage());
            throw new StoryParsingException("Error occurred while parsing the story: " + file.getName(), e);
        }
    }

    /**
     * Creates a story node from a list of lines and a JsonNodeFactory. 
     * The first line is set as the title of the story, and each line that 
     * starts with "::" is treated as a passage, which is converted into a 
     * passage node and added to the "passages" array in the story node.
     */
    private static ObjectNode createStoryNode(List<String> lines, JsonNodeFactory nodeFactory) {
        String  title = lines.get(0);

        if (title.isEmpty() || title.startsWith(PASSAGE_IDENTIFIER)) {
            throw new IllegalArgumentException("Story title cannot be empty");
        }
        if (title.length() > 30) {
            throw new IllegalArgumentException("Story title cannot be longer than 60 characters");
        }
        if (title.length() < 3) {
            throw new IllegalArgumentException("Story title must be at least 3 characters long");
        }

        ObjectNode storyNode = nodeFactory.objectNode();
        storyNode.put("title", title);

        ArrayNode passagesNode = nodeFactory.arrayNode();
        storyNode.set("passages", passagesNode);

        for (int i = 1; i < lines.size(); i++) {
            if (lines.get(i).startsWith("::")) {
                ObjectNode passageNode = createPassageNode(lines, nodeFactory, i);
                passagesNode.add(passageNode);
            }
        }

        return storyNode;
    }

    /**
     * Creates a passage node from the lines of text.
     * 
     * @param lines The lines of text in the story
     * @param nodeFactory The node factory to use for creating nodes
     * @param currentIndex The index of the current line
     * @return
     */
    private static ObjectNode createPassageNode(List<String> lines, JsonNodeFactory nodeFactory, int currentIndex) {
        ObjectNode passageNode = nodeFactory.objectNode();
        String title = lines.get(currentIndex).substring(2).trim();

        if (title.isEmpty()) {
            throw new IllegalArgumentException("Passage title cannot be empty");
        }
        passageNode.put("title", title);
        StringBuilder contentBuilder = new StringBuilder();
        ArrayNode linksNode = nodeFactory.arrayNode();

        currentIndex++;
        while (currentIndex < lines.size() && !lines.get(currentIndex).startsWith(PASSAGE_IDENTIFIER)) {
            Matcher matcher = LINK_PATTERN.matcher(lines.get(currentIndex));

            if (matcher.find()) {
                ObjectNode linkNode = nodeFactory.objectNode();
                String text = matcher.group(1);
                String reference = matcher.group(2);
                
                if (text.isEmpty() || reference.isEmpty()) {
                    throw new IllegalArgumentException("Link text and reference cannot be empty");
                }

                linkNode.put("text", text);
                linkNode.put("reference", reference);
                linksNode.add(linkNode);
            } else {
                contentBuilder.append(lines.get(currentIndex)).append("\n");
            }

            currentIndex++;
        }

        passageNode.put("content", contentBuilder.toString());
        passageNode.set("links", linksNode);

        return passageNode;
    }
   
    public static void main(String[] args) {
       File file = new File("/home/stian/test_story.paths"); 
       TextfileParser.parseStory(file);
    }

}