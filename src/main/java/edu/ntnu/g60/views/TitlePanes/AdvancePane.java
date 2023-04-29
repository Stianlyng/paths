package edu.ntnu.g60.views.TitlePanes;

import java.io.FileNotFoundException;
import javafx.scene.layout.StackPane;

public class AdvancePane extends StackPane{

    private static final String text = "Advancing";

    public AdvancePane() throws FileNotFoundException{
        getChildren().addAll(TitlePaneObjects.getObjects(text));
    }
}
