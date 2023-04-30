package edu.ntnu.g60;

import edu.ntnu.g60.models.*;
import edu.ntnu.g60.models.actions.Action;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.GoldGoal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.models.goals.InventoryGoal;
import edu.ntnu.g60.models.goals.ScoreGoal;
import edu.ntnu.g60.utils.fileHandling.PlayerParser;
import edu.ntnu.g60.utils.fileHandling.StoryParser;

import java.util.List;
import java.util.Scanner;

public class GameRunner {

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static Player choosePlayer(List<Player> players, Scanner scanner) {
        clearScreen();
        System.out.println("Please choose a player:");
        for (int i = 0; i < players.size(); i++) {
            System.out.println((i + 1) + ": " + players.get(i).getName());
        }

        int choice = scanner.nextInt();
        return players.get(choice - 1);
    }

    private static void displayPlayerStats(Player player) {
        System.out.println("\n" + player.getName() + " has " + player.getHealth() + " health and " + player.getGold() + " gold.");
    }

    private static void displayPassageInfo(Passage passage) {
        System.out.println("Background: " + passage.getBackground() +
                " Player: " + passage.getPlayer() +
                " Enemy: " + passage.getEnemy() +
                " isFight: " + passage.hasFightScene());
    }

    private static int promptChoice(Scanner scanner, List<Link> links) {
        System.out.println("\nChoose an option:");
        for (int i = 0; i < links.size(); i++) {
            System.out.println((i + 1) + ": " + links.get(i).getText());
        }
        return scanner.nextInt();
    }

    public static void main(String[] args) {

        StoryParser parser = new StoryParser("haunted_house");
        Story story = parser.build();

        PlayerParser playerParser = new PlayerParser("player");
        List<Player> players = playerParser.parse();

        Scanner scanner = new Scanner(System.in);

        Player player = choosePlayer(players, scanner);

        List<Goal> goals = List.of(
                new HealthGoal(0),
                new GoldGoal(0),
                new InventoryGoal(List.of("Sword", "Shield")),
                new ScoreGoal(100)
        );

        Game game = new Game(player, story, goals);

        Passage currentPassage = game.begin();
        boolean playing = true;

        while (playing) {
            clearScreen();
            displayPlayerStats(player);
            displayPassageInfo(currentPassage);

            System.out.println("\n" + currentPassage.getTitle());
            System.out.println(currentPassage.getContent());

            if (currentPassage.hasLinks()) {
                int choice = promptChoice(scanner, currentPassage.getLinks());
                Link chosenLink = currentPassage.getLinks().get(choice - 1);

                //Execute actions associated with the chosen link
                for (Action action : chosenLink.getActions()) {
                    action.execute(player);
                }
                System.out.println("text: " + chosenLink.getText() +
                        "Ref: " + chosenLink.getReference());

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