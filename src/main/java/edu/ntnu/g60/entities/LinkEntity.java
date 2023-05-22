package edu.ntnu.g60.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the structure of a link in the JSON file.
 * It is used by the StoryParser to parse the JSON file.
 *
 * @see StoryParser
 * @author Stian Lyng
 */
public class LinkEntity {

  /**
   * This holds the text of the link
   */
  private String text;

  /**
   * This holds the reference to another passage
   */
  private String reference;

  /**
   * This holds the list of actions associated with the link
   */
  @JsonProperty("actions")
  private List<ActionEntity> actions;

  /**
   * Gets the text of the link.
   *
   * @return A string representing the text of the link.
   */
  public String getText() {
    return text;
  }

  /**
   * Sets the text of the link.
   *
   * @param text A string representing the text of the link.
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Gets the reference of the link.
   *
   * @return A string representing the reference of the link.
   */
  public String getReference() {
    return reference;
  }

  /**
   * Sets the reference of the link.
   *
   * @param reference A string representing the reference of the link.
   */
  public void setReference(String reference) {
    this.reference = reference;
  }

  /**
   * Gets the list of actions associated with the link.
   *
   * @return A list of ActionEntity objects representing the actions associated with the link.
   */
  public List<ActionEntity> getActions() {
    return actions == null ? new ArrayList<>() : actions;
  }

  /**
   * Sets the list of actions associated with the link.
   *
   * @param actions A list of ActionEntity objects representing the actions associated with the link.
   */
  public void setActions(List<ActionEntity> actions) {
    this.actions = actions;
  }
}
