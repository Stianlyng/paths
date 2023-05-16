package edu.ntnu.g60.utils.fileHandling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.ntnu.g60.models.game.Game;
import edu.ntnu.g60.models.game.Save;
import edu.ntnu.g60.models.passage.Link;

public class SaveFileHandler{
    
  public static void saveGameToFile(Game game, String saveName, String currentPassage) {
      if (game == null) {
          throw new IllegalStateException("No game to save.");
      }
  
      String playerIdentifier = game.getPlayer().getName(); 
      String storyIdentifier = game.getStory().getTitle(); 
      String filePath = "src/main/resources/saves/" +
                            playerIdentifier + "_" + 
                            storyIdentifier + "_" + 
                            saveName + ".ser";
      Link link = new Link(currentPassage, currentPassage);
      Save save = new Save(game, link);
      try (FileOutputStream fileOut = new FileOutputStream(filePath);
           ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
          out.writeObject(save);
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  
  // returns the save and the link to the current passage
  public static Save loadGameFromFile(String filename) {
      String filePath = "src/main/resources/saves/" + filename;
      try (FileInputStream fileIn = new FileInputStream(filePath);
           ObjectInputStream in = new ObjectInputStream(fileIn)) {
          Save save = (Save) in.readObject();
          return save;
      } catch (IOException | ClassNotFoundException e) {
          e.printStackTrace();
          return null;
      }
  }

  /**
   * Returns a list of the current active player's available saves.
   *
   * @param playerIdentifier The identifier of the player.
   * @return A list of save names.
   */
  public static Set<String> getPlayerSaves(String playerIdentifier) {
      try (Stream<Path> paths = Files.walk(Paths.get("src/main/resources/saves/"))) {
          return paths
              .filter(Files::isRegularFile)
              .map(Path::getFileName)
              .map(Path::toString)
              .filter(filename -> filename.startsWith(playerIdentifier + "_"))
              .collect(Collectors.toSet());
      } catch (IOException e) {
          e.printStackTrace();
          return Collections.emptySet();
      }
  }

  /**
   * Deletes all saves for a specific player.
   *
   * @param playerIdentifier The identifier of the player.
   * @throws IOException If an I/O error occurs.
   */
  public static void deletePlayerSaves(String playerIdentifier) throws IOException {
      Set<String> playerSaves = getPlayerSaves(playerIdentifier);
      for (String save : playerSaves) {
          Path filePath = Paths.get("src/main/resources/saves/" + save);
          Files.deleteIfExists(filePath);
      }
  }

  /**
   * Deletes a specific save.
   * 
   * @param saveName
   * @throws IOException
   */
  public static void deleteSave(String playerIdentifier, String saveName) throws IOException {
      Path filePath = Paths.get("src/main/resources/saves/" + playerIdentifier + "_" + saveName);
      Files.deleteIfExists(filePath);
  }

  
  /**
  * Lists the files in the "src/main/resources/stories" folder.
  *
  * @return a list of file names without extensions
  */
  public static List<String> listFilesInFolder() {
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

  /**
  * Returns a set of the available players.
  *
  * @return A set of player identifiers.
  */
  public static Set<String> getAvailablePlayers() {
     try (Stream<Path> paths = Files.walk(Paths.get("src/main/resources/saves/"))) {
         return paths
             .filter(Files::isRegularFile)
             .map(Path::getFileName)
             .map(Path::toString)
             .map(filename -> filename.split("_")[0])
             .collect(Collectors.toSet());
     } catch (IOException e) {
         e.printStackTrace();
         return Collections.emptySet();
     }
  }

}