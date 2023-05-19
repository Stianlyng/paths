package edu.ntnu.g60.views.TitlePanes;

import java.io.FileNotFoundException;
import javafx.scene.layout.StackPane;
public class TxtPane extends StackPane{
    
    public TxtPane(String text) throws FileNotFoundException{
        getChildren().addAll(TitlePaneObjects.getObjects(text));
    }
}
