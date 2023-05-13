package edu.ntnu.g60.utils.serialization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class SerializationUtilsTest {

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSerializationAndDeserialization() throws IOException, ClassNotFoundException {
        String testObject = "Test String";

        Path testFilePath = tempDir.resolve("test_file");

        boolean serializationResult = SerializationUtils.serializeToFile(testObject, testFilePath.toString());

        assertTrue(serializationResult, "Expected serialization to succeed");

        Object deserializedObject = SerializationUtils.deserializeFromFile(testFilePath.toString());

        assertNotNull(deserializedObject, "Expected deserialization to return an object");
        assertEquals(testObject, deserializedObject, "Deserialized object does not match original object");
    }
}