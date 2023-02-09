public class Game {

    String player; //Todo: Endre til Player objekt
    Story story;
  
    // Todo; lag en Goal klasse
    // List<Goal> goals;
  
    public Game(String player, Story story) {
      this.player = player;
      this.story = story;
    }
  
    public String getPlayer() {
      return this.player;
    }
  
    public Story getStory() {
      return this.story;
    }
  
      /* Todo: Fjern comment, og lag en Goal klasse
      public List<Goal> getGoals() {
          return this.goals;
      }
      */
  
    public Passage begin() {
      return this.story.getOpeningPassage();
    }
  
    public Passage go(Link link) {
      return this.story.getPassage(link);
    }
  }
  
  