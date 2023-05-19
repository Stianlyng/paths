package edu.ntnu.g60.views;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class GameView extends Pane {
    private int width;
    private int height;
    private String playerName;

    public GameView(String playerName,int width, int height) {
        this.width = width;
        this.height = height;
        this.playerName = playerName;

        Label gameLabel = new Label("Game View: " + playerName);
        this.getChildren().add(gameLabel);
    }

    public Pane getLayout() {
        return this;
    }
}