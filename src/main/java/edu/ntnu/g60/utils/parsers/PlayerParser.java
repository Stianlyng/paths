package edu.ntnu.g60.utils.parsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ntnu.g60.entities.PlayerEntity;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * This class is responsible for parsing a player from a JSON file.
 *
 * @author Stian Lyng
 */
public class PlayerParser {
  
  /**
   * The logger for this class.
   */
  private static final Logger LOGGER = Logger.getLogger(PlayerParser.class.getName());

  /**
   * The JSON file to parse.
   */
  private final InputStream jsonStream;

  /**
   * The object mapper used to parse the JSON file.
   */
  private final ObjectMapper objectMapper;

  /**
   * The path to the JSON file.
   */
  public static final String SAVE_PATH = "/saves/";

  /**
   * Creates a new PlayerParser object.
   *
   * @param jsonFilename The name of the JSON file to parse.
   */
  public PlayerParser(String jsonFilename) {
    this.jsonStream = PlayerParser.class.getResourceAsStream(SAVE_PATH + jsonFilename + ".json");
    if (this.jsonStream == null) {
      LOGGER.severe("Could not find file: " + jsonFilename);
    }
    this.objectMapper = new ObjectMapper();
  }

  public PlayerParser(InputStream jsonStream) {
    this.jsonStream = jsonStream;
    if (this.jsonStream == null) {
      LOGGER.severe("Could not find file: " + jsonStream);
    }
    this.objectMapper = new ObjectMapper();
  }

  /**
   * Parses the JSON file and returns a Player object.
   *
   * @return A Player object.
   * @throws IOException if an I/O error occurs
   */
  public List<Player> parse() throws IOException {
    try (InputStream is = this.jsonStream) {
      List<PlayerEntity> playerEntities = objectMapper.readValue(
        is,
        new TypeReference<List<PlayerEntity>>() {}
      );
      return playerEntities
        .stream()
        .map(playerEntity ->
          new PlayerBuilder()
            .setName(playerEntity.getName())
            .setHealth(playerEntity.getHealth())
            .setScore(playerEntity.getScore())
            .setGold(playerEntity.getGold())
            .setInventory(playerEntity.getInventory())
            .build()
        )
        .collect(Collectors.toList());
    }
  }
}
