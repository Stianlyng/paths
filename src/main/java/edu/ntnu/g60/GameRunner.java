package edu.ntnu.g60;

import edu.ntnu.g60.models.*;
import edu.ntnu.g60.models.actions.Action;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.GoldGoal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.models.goals.InventoryGoal;
import edu.ntnu.g60.models.goals.ScoreGoal;
import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.utils.fileHandling.PlayerParser;
import edu.ntnu.g60.utils.fileHandling.StoryParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameRunner {

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static Player choosePlayer(Scanner scanner) {
        clearScreen();

        PlayerParser playerParser = new PlayerParser("player");
        List<Player> players = playerParser.parse();

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
    
    private static Story chooseStory(Scanner scanner) {
        clearScreen();
        System.out.println("Please choose a story:");
        List<String> names = listFilesInFolder();
        for (int i = 0; i < names.size(); i++) {
            System.out.println((i + 1) + ": " + names.get(i));
        }
        int choice = scanner.nextInt();
        System.out.println(choice);
        StoryParser parser = new StoryParser(names.get(choice - 1));
        return parser.build();
    }
   
    private static List<String> listFilesInFolder() {
        Path folderPath = Paths.get("src/main/resources/stories");

        try (Stream<Path> paths = Files.list(folderPath)) {
            return paths.filter(Files::isRegularFile)
                        .map(Path::getFileName)
                        .map(Path::toString)
                        .map(name -> name.substring(0, name.lastIndexOf('.')))
                        .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        Player player = choosePlayer(scanner);

        
        Story story = chooseStory(scanner);

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