package edu.ntnu.g60.models.game;

import java.io.Serializable;

import edu.ntnu.g60.models.passage.Link;

public class Save implements Serializable {
    private static final long serialVersionUID = 1L;
    private Game game;
    private Link currentLink;

    public Save(Game game, Link currentLink) {
        this.game = new Game(game); // Create a deep copy of the Game instance
        this.currentLink = currentLink;
    }

    public Link getCurrentLink() {
        return currentLink;
    }

    public Game getGame() {
        return game;
    }
}