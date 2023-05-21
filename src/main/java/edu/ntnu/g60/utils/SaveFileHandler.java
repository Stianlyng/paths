package edu.ntnu.g60.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.ntnu.g60.models.game.Game;
import edu.ntnu.g60.models.passage.Link;

/**
 * This class is responsible for saving and loading games to and from files.
 * 
 * @author Stian Lyng
 */
public class SaveFileHandler{
    
    private static final String SAVE_PATH = "/saves/";
    private static final String STORY_PATH = "/stories/";
    
 /**
  * Saves the current state of the Game to a .ser file. The filename is based on the player's name, the story's title, and the provided save name.
  * 
  * @param game The current Game object that will be saved.
  * @param saveName The name for the save file.
  * @param currentPassage The current passage in the game.
  * @throws IllegalStateException if the game parameter is null.
  */
  public static void saveGameToFile(Game game, String saveName, String currentPassage) {

      if (game == null) {
          throw new IllegalStateException("No game to save.");
      }
  
      String fileName = createFormattedSaveFileName(
            game.getPlayer().getName(),
            game.getStory().getTitle(),
            saveName
      );
      // what is the best way to define the filepath? I want to save it in the reources folder. i use maven
      String resourcePath = SaveFileHandler.class.getResource(SAVE_PATH).getPath();

      Link link = new Link(currentPassage, currentPassage);
      SerializedGameState save = new SerializedGameState(game, link);
      try (FileOutputStream fileOut = new FileOutputStream(resourcePath + fileName);
           ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
          out.writeObject(save);
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  

  /**
   * Creates a formatted save file name based on the player's name, the story's title, and the provided save name.
   * @param player The player's name.
   * @param story The story's title.
   * @param saveName The save name.
   * @return The formatted save file name, in the format "player_story_saveName.ser".
   */
  private static String createFormattedSaveFileName(String player, String story, String saveName) {
      return String.format("%s_%s_%s.ser", player, story, saveName);
  }

 /**
  * Loads a game from a .ser file in the /saves/ directory.
  *
  * @param filename The name of the .ser file in the /saves/ directory (without the path).
  * @return The SerializedGameState object representing the saved game state, or null if the file could not be read or does not represent a SerializedGameState.
  */
  public static Optional<SerializedGameState> loadGameFromFile(String fileName) {
      URL resourceUrl = SaveFileHandler.class.getResource(SAVE_PATH + fileName);
      if (resourceUrl == null) {
          System.err.println("Could not find file " + fileName);
          return Optional.empty();
      }
      try (InputStream resourceStream = resourceUrl.openStream();
           ObjectInputStream in = new ObjectInputStream(resourceStream)) {
          SerializedGameState save = (SerializedGameState) in.readObject();
          return Optional.of(save);
      } catch (IOException | ClassNotFoundException e) {
          e.printStackTrace();
          return Optional.empty();
      }
  }

  /**
   * Returns a list of the current active player's available saves.
   *
   * @param playerIdentifier The identifier of the player.
   * @return A list of save names.
   */
  public static Set<String> getPlayerSaves(String playerIdentifier) {
      try (Stream<Path> paths = Files.walk(DefaultValues.SAVE_PATH)) {
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
          Files.deleteIfExists(DefaultValues.SAVE_PATH.resolve(save));
      }
  }

  /**
   * Deletes a specific save.
   * 
   * @param saveName
   * @throws IOException
   */
  public static void deleteSave(String playerIdentifier, String storyName, String saveName) throws IOException {
      String fileName = createFormattedSaveFileName(playerIdentifier, storyName, saveName);
      Files.deleteIfExists(DefaultValues.SAVE_PATH.resolve(fileName));
  }

  
  /**
  * Lists the files in the "src/main/resources/stories" folder.
  *
  * @return a list of file names without extensions
  */
  public static List<String> listFilesInFolder() {

      try (Stream<Path> paths = Files.list(DefaultValues.STORY_PATH)) {
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

     try (Stream<Path> paths = Files.walk(DefaultValues.SAVE_PATH)) {
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