package edu.ntnu.g60.utils.fileHandling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import edu.ntnu.g60.entities.PlayerEntity;
import edu.ntnu.g60.models.Player;
import edu.ntnu.g60.models.PlayerBuilder;

public class PlayerParser {
    
    private final File jsonFile;
    
    private final ObjectMapper objectMapper;

    public PlayerParser(String jsonFilePath) {
        this.jsonFile = Paths.get("src/main/resources/saves/" + jsonFilePath + ".json").toFile();
        this.objectMapper = new ObjectMapper();
    }

    public List<Player> parse() {
        try {
            List<PlayerEntity> playerEntities = objectMapper.readValue(jsonFile, new TypeReference<List<PlayerEntity>>() {});

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