package edu.ntnu.g60;

import edu.ntnu.g60.models.actions.Action;
import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.GoldGoal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.models.goals.InventoryGoal;
import edu.ntnu.g60.models.goals.ScoreGoal;
import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.utils.fileHandling.StoryParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandLineInterface {
    private GameManager gameManager;

    public CommandLineInterface() {
        gameManager = GameManager.getInstance();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Start a new game");
            System.out.println("2. Load a saved game");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    startNewGame();
                    break;
                case 2:
                    loadGame();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Story chooseStory() {
        Scanner scanner = new Scanner(System.in);
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
    private void startNewGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your player name: ");
        String playerName = scanner.nextLine();
        
        List<String> inventory = List.of("Sword", "Shield");

        Player player = new PlayerBuilder()
                .setName(playerName)
                .setHealth(100)
                .setGold(0)
                .setScore(0)
                .setInventory(inventory)
                .build();


        Story story = chooseStory();
        List<Goal> goals = List.of(
                new HealthGoal(0),
                new GoldGoal(0),
                new InventoryGoal(List.of("Sword", "Shield")),
                new ScoreGoal(100)
        );

        gameManager.setPlayer(player);
        gameManager.setStory(story);
        gameManager.setGoals(goals);
        gameManager.createGame();

        playGame(true);
    }

    private void loadGame() {

        Scanner scanner = new Scanner(System.in);

        int index=1;

        System.out.println("Select player: ");
        List<String> players = gameManager.getAvailablePlayers();
        for (String player : players) {
            System.out.println(index + ". " + player);
            index++;
        }

        int playerNumber = scanner.nextInt() - 1;

        List<String> playerSaves = gameManager.getPlayerSaves(players.get(playerNumber));
        if (playerSaves.isEmpty()) {
            System.out.println("No saves found for this player.");
            return;
        }

        System.out.println("Select story: ");

        index = 1;
        for (String save : playerSaves) {
            String[] split = save.split("_");
            System.out.println(index + ". " + split[1] + " - " + split[2].replace(".ser", ""));
            index++;
        }
        
        int storyNumber = scanner.nextInt() - 1;

        System.out.println(playerSaves.get(storyNumber));
        gameManager.loadGameFromFile(playerSaves.get(storyNumber));
        
        playGame(false);
    }

    private void playGame(boolean newGame) {
        Scanner scanner = new Scanner(System.in);

        if (gameManager.getGame().getCurrentPassage() == null) {
            gameManager.getGame().begin();
            return;
        }

        Passage currentPassage = gameManager.getGame().getCurrentPassage();

        while (true) {
            System.out.println(currentPassage.getContent());
    
            List<Link> links = currentPassage.getLinks();
            if (links.isEmpty()) {
                System.out.println("The story ends here.");
                gameManager.endGame();
                break;
            }
    
            System.out.println("Choose an option:");
            for (int i = 0; i < links.size(); i++) {
                System.out.println((i + 1) + ". " + links.get(i).getText());
            }
    
            System.out.println("Or type 'save' to save the game.");
    
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("save")) {
                if (newGame) {
                    System.out.print("Enter a name for the save: ");
                    String saveName = scanner.nextLine();
                    gameManager.saveGameToFile(saveName);
                } else {
                    gameManager.saveGameToFile("");
                }
                
                System.out.println("Game saved successfully.");
                continue;
            }
    
            int choice = Integer.parseInt(input) - 1;
            Link selectedLink = links.get(choice);
    
            for (Action action : selectedLink.getActions()) {
                action.execute(gameManager.getGame().getPlayer());
            }
    
            currentPassage = gameManager.getGame().go(selectedLink);
        }
    }

    public static void main(String[] args) {
        CommandLineInterface cli = new CommandLineInterface();
        cli.start();
    }
}