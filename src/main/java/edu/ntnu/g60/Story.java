package edu.ntnu.g60;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;


public class Story {

  private String title;
  private Map<Link, Passage> passages;
  private Passage openingPassage;



  public Story(String title, Passage openingPassage) {
    this.title = title;
    this.openingPassage = openingPassage;
    this.passages = new HashMap<>();
  }

  public String getTitle() {
    return this.title;
  }

  public Passage getOpeningPassage() {
    return this.openingPassage;
  }

  public void addPassage(Passage passage) {
    Link link = new Link(passage.getTitle(), passage.getContent());
    this.passages.put(link, passage);
  }


  public Passage getPassage(Link link) {
    return this.passages.get(link);
  }

  public Collection<Passage> getPassages() {
    return this.passages.values();
  }

}
