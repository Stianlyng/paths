package edu.ntnu.g60.views.TitlePanes;

import java.io.FileNotFoundException;
import javafx.scene.layout.StackPane;

public class DeathPane extends StackPane{
    
    private static final String text = "GAME OVER";

    public DeathPane() throws FileNotFoundException{
        getChildren().addAll(TitlePaneObjects.getObjects(text));
    }
}
