package edu.ntnu.g60.Frontend;

import java.io.IOException;
import edu.ntnu.g60.Player;
import javafx.application.Application;

import javafx.stage.Stage;

public class ApplicationFront extends Application {
    
    private static Stage stage;
    static Player player = new Player("Bjørn", 54, 0, 0); //TODO make variable from when player chooses save file
    static int amountOfLines;
    static int lineNumber;

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        ApplicationFront.stage = stage;
        stage.setTitle("Half life 3");
        stage.setScene(OpeningScene.scene());
        stage.setResizable(false);
        stage.show();
    }
    
    public static Stage getStage(){
        return stage;
    }

    public static int getAmountOfLines(){
        return amountOfLines;
    }

    public static int getLineNumber(){
        return lineNumber;
    }

    public static void setAmountOfLines(int amount){
        amountOfLines = amount;
    }

    public static void setLineNumber(int number){
        lineNumber = number;
    }

    public static Player getPlayer(){
        return player;
    }

    public static void setPlayer(Player updatedPlayer){
        player = updatedPlayer;
    }

    public static void main(String[] args){launch();}
}
