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
    // TODO: sjekk om det skal legges til en sjekk for om reference er en gyldig passage
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
  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || this.getClass() != obj.getClass()) return false;
    Link link = (Link) obj;
    return  Objects.equals(this.text, link.text) && 
            Objects.equals(this.reference, link.reference);// &&
           // Objects.equals(this.actions, link.actions);

  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Link)) return false;
    Link link = (Link) o;
    return getText().equals(link.getText()) && Objects.equals(getReference(), link.getReference());
  }
  
  @Override
  public int hashCode() {
    //return Objects.hash(text, reference, actions);
    return Objects.hash(text, reference);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getText(), getReference());
  }
  */

  /**
   * Two links are equal if they have the same reference
   * This is to make the required map of links to passages
   * point links with different text and actions to the same passage
   *
   * @param o Object - Any object equality checked with this link
   * @return boolean - true if the links are equal, false if not
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Link)) return false;
    Link link = (Link) o;
    return Objects.equals(getReference(), link.getReference());
  }

  /**
   * Returns a hashcode for the link based on the reference
   *
   * @return int - hashcode for the link
   */
  @Override
  public int hashCode() {
    return Objects.hash(getReference());
  }

}
