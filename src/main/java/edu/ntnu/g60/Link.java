package edu.ntnu.g60;
import java.util.List;
import edu.ntnu.g60.actions.Action;

public class Link {

  private String text;
  private String reference;
  List<Action> actions; 


  public Link(String text, String reference) {
    this.text = text;
    this.reference = reference;
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

  public String toString() {
    return "text: " + text + ", reference: " + reference;
  }

}
