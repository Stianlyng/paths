package edu.ntnu.g60.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ActionEntityTest {

  private ActionEntity actionEntity;

  @BeforeEach
  public void setUp() {
    actionEntity = new ActionEntity();
  }

  @Test
  public void testGetType() {
    String type = "testType";
    actionEntity.setType(type);
    assertEquals(type, actionEntity.getType());
  }

  @Test
  public void testSetValue() {
    String value = "testValue";
    actionEntity.setValue(value);
    assertEquals(value, actionEntity.getValue());
  }

  @Test
  public void testSetType() {
    String type = "newType";
    actionEntity.setType(type);
    assertEquals(type, actionEntity.getType());
  }

  @Test
  public void testGetValue() {
    String value = "newValue";
    actionEntity.setValue(value);
    assertEquals(value, actionEntity.getValue());
  }
}
