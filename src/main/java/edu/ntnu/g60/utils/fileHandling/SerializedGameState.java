package edu.ntnu.g60.utils.fileHandling;

import java.io.Serializable;

import edu.ntnu.g60.models.game.Game;
import edu.ntnu.g60.models.passage.Link;

/**
 * This class is used to serialize the state of the game.
 * 
 * @author Stian Lyng
 */
public class SerializedGameState implements Serializable {
    private static final long serialVersionUID = 1L;
    private Game game;
    private Link currentLink;

    public SerializedGameState(Game game, Link currentLink) {
        this.game = new Game(game); 
        this.currentLink = currentLink;
    }

    public Link getCurrentLink() {
        return currentLink;
    }

    public Game getGame() {
        return game;
    }
}