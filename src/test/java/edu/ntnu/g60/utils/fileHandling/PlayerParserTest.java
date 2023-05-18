package edu.ntnu.g60.utils.fileHandling;

import edu.ntnu.g60.entities.PlayerEntity;

import org.junit.jupiter.api.BeforeEach;
import java.util.List;

//import org.mockito.Mockito;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;

//todo: not finished
public class PlayerParserTest {

    @BeforeEach
    public void setUp() {
        //String json = """
        //    [
        //        {
        //            "name": "testName",
        //            "health": 100,
        //            "score": 50,
        //            "gold": 500,
        //            "inventory": ["Sword", "Shield"]
        //        }
        //    ]
        //    """;

        //InputStream jsonInputStream = new ByteArrayInputStream(json.getBytes());

        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setName("testName");
        playerEntity.setHealth(100);
        playerEntity.setScore(50);
        playerEntity.setGold(500);
        playerEntity.setInventory(List.of("Sword", "Shield"));

        //ObjectMapper mockObjectMapper = Mockito.mock(ObjectMapper.class);

    }
}