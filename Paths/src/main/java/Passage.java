import java.util.ArrayList;
import java.util.List;

public class Passage{

  private String title;
  private String content;
  private List<Link> links;


  public Passage(String title, String content) {
    this.title = title;
    this.content = content;
    this.links = new ArrayList<>();
  }

  public String getTitle() {
    return this.title;
  }

  public String getContent() {
    return this.content;
  }

  public boolean addLink(Link link) {
    return this.links.add(link);
  }


  public List<Link> getLinks() {
    return this.links;
  }


  public boolean hasLinks() {
    return this.links.isEmpty();
  }




  public String toString() {
    return title + " (" + content + ")";
  }

  // TODO: Implement equals and hashCode

}
