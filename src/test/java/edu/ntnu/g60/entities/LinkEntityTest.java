package edu.ntnu.g60.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkEntityTest {

  private LinkEntity linkEntity;

  @BeforeEach
  public void setUp() {
    linkEntity = new LinkEntity();
  }

  @Test
  public void testGetText() {
    String text = "testText";
    linkEntity.setText(text);
    assertEquals(text, linkEntity.getText());
  }

  @Test
  public void testSetText() {
    String text = "newText";
    linkEntity.setText(text);
    assertEquals(text, linkEntity.getText());
  }

  @Test
  public void testGetReference() {
    String reference = "testReference";
    linkEntity.setReference(reference);
    assertEquals(reference, linkEntity.getReference());
  }

  @Test
  public void testSetReference() {
    String reference = "newReference";
    linkEntity.setReference(reference);
    assertEquals(reference, linkEntity.getReference());
  }

  @Test
  public void testGetActions() {
    List<ActionEntity> actions = new ArrayList<>();
    ActionEntity action = new ActionEntity();
    action.setType("testType");
    action.setValue("testValue");
    actions.add(action);
    linkEntity.setActions(actions);
    assertEquals(actions, linkEntity.getActions());
  }

  @Test
  void testGetActionsWhenNull() {
    assertTrue(
      linkEntity.getActions().isEmpty(),
      "Should return an empty list when actions is null"
    );
  }

  @Test
  public void testSetActions() {
    List<ActionEntity> actions = new ArrayList<>();
    ActionEntity action = new ActionEntity();
    action.setType("newType");
    action.setValue("newValue");
    actions.add(action);
    linkEntity.setActions(actions);
    assertEquals(actions, linkEntity.getActions());
  }
}
