package edu.ntnu.g60.utils.parsers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import edu.ntnu.g60.entities.PlayerEntity;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;
import edu.ntnu.g60.utils.DefaultValues;

/**
 * This class is responsible for parsing a player from a JSON file.
 * 
 * @author Stian Lyng
 */
public class PlayerParser {

    /**
     * The JSON file to parse.
     */
    private final File jsonFile;

    /**
     * The object mapper used to parse the JSON file.
     */
    private final ObjectMapper objectMapper;

    /**
     * Creates a new PlayerParser object.
     * 
     * @param jsonFilename The name of the JSON file to parse.
     */
    public PlayerParser(String jsonFilename) {
        this.jsonFile = DefaultValues.SAVE_PATH.resolve(jsonFilename + ".json").toFile();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Parses the JSON file and returns a Player object.
     * 
     * @return A Player object.
     */
    public List<Player> parse() {
        try {
            List<PlayerEntity> playerEntities = objectMapper.readValue(jsonFile,
                    new TypeReference<List<PlayerEntity>>() {
                    });

            return playerEntities.stream()
                    .map(playerEntity -> new PlayerBuilder()
                            .setName(playerEntity.getName())
                            .setHealth(playerEntity.getHealth())
                            .setScore(playerEntity.getScore())
                            .setGold(playerEntity.getGold())
                            .setInventory(playerEntity.getInventory())
                            .build())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}