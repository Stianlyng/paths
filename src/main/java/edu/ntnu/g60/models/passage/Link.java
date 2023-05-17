package edu.ntnu.g60.models.passage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.ntnu.g60.models.actions.Action;

/**
 * The Link class represents a link between two passages.
 * It allows the player to move from one passage to another.
 *
 * @author Stian Lyng
 */
public class Link implements Serializable{

  private String text;
  private String reference;
  private List<Action> actions; 

  /**
   * Constructor for the Link class.
   * @param text The text of the link.
   * @param reference The reference to the passage.
   */
  public Link(String text, String reference) throws IllegalArgumentException {
    if (text == null) throw new IllegalArgumentException("Text cannot be null.");
    if (reference == null) throw new IllegalArgumentException("Reference cannot be null.");
    this.text = text;
    this.reference = reference;
    this.actions = new ArrayList<>();
    
  }

  public void addAction(Action action) {
    this.actions.add(action);
  }

  public List<Action> getActions() {
    return this.actions;
  }


  public String getText() {
    return this.text;
  }

  public String getReference() {
    return this.reference;
  }

  @Override
  public String toString() {
    return "Link: " + getText() + ", reference: " + getReference();
  }

  /**
   * Checks if two links are equal.
   * @param obj The object to compare with.
   * @return True if the reference in the links are equal, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
      if (this == obj) {
          return true;
      }
      if (obj == null || getClass() != obj.getClass()) {
          return false;
      }
      Link link = (Link) obj;
      return Objects.equals(reference, link.reference);
  }
  
  /**
   * Returns the hash code of the link.
   * @return The hash code of the link.
   */
  @Override
  public int hashCode() {
      return Objects.hash(reference);
  }

}
