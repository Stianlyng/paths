package edu.ntnu.g60;

import java.util.List;

import edu.ntnu.g60.models.Game;
import edu.ntnu.g60.models.Link;
import edu.ntnu.g60.models.Passage;
import edu.ntnu.g60.models.Player;
import edu.ntnu.g60.models.Story;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.GoldGoal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.models.goals.InventoryGoal;
import edu.ntnu.g60.models.goals.ScoreGoal;

public class App {
    public static void main(String[] args) {
        
        Passage openingPassage = new Passage("Opening Passage", "This is the opening passage");
        Link link = new Link("forward", "passage0");
        openingPassage.addLink(link);

        Story story = new Story("Haunted House", openingPassage);
        
        for (int i = 0; i < 10;i++) {
            Passage p = new Passage("passage" + i, "content" + i);
            story.addPassage(p);
            Link linkForward = new Link("forward", "passage" + (i+1));
            Link linkBack = new Link("backwards", "passage" + (i-1));
            p.addLink(linkForward);
            p.addLink(linkBack);
        }   


        List<Goal> goals = List.of( 
                    new HealthGoal(0), 
                    new GoldGoal(0),
                    new InventoryGoal(List.of("Sword", "Shield")),
                    new ScoreGoal(100)
                    );

            
        Game game = new Game(new Player("Stian"), story, goals);
       
        game.begin();

        System.out.println(game.getStory().getOpeningPassage().toString());
        System.out.println(game.getStory().getOpeningPassage().getLinks().get(0));

        Link nextLink = game.getStory().getOpeningPassage().getLinks().get(0);

        Passage pas = game.go(nextLink);

        System.out.println(pas);

        /*
        System.out.println("Story title: " + story.getTitle());
        
        Passage getOpeningPassage = story.getOpeningPassage();
        System.out.println("Opening passage title: " + getOpeningPassage.getTitle());
        System.out.println("Opening passage content: " + getOpeningPassage.getContent());
        System.out.println("Opening passage links: " + getOpeningPassage.getLinks());
        
        story.getPassages().forEach((passage) -> {
            System.out.println("Passage title: " + passage.getTitle());
            System.out.println("Passage content: " + passage.getContent());
            System.out.println("Passage links: " + passage.getLinks());
        });
        */
        
    }
}