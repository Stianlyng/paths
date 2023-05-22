package edu.ntnu.g60.utils.fileHandling;

import edu.ntnu.g60.models.game.Game;
import edu.ntnu.g60.models.passage.Link;
import java.io.Serializable;

/**
 * This class is used to serialize the state of the game.
 * Including the curreent game instance and the link to the current passage.
 *
 * @author Stian Lyng
 */
public class SerializedGameState implements Serializable {

  private static final long serialVersionUID = 1L;
  private Game game;
  private Link currentLink;

  /**
   * Constructs a new SerializedGameState with a copy of the specified game and the provided link to the current passage.
   *
   * @param game The current Game object to be saved.
   * @param currentLink The link to the current passage in the game.
   */
  public SerializedGameState(Game game, Link currentLink) {
    this.game = new Game(game);
    this.currentLink = currentLink;
  }

  /**
   * Returns the link to the current passage in the game at the time of serialization.
   * @see Game.go(link) for how to use this link to restore the state.
   *
   * @return The Link object representing the current passage.
   */
  public Link getCurrentLink() {
    return currentLink;
  }

  /**
   * Returns a copy of the game state at the time of serialization.
   *
   * @return The Game object representing the state of the game.
   */
  public Game getGame() {
    return game;
  }
}
