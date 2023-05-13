package edu.ntnu.g60;

import java.util.List;

import edu.ntnu.g60.models.game.Game;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.GoldGoal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.models.goals.InventoryGoal;
import edu.ntnu.g60.models.goals.ScoreGoal;
import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.passage.PassageBuilder;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.models.story.StoryBuilder;

public class App {
    public static void main(String[] args) {
        
        Passage openingPassage = new PassageBuilder()
            .setTitle("Opening Passage")
            .setContent("This is the opening passage")
            .build();
        
        Story story = new StoryBuilder()
            .setTitle("Haunted House")
            .setOpeningPassage(openingPassage)
            .build();
        
        Passage firstPassage = new PassageBuilder()
            .setTitle("passage0")
            .setContent("content0")
            .build();
        story.addPassage(firstPassage);
        Link link = new Link("forward", "passage0");
        openingPassage.addLink(link);

        for (int i = 1; i < 10; i++) {
            Passage p = new PassageBuilder()
                .setTitle("passage" + i)
                .setContent("content" + i)
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
