package edu.ntnu.g60.utils;

import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.passage.PassageBuilder;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class SaveTest {

    @Test
    void testExternalization() throws IOException, ClassNotFoundException {
        // Prepare a test Passage object
        Passage testPassage = new PassageBuilder()
                .setTitle("Test Title")
                .setContent("Test Content")
                .setPlayer("Test Player")
                .setEnemy("Test Enemy")
                .setBackground("Test Background")
                .isFightScene(true)
                .build();
        testPassage.addLink(new Link("Test Link Text 1", "Test Link Reference 1"));
        testPassage.addLink(new Link("Test Link Text 2", "Test Link Reference 2"));

        // Prepare a test Save object
        Save testSave = new Save(testPassage, "Test Save Name", 0, "Test Story Name");

        // Write the test Save object to a byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutput out = new ObjectOutputStream(baos)) {
            testSave.writeExternal(out);
        }

        // Read the byte array into a new Save object
        Save deserializedSave = new Save();
        try (ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()))) {
            deserializedSave.readExternal(in);
        }

        // Verify the deserialized Save object
        assertEquals(testSave.getSaveName(), deserializedSave.getSaveName(), "Save name does not match");
        assertEquals(testSave.getSaveNumber(), deserializedSave.getSaveNumber(), "Save number does not match");
        assertEquals(testSave.getStoryName(), deserializedSave.getStoryName(), "Story name does not match");

        Passage deserializedPassage = deserializedSave.getPassage();
        assertNotNull(deserializedPassage, "Deserialized passage is null");
        assertEquals(testPassage.getTitle(), deserializedPassage.getTitle(), "Passage title does not match");
        assertEquals(testPassage.getContent(), deserializedPassage.getContent(), "Passage content does not match");
        assertEquals(testPassage.getPlayer(), deserializedPassage.getPlayer(), "Passage player does not match");
        assertEquals(testPassage.getEnemy(), deserializedPassage.getEnemy(), "Passage enemy does not match");
        assertEquals(testPassage.getBackground(), deserializedPassage.getBackground(), "Passage background does not match");
        assertEquals(testPassage.hasFightScene(), deserializedPassage.hasFightScene(), "Passage fight scene flag does not match");

        assertEquals(testPassage.getLinks().size(), deserializedPassage.getLinks().size(), "Number of passage links does not match");
        for (int i = 0; i < testPassage.getLinks().size(); i++) {
            Link testLink = testPassage.getLinks().get(i);
            Link deserializedLink = deserializedPassage.getLinks().get(i);
            assertEquals(testLink.getText(), deserializedLink.getText(), "Link text does not match");
            assertEquals(testLink.getReference(), deserializedLink.getReference(), "Link reference does not match");
        }
    }
}