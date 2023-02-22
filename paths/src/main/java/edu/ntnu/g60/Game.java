package edu.ntnu.g60;
import java.util.List;
import edu.ntnu.g60.goals.Goal;


public class Game {

    private Player player; //Todo: Endre til Player objekt
    private Story story;
  
    
    List<Goal> goals;
  
    public Game(Player player, Story story) {
      this.player = player;
      this.story = story;
    }
  
    public Player getPlayer() {
      return this.player;
    }
  
    public Story getStory() {
      return this.story;
    }
  
    public List<Goal> getGoals() {
      return this.goals;
    }
  

public Passage begin() {
  return this.story.getOpeningPassage();
}

public Passage go(Link link) {
  return this.story.getPassage(link);
}
}

  