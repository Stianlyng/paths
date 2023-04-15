package edu.ntnu.g60;

import edu.ntnu.g60.fileHandling.FileParser;
import edu.ntnu.g60.models.*;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.GoldGoal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.models.goals.InventoryGoal;
import edu.ntnu.g60.models.goals.ScoreGoal;

import java.util.List;
import java.util.Scanner;

public class GameRunner {

    public static void main(String[] args) {
        String filePath = "src/main/resources/textFiles/story.txt";

        FileParser parser = new FileParser(filePath);
        Story story = parser.buildStory();
        Player player = new Player("PlayerName");

        List<Goal> goals = List.of( 
                    new HealthGoal(0), 
                    new GoldGoal(0),
                    new InventoryGoal(List.of("Sword", "Shield")),
                    new ScoreGoal(100)
                    );

        Game game = new Game(player, story, goals);

        Scanner scanner = new Scanner(System.in);
        Passage currentPassage = game.begin();
        boolean playing = true;

        while (playing) {
            System.out.println("\n" + currentPassage.getTitle());
            System.out.println(currentPassage.getContent());

            if (currentPassage.hasLinks()) {
                System.out.println("\nChoose an option:");
                List<Link> links = currentPassage.getLinks();
                for (int i = 0; i < links.size(); i++) {
                    System.out.println((i + 1) + ": " + links.get(i).getText());
                }

                int choice = scanner.nextInt();
                Link chosenLink = links.get(choice - 1);
                System.out.println("rap: " + chosenLink);

                // Execute actions associated with the chosen link
                //for (Action action : chosenLink.getActions()) {
                //    action.execute(player);
                //}
                System.out.println("text: " + chosenLink.getText() +
                                    "Ref: " + chosenLink.getReference());

                System.out.println("cpach: " + currentPassage.getTitle());
                //System.out.println("netite: " + story.getPassage(chosenLink).getTitle());
                currentPassage = game.go(chosenLink);
            } else {
                playing = false;
                System.out.println("\nThe story has ended.");
            }

            // Check if goals are fulfilled
            boolean goalsFulfilled = goals.stream().allMatch(goal -> goal.isFulfilled(player));
            if (goalsFulfilled) {
                playing = false;
                System.out.println("\nCongratulations! You have achieved all goals.");
            }
        }

        scanner.close();
    }
}