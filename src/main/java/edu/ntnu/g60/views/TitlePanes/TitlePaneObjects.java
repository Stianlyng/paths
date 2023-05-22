package edu.ntnu.g60.views.TitlePanes;

import edu.ntnu.g60.views.ViewObjects;
import java.io.FileNotFoundException;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * The class provides utility methods for creating and manipulating
 * objects used in title panes.
 * @author olav sie
 */
public class TitlePaneObjects {

  private static final int TEXT_SIZE = 40;
  private static final boolean TEXT_UNDERLINE = false;
  private static final int X_POSITION = 415;
  private static final int Y_POSTION = 328;
  private static final String BACKGROUND_FOLDERNAME = "backgrounds";
  private static final String BACKGROUND_FILENAME = "Background1.png";
  private static final int BACKGROUND_WIDTH = 1643;
  private static final int BACKGORUND_HEIGHT = 1006;

  /**
   * Creates and returns a Group containing objects used in a title pane.
   *
   * @param text the text to be displayed in the title pane
   * @return a group containing the title pane objects
   * @throws FileNotFoundException if the background image file is not found
   */
  public static Group getObjects(String text) throws FileNotFoundException {
    Text lvlText = ViewObjects.newText(text, TEXT_SIZE, TEXT_UNDERLINE, X_POSITION, Y_POSTION);
    ImageView background = ViewObjects.newImage(
      BACKGROUND_FOLDERNAME,
      BACKGROUND_FILENAME,
      0,
      0,
      BACKGROUND_WIDTH,
      BACKGORUND_HEIGHT
    );
    Group root = new Group(background, lvlText);
    return root;
  }
}
