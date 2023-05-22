package edu.ntnu.g60.utils.parsers;

import edu.ntnu.g60.models.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the PlayerParser class.
 * @author Stian Lyng
 */
class PlayerParserTest {

    private static final String PLAYER_JSON = """
            [
                {
                    "name": "Player1",
                    "health": 100,
                    "score": 50,
                    "gold": 1000,
                    "inventory": [
                        "sword",
                        "shield"
                    ]
                },
                {
                    "name": "Player2",
                    "health": 50,
                    "score": 100,
                    "gold": 500,
                    "inventory": [
                        "gun",
                        "bazookah"
                    ]
                }
            ]
            """;

    private PlayerParser parser;

    /**
     * Creates PlayerParser object with the JSON string stream as input.
     */
    @BeforeEach
    void setUp() {
        InputStream stream = new ByteArrayInputStream(PLAYER_JSON.getBytes(StandardCharsets.UTF_8));
        parser = new PlayerParser(stream);
    }

    /**
     * Ensures that the parse method correctly parses player data from a JSON string into a list of Player objects
     * with accurate properties.
     * @throws IOException if an I/O error occurs
     */
    @Test
    void testParsePlayers() throws IOException {
        List<Player> players = parser.parse();

        assertEquals(2, players.size());

        Player player1 = players.get(0);
        assertEquals("Player1", player1.getName());
        assertEquals(100, player1.getHealth());
        assertEquals(50, player1.getScore());
        assertEquals(1000, player1.getGold());
        assertEquals(Arrays.asList("sword", "shield"), player1.getInventory());

        Player player2 = players.get(1);
        assertEquals("Player2", player2.getName());
        assertEquals(50, player2.getHealth());
        assertEquals(100, player2.getScore());
        assertEquals(500, player2.getGold());
        assertEquals(Arrays.asList("gun", "bazookah"), player2.getInventory());
    }

    /**
     * Tests if the PlayerParser correctly throws an IllegalArgumentException
     * when attempting to parse data from a nonexistent file.
     */
    @Test
    void testIOException() {
        assertThrows(IllegalArgumentException.class, () -> {
            PlayerParser parser = new PlayerParser("NonExistentFile.json");
            parser.parse();
        });
    }
}