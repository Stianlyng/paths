package edu.ntnu.g60.frontend;

import java.io.IOException;
import edu.ntnu.g60.Player;
import javafx.application.Application;

import javafx.stage.Stage;

public class ApplicationFront extends Application {
    
    private static Stage stage;
    static int textLine;

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

    public static int getTextLine(){
        return textLine;
    }

    
    public static void setTextLine(int amount){
        textLine = amount;
    }

    


    public static void main(String[] args){launch();}
}
