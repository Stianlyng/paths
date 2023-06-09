package edu.ntnu.g60.utils.fileHandling;

import edu.ntnu.g60.models.game.Game;
import edu.ntnu.g60.models.passage.Link;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class is responsible for saving and loading games to and from files.
 * The paths to the save files are relative to the resources folder using the getResource() method.;w
 *
 * @author Stian Lyng
 */
public class SaveFileHandler {
  
  
  /**
   * The logger for this class.
   */
  private static final Logger LOGGER = Logger.getLogger(SaveFileHandler.class.getName());

  /**
   * The relative path to the save files.
   */
  private static final URL SAVE_PATH = SaveFileHandler.class.getResource("/saves/");

  /**
   * The relative path to the story files.
   */
  private static final URL STORY_PATH = SaveFileHandler.class.getResource("/stories/");

  /**
   * Saves the current state of the Game to a .ser file. The filename is based on the player's name, the story's title, and the provided save name.
   *
   * @param game The current Game object that will be saved.
   * @param saveName The name for the save file.
   * @param currentPassage The current passage in the game.
   * @throws IllegalStateException if the game parameter is null.
   */
  public static void saveGameToFile(Game game, String saveName, String currentPassage) {

    if (SAVE_PATH == null) {
      // create 
    }
    String fileName = createFormattedSaveFileName(
      game.getPlayer().getName(),
      game.getStory().getTitle(),
      saveName
    );
    String path = SAVE_PATH.getPath() + fileName;
    Link link = new Link(currentPassage, currentPassage);
    SerializedGameState save = new SerializedGameState(game, link);
    try (
      FileOutputStream fileOut = new FileOutputStream(path);
      ObjectOutputStream out = new ObjectOutputStream(fileOut)
    ) {
      LOGGER.info("Saving game to file: " + path);
      out.writeObject(save);
    } catch (IOException e) {
      LOGGER.severe("Could not save game to file: " + path + "\n" + e.getStackTrace());
    }
  }

  /**
   * Creates a formatted file name based on the player's name, the story's title, and the provided save name.
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
    String path = SAVE_PATH.getPath() + fileName;
    try (
      FileInputStream fileIn = new FileInputStream(path);
      ObjectInputStream in = new ObjectInputStream(fileIn)
    ) {
      SerializedGameState save = (SerializedGameState) in.readObject();
      LOGGER.info("Loaded game from file: " + path);
      return Optional.of(save);
    } catch (IOException | ClassNotFoundException e) {
      LOGGER.severe("Could not load game from file: " + path + "\n" + e.getStackTrace());
      return Optional.empty();
    }
  }

  /**
   * Returns a list of the current active player's available saves.
   * The saves are represented as the save file names
   *
   * @param playerIdentifier The name of the player.
   * @return A set of save names for the given player.
   * @throws URISyntaxException If the path to the save files is invalid.
   */
  public static Set<String> getPlayerSaves(String playerIdentifier) throws URISyntaxException {
    if (SAVE_PATH == null) {
      return Collections.emptySet();
    }
    Path path = Paths.get(SAVE_PATH.toURI());

    try (Stream<Path> paths = Files.walk(path)) {
      LOGGER.info("Getting player saves for: " + playerIdentifier);
      return paths
        .filter(Files::isRegularFile)
        .map(Path::getFileName)
        .map(Path::toString)
        .filter(filename -> filename.startsWith(playerIdentifier + "_") && filename.endsWith(".ser"))
        .collect(Collectors.toSet());
    } catch (IOException e) {
      LOGGER.severe("Could not get player saves: " + e.getStackTrace());
      return Collections.emptySet();
    }
  }

  /**
   * Deletes all saves for a specific player.
   *
   * @param playerIdentifier The name of the player.
   * @throws IOException If an I/O error occurs.
   * @throws URISyntaxException if the path to the save files is invalid.
   */
  public static void deletePlayerSaves(String playerIdentifier)
    throws IOException, URISyntaxException {
      Set<String> playerSaves = getPlayerSaves(playerIdentifier);
      for (String save : playerSaves) {
        if (save.endsWith(".ser")) {
          Path savePath = Paths.get(SAVE_PATH.toURI());
          Path path = savePath.resolve(save);
          LOGGER.info("Deleting save: " + path);
          Files.deleteIfExists(path);
        }
      }
  }

  /**
   * Lists the files in the "/resources/stories" folder.
   *
   * @return a list of file names without extensions.
   * @throws URISyntaxException if the path to the story files is invalid.
   */
  public static List<String> listFilesInFolder() throws URISyntaxException {
    Path path = Paths.get(STORY_PATH.toURI());

    try (Stream<Path> paths = Files.list(path)) {
      LOGGER.info("Listing files in folder: " + path);
      return paths
        .filter(Files::isRegularFile)
        .map(Path::getFileName)
        .map(Path::toString)
        .map(name -> name.substring(0, name.lastIndexOf('.')))
        .collect(Collectors.toList());
    } catch (IOException e) {
      LOGGER.severe("Could not list files in folder: " + path + "\n" + e.getStackTrace());
      return null;
    }
  }

  /**
   * Returns a set of the available players.
   *
   * @return A set of playernames.
   * @throws URISyntaxException if the path to the save files is invalid.
   */
  public static Set<String> getAvailablePlayers() throws URISyntaxException {
    if (SAVE_PATH == null) {
      LOGGER.severe("Could not get available players: save path is null");
      return Collections.emptySet();
    }
    Path path = Paths.get(SAVE_PATH.toURI());

    try (Stream<Path> paths = Files.walk(path)) {
      LOGGER.info("Getting available players");
      return paths
        .filter(Files::isRegularFile)
        .map(Path::getFileName)
        .map(Path::toString)
        .filter(filename -> filename.endsWith(".ser"))
        .map(filename -> filename.split("_")[0])
        .collect(Collectors.toSet());
    } catch (IOException e) {
      LOGGER.severe("Could not get available players: " + e.getStackTrace());
      return Collections.emptySet();
    }
  }
}