package edu.ntnu.g60.utils.fileHandling;

import org.junit.jupiter.api.Test;

import edu.ntnu.g60.utils.frontend.FrontendUtils;

import static org.junit.jupiter.api.Assertions.*;

class FrontendUtilsTest {

    @Test
    void delayTest() {
        long delayMillis = 1000;
        boolean[] continuationExecuted = { false };
        FrontendUtils.delay(delayMillis, () -> continuationExecuted[0] = true);
        assertFalse(continuationExecuted[0]);
        try {
            Thread.sleep(delayMillis + 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertFalse(continuationExecuted[0]);
    }

    @Test
    void splitTextIntoFourLinesTest() {
        String[] conversationParts = {
            "{N}Trial text",
            "{P}More Trial text"
        };
        int conversationPaneNumber = 1;
        String passageContent = "This is text";
        String[] result = FrontendUtils.splitTextIntoFourLines(conversationParts, conversationPaneNumber, passageContent);
        assertEquals(4, result.length);
        assertEquals("More Trial text       ", result[0]);
        assertEquals("", result[1]);
        assertEquals("", result[2]);
        assertEquals("", result[3]);
    }
}
