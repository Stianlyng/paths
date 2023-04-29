package edu.ntnu.g60.views.TitlePanes;

import java.io.FileNotFoundException;

import edu.ntnu.g60.views.ViewObjects;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class TitlePaneObjects {

    private static final int TEXT_SIZE = 40;
    private static final boolean TEXT_UNDERLINE = false;
    private static final int X_POSITION = 315;
    private static final int Y_POSTION = 278;
    private static final String BACKGROUND_FOLDERNAME = "backgrounds";
    private static final String BACKGROUND_FILENAME = "Background1.png";
    private static final int BACKGROUND_WIDTH = 1643;
    private static final int BACKGORUND_HEIGHT = 1006;

    public static Group getObjects(String text) throws FileNotFoundException{
        Text lvlText = ViewObjects.newText(text, TEXT_SIZE, TEXT_UNDERLINE, X_POSITION, Y_POSTION);
        ImageView background = ViewObjects.newImage(BACKGROUND_FOLDERNAME, BACKGROUND_FILENAME, 0 ,0 ,BACKGROUND_WIDTH ,BACKGORUND_HEIGHT);
        Group root = new Group(background, lvlText);
        return root;
    }
}
