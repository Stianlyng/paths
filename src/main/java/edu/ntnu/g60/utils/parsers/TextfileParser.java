package edu.ntnu.g60.utils.parsers;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;


/**
 * A utility class for parsing text stories and converting them to JSON format.
 * 
 * @author Stian Lyng
 */
public class TextfileParser {

    private static final Pattern LINK_PATTERN = Pattern.compile("\\[(.+?)\\]\\((.+?)\\)");

    public static final String STORY_PATH = "/stories/";

    /**
     * Parses a text story and saves it in JSON format.
     *
     * @param filename The name of the input text file (without the extension)
     * @throws IOException If there is a problem reading or writing the files
     */
    public static boolean parseStory(File file) {
        try {
            Path inputPath = Paths.get(file.getAbsolutePath());
            String filename = file.getName();
            int pos = filename.lastIndexOf(".");
            if (pos > 0) {
                filename = filename.substring(0, pos);
            }

            String resourcePath = TextfileParser.class.getResource(STORY_PATH).getPath();
            Path outputPath = Paths.get(resourcePath + filename + ".json");
            List<String> lines = Files.readAllLines(inputPath);
            ObjectMapper mapper = new ObjectMapper();
            JsonNodeFactory nodeFactory = JsonNodeFactory.instance;
    
            ObjectNode storyNode = createStoryNode(lines, nodeFactory);
            mapper.writerWithDefaultPrettyPrinter().writeValue(outputPath.toFile(), storyNode);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Creates a story node from a list of lines and a JsonNodeFactory. 
     * The first line is set as the title of the story, and each line that 
     * starts with "::" is treated as a passage, which is converted into a 
     * passage node and added to the "passages" array in the story node.
     */
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