package edu.ntnu.g60;

import java.util.List;

import edu.ntnu.g60.models.Game;
import edu.ntnu.g60.models.Link;
import edu.ntnu.g60.models.Passage;
import edu.ntnu.g60.models.PassageBuilder;
import edu.ntnu.g60.models.Player;
import edu.ntnu.g60.models.PlayerBuilder;
import edu.ntnu.g60.models.Story;
import edu.ntnu.g60.models.StoryBuilder;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.GoldGoal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.models.goals.InventoryGoal;
import edu.ntnu.g60.models.goals.ScoreGoal;

public class App {
    public static void main(String[] args) {
        
        Passage openingPassage = new PassageBuilder()
            .withTitle("Opening Passage")
            .withContent("This is the opening passage")
            .build();
        
        Story story = new StoryBuilder()
            .setTitle("Haunted House")
            .setOpeningPassage(openingPassage)
            .build();
        
        Passage firstPassage = new PassageBuilder()
            .withTitle("passage0")
            .withContent("content0")
            .build();
        story.addPassage(firstPassage);
        Link link = new Link("forward", "passage0");
        openingPassage.addLink(link);

        for (int i = 1; i < 10; i++) {
            Passage p = new PassageBuilder()
                .withTitle("passage" + i)
                .withContent("content" + i)
                .build();
            story.addPassage(p);
            Link linkForward = new Link("forward", "passage" + (i + 1));
            Link linkBack = new Link("backwards", "passage" + (i - 1));
            p.addLink(linkForward);
            p.addLink(linkBack);
        }

        List<Goal> goals = List.of( 
                    new HealthGoal(0), 
                    new GoldGoal(0),
                    new InventoryGoal(List.of("Sword", "Shield")),
                    new ScoreGoal(100)
                    );

        Player player = new PlayerBuilder()
                        .setName("Stian")
                        .build();
            
        Game game = new Game(player, story, goals);
       
        game.begin();

        System.out.println(game.getStory().getOpeningPassage().toString());
        //System.out.println(game.getStory().getOpeningPassage().getLinks().get(0));

        Link nextLink = game.getStory().getOpeningPassage().getLinks().get(0);
        //System.out.println(nextLink);
        Link nextLink2 = new Link("forward", "passage0");

        // check if the link equals the reference
        System.out.println(nextLink.equals(nextLink2));
        Passage pas = game.go(nextLink);

        System.out.println(pas);

        //System.out.println(game.getStory().getOpeningPassage().getLinks().get(0));
    }
}
