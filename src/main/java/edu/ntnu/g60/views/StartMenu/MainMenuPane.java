package edu.ntnu.g60.views.StartMenu;

import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.views.ViewObjects;
import edu.ntnu.g60.views.ViewValues;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * MainMenuPane represents the pane in with a user can choose wheter to start, continue or import a game
 * @author olav sie
 */
public class MainMenuPane extends StackPane {

  private static StartMenuController controller;
  public static String saveName;

  /**
   * Constructs a new MainMenuPane object.
   * @throws FileNotFoundException if the file specified is not found.
   */
  public MainMenuPane() throws FileNotFoundException, IOException {
    MainMenuPane.controller = new StartMenuController();
    getChildren().addAll(getStartMenuObjects());
  }

  /**
   * Adds the objects required for the start menu pane.
   *
   * @return a Group containing the stert menu pane objects
   * @throws IOException if the file specified is not found
   */
  private static Group getStartMenuObjects() throws FileNotFoundException {
    Button continueButton = ViewObjects.newButton(
      "Continue",
      421,
      207,
      ViewValues.MENU_BUTTON_ID,
      ViewValues.MENU_BUTTON_HOVER_ID,
      controller::goTocontinueGamePaneAction,
      "Press here to continue from a previous save"
    );
    Button newGameButton = ViewObjects.newButton(
      "New game",
      421,
      274,
      ViewValues.MENU_BUTTON_ID,
      ViewValues.MENU_BUTTON_HOVER_ID,
      controller::goToNewGamePaneAction,
      "Press here to start a new game"
    );
    Button customButton = ViewObjects.newButton(
      "Custom",
      421,
      341,
      ViewValues.MENU_BUTTON_ID,
      ViewValues.MENU_BUTTON_HOVER_ID,
      controller::goToCustomGamePaneAction,
      "Press here to import your own custom game"
    );
    Button backButton = ViewObjects.newButton(
      "Back",
      760,
      524,
      ViewValues.BACK_BUTTON_ID,
      ViewValues.BACK_BUTTON_HOVER_ID,
      controller::goToSelectPlayerPaneAction
    );
    ImageView background = ViewObjects.newImage(
      ViewValues.MENU_BACKGROUND_FOLDERNAME,
      ViewValues.MENU_BACKGROUND_IMAGENAME,
      0,
      0,
      ViewValues.BACKGROUND_WIDTH,
      ViewValues.BACKGROUND_HEIGHT
    );
    ImageView information = ViewObjects.newImage(
      "icons",
      "information.png",
      830,
      20,
      35,
      35,
      controller::goToInformationPaneAction
    );
    ImageView settings = ViewObjects.newImage(
      "icons",
      "settings.png",
      130,
      20,
      35,
      35,
      controller::goToSettingsPaneAction
    );
    return new Group(
      background,
      continueButton,
      newGameButton,
      customButton,
      information,
      settings,
      backButton
    );
  }
}
