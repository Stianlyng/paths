package edu.ntnu.g60.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.ntnu.g60.models.actions.Action;

/**
 * The Link class represents a link between two passages.
 * It allows the player to move from one passage to another.
 */
public class Link {

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
   * Compares this link to another object.
   * @param obj The object to compare with this link.
   * @return True if the object is equal to this link, false otherwise.
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
    return Objects.equals(text, link.text) &&
           Objects.equals(reference, link.reference);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text, reference);
  }

  public static void main(String[] args) {
    Link link1 = new Link("Link 1", "Passage 1");
    Link link2 = new Link("Link 1", "Passage 1");
    
    System.out.println(link1.equals(link2));
  }

}
