package edu.ntnu.g60.utils.fileHandling;
    
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

//todo: not finished
public class StoryParseTest {

    @BeforeEach
    public void setUp() {
        String json = """
            {
                "title":"Test Story",
                "passages":[
                    {
                       "title":"The First Passage",
                       "content":"This is the first passage",
                       "background":"Background1.png",
                       "player":"baby.png",
                       "enemy":"gender.png",
                       "isFight":false,
                       "links":[
                          {
                             "text":"Go to the second passage",
                             "reference":"The Second Passage"
                          }
                        ]
                    },
                    {
                       "title":"The Second Passage",
                       "content":"This is the second passage",
                       "background":"Background1.png",
                       "player":"baby.png",
                       "enemy":"gender.png",
                       "isFight":true,
                       "links":[
                          {
                             "text":"ggez",
                             "reference":"game over"
                          }
                       ]
                    }
                ]
            }
            """;

        InputStream jsonInputStream = new ByteArrayInputStream(json.getBytes());

        ObjectMapper mockObjectMapper = Mockito.mock(ObjectMapper.class);

    }
}