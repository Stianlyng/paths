package edu.ntnu.g60.utils.fileHandling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class for parsing text stories and converting them to JSON format.
 * 
 * @version 1.0
 * @author Stian Lyng
 */
public class TextfileParser {

    private static final Pattern LINK_PATTERN = Pattern.compile("\\[(.+?)\\]\\((.+?)\\)");

    /**
     * Private constructor to prevent instantiation.
     */
    private TextfileParser() {
    }

    public static void main(String[] args) {
        try {
            TextfileParser.parseStory("demo_story");
        } catch (IOException e) {
            // Log the error or handle it as appropriate
            e.printStackTrace();
        }
    }

    /**
     * Parses a text story and saves it in JSON format.
     *
     * @param filename The name of the input text file (without the extension)
     * @throws IOException If there is a problem reading or writing the files
     */
    public static void parseStory(String filename) throws IOException {
        Path inputPath = Paths.get("src/main/resources/stories/" + filename + ".txt");
        Path outputPath = Paths.get("src/main/resources/stories/" + filename + ".json");

        List<String> lines = Files.readAllLines(inputPath);
        ObjectMapper mapper = new ObjectMapper();
        JsonNodeFactory nodeFactory = JsonNodeFactory.instance;

        ObjectNode storyNode = createStoryNode(lines, nodeFactory);
        mapper.writerWithDefaultPrettyPrinter().writeValue(outputPath.toFile(), storyNode);
    }
    

    // The rest of the methods remain unchanged
    private static ObjectNode createStoryNode(List<String> lines, JsonNodeFactory nodeFactory) {
        ObjectNode storyNode = nodeFactory.objectNode();
        storyNode.put("title", lines.get(0));

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
        passageNode.put("title", lines.get(currentIndex).substring(2).trim());
        StringBuilder contentBuilder = new StringBuilder();
        ArrayNode linksNode = nodeFactory.arrayNode();

        currentIndex++;
        while (currentIndex < lines.size() && !lines.get(currentIndex).startsWith("::")) {
            Matcher matcher = LINK_PATTERN.matcher(lines.get(currentIndex));

            if (matcher.find()) {
                ObjectNode linkNode = nodeFactory.objectNode();
                linkNode.put("text", matcher.group(1));
                linkNode.put("reference", matcher.group(2));
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

}