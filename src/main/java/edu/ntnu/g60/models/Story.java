package edu.ntnu.g60.models;
import java.util.Map;
import java.util.Objects;

import edu.ntnu.g60.Save;

import java.util.HashMap;
import java.util.Collection;

/**
 * The Story class represents the story of the game.
 * It contains the title, the opening passage, and the passages of the story.
 */
public class Story {

  private String title;
  private Map<String, Passage> passages;
  private Passage openingPassage;
  private static Save currentSave;

  /**
   * Constructor for the Story class.
   * @param title The title of the story.
   * @param openingPassage The opening passage of the story.
   * @throws IllegalArgumentException if title is null or blank.
   * @throws IllegalArgumentException if openingPassage is null.
   */
  public Story(String title, Passage openingPassage) throws IllegalArgumentException {
    if (title == null || title.isBlank()) throw new IllegalArgumentException("Title cannot be null or blank.");
    if (openingPassage == null) throw new IllegalArgumentException("Opening passage cannot be null.");
    this.title = title;
    this.openingPassage = openingPassage;
    this.passages = new HashMap<String, Passage>();
  }

  public static void setCurrentSave(Save save){
    currentSave = save;
  }

  public static Save getCurrentSave(){
    return currentSave;
  }


  //todo: add constructor with list of passages
  
  /**
   * Adds a passage to the story.
   * @param passage The passage to add.
   * @throws IllegalArgumentException if passage is null.
   */

  public void addPassage(Passage passage) throws IllegalArgumentException {
    if (passage == null) throw new IllegalArgumentException("Passage cannot be null.");
    String passageTitle = passage.getTitle();
    this.passages.put(passageTitle, passage);
  }

 
  public String getTitle() {
    return this.title;
  }

  public Passage getOpeningPassage() {
    return this.openingPassage;
  }

  /*
  public Passage getPassage(Link link) {
      return this.passages.get(link);
  }
   */

 public Passage getPassage(String title) {
      return this.passages.get(title);
  }

  public Collection<Passage> getPassages() {
    return this.passages.values();
  }

  @Override
  public String toString() {
    return "Story{" +
      " title='" + getTitle() + "'" +
      ", openingPassage='" + getOpeningPassage() + "'" +
      ", passages='" + getPassages() + "'" +
      "}";
  }
  
   public static void main(String[] args) {

    Passage p1 = new Passage("Test", "Test");
    Link link = new Link("Go to test1", "Test1");
    p1.addLink(link);

    Story story = new Story("test", p1);

    Passage p2 = new Passage("Test1", "Test1");
    Link link2 = new Link("Go to test2", "Test2");
    p2.addLink(link2);

    story.addPassage(p2);

    System.out.println(story);

    System.out.println(story.getPassage(link.getReference()));

  }

}
