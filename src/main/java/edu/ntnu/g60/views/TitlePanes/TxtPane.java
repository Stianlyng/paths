package edu.ntnu.g60.views.TitlePanes;

import java.io.FileNotFoundException;
import javafx.scene.layout.StackPane;

/**
 * The TxtPane class extends StackPane and represents a custom stack pane
 * used for displaying text in a title pane.
 * @author olav sie
 */
public class TxtPane extends StackPane{
    
    /**
     * Constructs a new TxtPane object with the specified text.
     *
     * @param text the text to be displayed in the title pane
     * @throws FileNotFoundException if the background image file is not found
     */
    public TxtPane(String text) throws FileNotFoundException{
        getChildren().addAll(TitlePaneObjects.getObjects(text));
    }
}
