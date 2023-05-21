package edu.ntnu.g60.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StoryEntityTest {
    private StoryEntity storyEntity;

    @BeforeEach
    public void setUp() {
        storyEntity = new StoryEntity();
    }

    @Test
    public void testGetTitle() {
        String title = "testTitle";
        storyEntity.setTitle(title);
        assertEquals(title, storyEntity.getTitle());
    }

    @Test
    public void testSetTitle() {
        String title = "newTitle";
        storyEntity.setTitle(title);
        assertEquals(title, storyEntity.getTitle());
    }

    @Test
    public void testGetPassages() {
        List<PassageEntity> passages = new ArrayList<>();
        PassageEntity passage = new PassageEntity();
        passage.setTitle("testPassage");
        passages.add(passage);
        storyEntity.setPassages(passages);
        assertEquals(passages, storyEntity.getPassages());
    }

    @Test
    public void testSetPassages() {
        List<PassageEntity> passages = new ArrayList<>();
        PassageEntity passage = new PassageEntity();
        passage.setTitle("newPassage");
        passages.add(passage);
        storyEntity.setPassages(passages);
        assertEquals(passages, storyEntity.getPassages());
    }

    @Test
    public void testGetAndSetGoals() {
        GoalEntity goals = new GoalEntity();
        goals.setGold(100);
        goals.setHealth(100);
        goals.setScore(100);
        storyEntity.setGoals(goals);
        assertEquals(goals, storyEntity.getGoals());
    }
    
}