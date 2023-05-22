package edu.ntnu.g60.views.StartMenu;

import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.views.ViewObjects;
import edu.ntnu.g60.views.ViewValues;
import java.io.FileNotFoundException;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * SelectPlayerPane represents the pane in with a user can choose or create a player
 * @author olav sie
 */
public class SelectPlayerPane extends StackPane {

  private static StartMenuController controller;
  public static ChoiceBox playerChoice;
  public static String playerName;
  static TextField playerNameTextField;

  /**
   * Constructs a new SelectPlayerPane object.
   * @throws FileNotFoundException if the file specified is not found.
   */
  public SelectPlayerPane() throws FileNotFoundException {
    SelectPlayerPane.controller = new StartMenuController();
    getChildren().addAll(SelectPlayerObjects());
  }

  /**
   * Adds the objects required for the select player pane.
   *
   * @return a Group containing the select player pane objects
   * @throws IOException if the file specified is not found
   */
  private static Group SelectPlayerObjects() throws FileNotFoundException {
    Text informationTitle = ViewObjects.newText("Select or create a player", 45, false, 285, 50);
    Button startButton = ViewObjects.newButton(
      "Create",
      421,
      404,
      ViewValues.MENU_BUTTON_ID,
      ViewValues.MENU_BUTTON_HOVER_ID,
      controller::createPlayerAction
    );
    playerNameTextField = ViewObjects.newTextField("Playername..", 421, 356, "text_field");
    playerChoice =
      ViewObjects.newChoiceBox(controller.getPlayerNames(), 421, 116, "launch_choicebox");
    Button chooseButton = ViewObjects.newButton(
      "Continue",
      421,
      172,
      ViewValues.MENU_BUTTON_ID,
      ViewValues.MENU_BUTTON_HOVER_ID,
      controller::ChoosePlayerAction
    );
    ImageView background = ViewObjects.newImage(
      ViewValues.MENU_BACKGROUND_FOLDERNAME,
      ViewValues.MENU_BACKGROUND_IMAGENAME,
      0,
      0,
      ViewValues.BACKGROUND_WIDTH,
      ViewValues.BACKGROUND_HEIGHT
    );
    return new Group(
      background,
      startButton,
      playerChoice,
      playerNameTextField,
      chooseButton,
      informationTitle
    );
  }

  /**
   * Updates the value of playerName to be equal to the input of playerNameTextField
   */
  public static void updatePlayerName() {
    playerName = playerNameTextField.getText();
  }

  /**
   * Returns the choosen player in the playerChoice ChoiceBox.
   *
   * @return the chosen player in the playerChoice ChoiceBox
   */
  public static String getPlayerChoice() {
    return (String) playerChoice.getValue();
  }
}
