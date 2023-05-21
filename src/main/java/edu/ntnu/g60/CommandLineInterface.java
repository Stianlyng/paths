package edu.ntnu.g60;

import edu.ntnu.g60.exceptions.BrokenLinkException;
import edu.ntnu.g60.models.actions.Action;
import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.models.player.PlayerBuilder;
import edu.ntnu.g60.utils.SaveFileHandler;
import edu.ntnu.g60.utils.SerializedGameState;
import edu.ntnu.g60.utils.parsers.StoryParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CommandLineInterface {
    private GameManager gameManager;
    private static Passage currentPassage;

    public CommandLineInterface() {
        gameManager = GameManager.getInstance();
    }

    public void start() {
    
        clearScreen();
    
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Start a new game");
            System.out.println("2. Load a saved game");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
    
            boolean gameEnded = false;
            switch (choice) {
                case 1:
                    gameEnded = startNewGame();
                    break;
                case 2:
                    gameEnded = loadGame();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
    
            if (gameEnded) {
                // If the game ended due to goals being fulfilled, break out of the loop and start it again.
                continue;
            }
        }
    }
          
    private void chooseStory() {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose a story:");
        List<String> names = SaveFileHandler.listFilesInFolder();
        for (int i = 0; i < names.size(); i++) {
            System.out.println((i + 1) + ": " + names.get(i));
        }
        int choice = scanner.nextInt();
        System.out.println(choice);
        StoryParser parser = new StoryParser(names.get(choice - 1));
        try {
            gameManager.setStory(parser.getStory());
            gameManager.setGoals(parser.getGoals());
        } catch (BrokenLinkException e) {
            System.out.println(e.getMessage());
        }
    }
   
    private boolean startNewGame() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your player name: ");
        String playerName = scanner.nextLine();
        
        List<String> inventory = List.of("Sword");

        Player player = new PlayerBuilder()
                .setName(playerName)
                .setHealth(100)
                .setGold(0)
                .setScore(0)
                .setInventory(inventory)
                .build();

        chooseStory();

        gameManager.setPlayer(player);
        gameManager.createGame();

        return playGame(true);
    }

    private boolean loadGame() {
        clearScreen();
        Scanner scanner = new Scanner(System.in);

        int index=1;

        System.out.println("Select player: ");

        Set<String> players = SaveFileHandler.getAvailablePlayers();
        List<String> playersList = new ArrayList<>(players);

        for (String player : players) {
            System.out.println(index + ". " + player);
            index++;
        }

        int playerNumber = scanner.nextInt() - 1;

        Set<String> playerSaves = SaveFileHandler.getPlayerSaves(playersList.get(playerNumber));
        if (playerSaves.isEmpty()) {
            System.out.println("No saves found for this player.");
        }

        System.out.println("Select story: ");

        index = 1;
        for (String save : playerSaves) {
            String[] split = save.split("_");
            System.out.println(index + ". " + split[1] + " - " + split[2].replace(".ser", ""));
            index++;
        }
        
        int storyNumber = scanner.nextInt() - 1;
        List<String> playerSavesList = new ArrayList<>(playerSaves);
        System.out.println(playerSavesList.get(storyNumber));

        SerializedGameState save = SaveFileHandler.loadGameFromFile(playerSavesList.get(storyNumber));
        gameManager.setPlayer(save.getGame().getPlayer());
        gameManager.setStory(save.getGame().getStory());
        gameManager.setGoals(save.getGame().getGoals());
        gameManager.createGame();
        
        currentPassage = gameManager.getGame().go(save.getCurrentLink()); 
        return playGame(false);
    }

    private static void displayPlayerStats(Player player) {
        System.out.println("Player: " + player.getName() + 
                            "\nHealth: " + player.getHealth() + 
                            ", Gold: " + player.getGold() + 
                            ", Score: " + player.getScore() +
                            ", Inventory: " + player.getInventory());
        }

    private boolean playGame(boolean newGame) {

        Scanner scanner = new Scanner(System.in);


        if (newGame) {
            currentPassage = gameManager.getGame().begin();
        }

        while (true) {

            clearScreen();
            displayPlayerStats(gameManager.getGame().getPlayer());

            System.out.println("Minimum Goals: " + gameManager.getGame().getGoals().toString());

            System.out.println(currentPassage.getContent());
    
            // Check if goals are fulfilled
            boolean goalsFulfilled = gameManager.getGame().getGoals().stream().allMatch(goal -> goal.isFulfilled(gameManager.getGame().getPlayer()));
            if (goalsFulfilled) {
                System.out.println("\nCongratulations! You have achieved all goals.");
                gameManager.endGame();
                return true;  // Return true to indicate that the game ended due to goals being fulfilled.
            }

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
    
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("save")) {
                System.out.print("Enter a name for the save: ");
                String saveName = scanner.nextLine();
                SaveFileHandler.saveGameToFile(gameManager.getGame(), saveName, currentPassage.getTitle());
                
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
        return false; // if game ends 4 some reason..
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        CommandLineInterface cli = new CommandLineInterface();
        cli.start();
    }
}
