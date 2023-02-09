import java.util.List;
public class Link {

  private String text;
  private String reference;
  // List<Action> actions; TODO: Implement actions


  public Link(String text, String reference) {
    this.text = text;
    this.reference = reference;
  }

  // setters and getters

  public String getText() {
    return this.text;
  }

  public String getReference() {
    return this.reference;
  }

  public String toString() {
    return "text: " + text + ", reference: " + reference;
  }

  // TODO: Implement equals and hashCode

}
