package edu.ntnu.g60.models.game;

import java.io.Serializable;

public class Save implements Serializable {
    private static final long serialVersionUID = 1L;
    private String saveName;
    private Game game;

    public Save(String saveName, Game game) {
        this.saveName = saveName;
        this.game = new Game(game); // Create a deep copy of the Game instance
    }

    public String getSaveName() {
        return saveName;
    }

    public Game getGame() {
        return game;
    }
}