package edu.ntnu.g60.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassageEntityTest {

  private PassageEntity passageEntity;

  @BeforeEach
  public void setUp() {
    passageEntity = new PassageEntity();
  }

  @Test
  public void testGetTitle() {
    String title = "testTitle";
    passageEntity.setTitle(title);
    assertEquals(title, passageEntity.getTitle());
  }

  @Test
  public void testSetTitle() {
    String title = "newTitle";
    passageEntity.setTitle(title);
    assertEquals(title, passageEntity.getTitle());
  }

  @Test
  public void testGetContent() {
    String content = "testContent";
    passageEntity.setContent(content);
    assertEquals(content, passageEntity.getContent());
  }

  @Test
  public void testSetContent() {
    String content = "newContent";
    passageEntity.setContent(content);
    assertEquals(content, passageEntity.getContent());
  }

  @Test
  public void testGetLinks() {
    List<LinkEntity> links = new ArrayList<>();
    LinkEntity link = new LinkEntity();
    link.setText("testLink");
    links.add(link);
    passageEntity.setLinks(links);
    assertEquals(links, passageEntity.getLinks());
  }

  @Test
  public void testSetLinks() {
    List<LinkEntity> links = new ArrayList<>();
    LinkEntity link = new LinkEntity();
    link.setText("newLink");
    links.add(link);
    passageEntity.setLinks(links);
    assertEquals(links, passageEntity.getLinks());
  }

  @Test
  public void testGetBackground() {
    String background = "testBackground";
    passageEntity.setBackground(background);
    assertEquals(background, passageEntity.getBackground());
  }

  @Test
  public void testSetBackground() {
    String background = "newBackground";
    passageEntity.setBackground(background);
    assertEquals(background, passageEntity.getBackground());
  }

  @Test
  public void testGetPlayer() {
    String player = "testPlayer";
    passageEntity.setPlayer(player);
    assertEquals(player, passageEntity.getPlayer());
  }

  @Test
  public void testSetPlayer() {
    String player = "newPlayer";
    passageEntity.setPlayer(player);
    assertEquals(player, passageEntity.getPlayer());
  }

  @Test
  public void testGetEnemy() {
    String enemy = "testEnemy";
    passageEntity.setEnemy(enemy);
    assertEquals(enemy, passageEntity.getEnemy());
  }

  @Test
  public void testSetEnemy() {
    String enemy = "newEnemy";
    passageEntity.setEnemy(enemy);
    assertEquals(enemy, passageEntity.getEnemy());
  }

  @Test
  public void testGetIsFight() {
    Boolean isFight = true;
    passageEntity.setIsFight(isFight);
    assertEquals(isFight, passageEntity.getIsFight());
  }

  @Test
  public void testSetIsFight() {
    Boolean isFight = false;
    passageEntity.setIsFight(isFight);
    assertEquals(isFight, passageEntity.getIsFight());
  }
}
