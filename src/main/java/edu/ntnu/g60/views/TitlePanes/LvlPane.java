package edu.ntnu.g60.views.TitlePanes;

import java.io.FileNotFoundException;
import javafx.scene.layout.StackPane;
public class LvlPane extends StackPane{
    
    public LvlPane(String text) throws FileNotFoundException{
        getChildren().addAll(TitlePaneObjects.getObjects(text));
    }
}
