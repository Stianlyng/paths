package edu.ntnu.g60.views.GamePanes;

import edu.ntnu.g60.controllers.MiniGameController;
import edu.ntnu.g60.models.game.Game;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.views.ViewObjects;
import java.io.FileNotFoundException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * MiniGamePane represents a pane for a mini-game fight scene
 * @author olav sie
 */
public class MiniGamePane extends StackPane {

  public static Game game = MiniGameController.game;
  public static Passage passage;

  static ProgressBar playerBar;
  static ProgressBar enemyBar;

  public static MiniGameController controller;

  //TODO: change name of passage and fix to private non static
  /**
   * Constructs a new MiniGamePane object.
   * @throws FileNotFoundException if the file specified is not found.
   */
  public MiniGamePane(Passage npassage) throws FileNotFoundException {
    passage = npassage;
    MiniGamePane.controller = new MiniGameController(passage);
    getChildren().addAll(addFightPaneObjects());
  }

  /**
   * Updates the playerBar to show the specified health
   * @param playerHealth a players updated health
   */
  public static void updateHealthPlayer(float playerHealth) {
    playerBar.setProgress(playerHealth);
  }

  /**
   * Updates the enemyBar to show the specified health
   * @param playerHealth a enemyes updated health
   */
  public static void updateHealthEnemy(float enemyHealth) {
    enemyBar.setProgress(enemyHealth);
  }

  /**
   * Adds the objects required for the minigame pane.
   *
   * @return a Group containing the fight minigame objects
   * @throws FileNotFoundException if the file specified is not found
   */
  public static Group addFightPaneObjects() throws FileNotFoundException {
    ImageView enemyImage = ViewObjects.newImage(
      "characters",
      passage.getPlayerImage(),
      150,
      200,
      150,
      150
    );
    ImageView playerImage = ViewObjects.newImage(
      "characters",
      passage.getEnemyImage(),
      700,
      200,
      150,
      150
    );
    ImageView backgroundImage = ViewObjects.newImage(
      "backgrounds",
      passage.getBackgroundImage(),
      0,
      0,
      1650,
      1000
    );
    Button fightButton = ViewObjects.newButton(
      "Fight",
      116,
      454,
      "fight_button",
      "fight_hover",
      controller::fightAction
    );
    Button healButton = ViewObjects.newButton(
      "Heal",
      511,
      454,
      "heal_button",
      "heal_hover",
      controller::healAction
    );
    Button inventoryButton = ViewObjects.newButton(
      "Inventory",
      116,
      545,
      "inventory_button",
      "inventory_hover",
      controller::inventoryAction
    );
    Button escapeButton = ViewObjects.newButton(
      "Escape",
      511,
      545,
      "escape_button",
      "escape_hover",
      controller::escapeAction
    );
    ImageView coinIcon = ViewObjects.newImage("icons", "coin.png", 116, 65, 24, 24);
    ImageView scoreIcon = ViewObjects.newImage("icons", "star.png", 196, 65, 24, 24);
    Text scoreText = ViewObjects.newText("" + game.getPlayer().getScore(), 18, false, 229, 84);
    Text goldText = ViewObjects.newText("" + game.getPlayer().getGold(), 18, false, 149, 84);
    Rectangle infoBoard = ViewObjects.newRectangle(110, 58, 163, 38);
    MenuButton dropDown = ViewObjects.newMenuButton(
      controller::goToMenuAndSaveAction,
      controller::exitApplicationAction,
      "menu_button",
      "menu_hover",
      283,
      129 - 71,
      "Save and go to main menu",
      "Exit application"
    );
    playerBar = ViewObjects.newHealthBar(116, 427, 1.00F, "progress_bar");
    enemyBar = ViewObjects.newHealthBar(511, 427, 1.00F, "progress_bar");
    return new Group(
      backgroundImage,
      infoBoard,
      enemyImage,
      playerImage,
      fightButton,
      healButton,
      inventoryButton,
      escapeButton,
      coinIcon,
      scoreIcon,
      scoreText,
      goldText,
      playerBar,
      enemyBar,
      dropDown
    );
  }

  /**
   * Adds the win text to the MiniGamePane.
   *
   * @param pane the MiniGamePane object
   */
  public static void addWinText(MiniGamePane pane) {
    Text winText = ViewObjects.newText("You won!", 50, false, 0, 0);
    pane.getChildren().addAll(winText);
  }

  /**
   * Adds the lose text to the MiniGamePane.
   *
   * @param pane the MiniGamePane object
   */
  public static void addLooseText(MiniGamePane pane) {
    Text looseText = ViewObjects.newText("You lost!", 50, false, 0, 0);
    pane.getChildren().addAll(looseText);
  }

  /**
   * Adds the default buttons to the MiniGamePane.
   *
   * @param pane the MiniGamePane object
   */
  public static void addDefaultObjects(MiniGamePane pane) {
    Button fightButton = ViewObjects.newButton(
      "Fight",
      116,
      463,
      "fight_button",
      "fight_hover",
      controller::fightAction
    );
    Button healButton = ViewObjects.newButton(
      "Heal",
      511,
      463,
      "heal_button",
      "heal_hover",
      controller::healAction
    );
    Button inventoryButton = ViewObjects.newButton(
      "Inventory",
      116,
      554,
      "inventory_button",
      "inventory_hover",
      controller::inventoryAction
    );
    Button escapeButton = ViewObjects.newButton(
      "Escape",
      511,
      554,
      "escape_button",
      "escape_hover",
      controller::escapeAction
    );
    StackPane.setAlignment(fightButton, Pos.BOTTOM_LEFT);
    StackPane.setMargin(fightButton, new Insets(50, 16, 109, 16));
    StackPane.setAlignment(healButton, Pos.BOTTOM_RIGHT);
    StackPane.setMargin(healButton, new Insets(50, 16, 109, 16));
    StackPane.setAlignment(inventoryButton, Pos.BOTTOM_LEFT);
    StackPane.setMargin(inventoryButton, new Insets(50, 16, 18, 16));
    StackPane.setAlignment(escapeButton, Pos.BOTTOM_RIGHT);
    StackPane.setMargin(escapeButton, new Insets(50, 16, 18, 16));
    pane.getChildren().addAll(fightButton, healButton, inventoryButton, escapeButton);
  }

  /**
   * Adds the fight buttons to the MiniGamePane.
   *
   * @param pane the MiniGamePane object
   * @throws FileNotFoundException if the file specified is not found
   */
  public static void addFightObjects(MiniGamePane pane) throws FileNotFoundException {
    Button abilityOneButton = ViewObjects.newButton(
      "Insult",
      16,
      413,
      "fight_button",
      "fight_hover",
      controller::abilityOneAction
    );
    Button abilityTwoButton = ViewObjects.newButton(
      "Shame",
      411,
      413,
      "fight_button",
      "fight_hover",
      controller::abilityTwoAction
    );
    Button abilityThreeButton = ViewObjects.newButton(
      "Hit",
      16,
      504,
      "fight_button",
      "fight_hover",
      controller::abilityThreeAction
    );
    Button backButton = ViewObjects.newButton(
      "Back",
      411,
      504,
      "fight_button",
      "fight_hover",
      controller::backAction
    );
    StackPane.setAlignment(abilityOneButton, Pos.BOTTOM_LEFT);
    StackPane.setMargin(abilityOneButton, new Insets(50, 16, 109, 16));
    StackPane.setAlignment(abilityTwoButton, Pos.BOTTOM_RIGHT);
    StackPane.setMargin(abilityTwoButton, new Insets(50, 16, 109, 16));
    StackPane.setAlignment(abilityThreeButton, Pos.BOTTOM_LEFT);
    StackPane.setMargin(abilityThreeButton, new Insets(50, 16, 18, 16));
    StackPane.setAlignment(backButton, Pos.BOTTOM_RIGHT);
    StackPane.setMargin(backButton, new Insets(50, 16, 18, 16));
    pane.getChildren().addAll(abilityOneButton, abilityTwoButton, abilityThreeButton, backButton);
  }

  /**
   * Adds the heal buttons to the MiniGamePane.
   *
   * @param pane the MiniGamePane object
   * @throws FileNotFoundException if the file specified is not found
   */
  public static void addHealObjects(MiniGamePane pane) throws FileNotFoundException {
    Button healOneButton = ViewObjects.newButton(
      "Lick wound",
      16,
      413,
      "heal_button",
      "heal_hover",
      controller::healOneAction
    );
    Button healTwoButton = ViewObjects.newButton(
      "Ignore pain",
      411,
      413,
      "heal_button",
      "heal_hover",
      controller::healTwoAction
    );
    Button backButton = ViewObjects.newButton(
      "Back",
      411,
      504,
      "heal_button",
      "heal_hover",
      controller::backAction
    );
    Button emptyButton = ViewObjects.newButton(
      "",
      411,
      504,
      "heal_button",
      "heal_hover",
      controller::emptyAction
    );
    StackPane.setAlignment(healOneButton, Pos.BOTTOM_LEFT);
    StackPane.setMargin(healOneButton, new Insets(50, 16, 109, 16));
    StackPane.setAlignment(healTwoButton, Pos.BOTTOM_RIGHT);
    StackPane.setMargin(healTwoButton, new Insets(50, 16, 109, 16));
    StackPane.setAlignment(backButton, Pos.BOTTOM_LEFT);
    StackPane.setMargin(backButton, new Insets(50, 16, 18, 16));
    StackPane.setAlignment(emptyButton, Pos.BOTTOM_RIGHT);
    StackPane.setMargin(emptyButton, new Insets(50, 16, 18, 16));
    pane.getChildren().addAll(healOneButton, healTwoButton, backButton, emptyButton);
  }

  /**
   * Adds the inventory buttons to the MiniGamePane.
   *
   * @param pane the MiniGamePane object
   * @throws FileNotFoundException if the file specified is not found
   */
  public static void addInventoryObjects(MiniGamePane pane) throws FileNotFoundException {
    String[] inventoryItems = MiniGameController.getPlayerInventoryItems();
    Button itemOneButton = ViewObjects.newButton(
      inventoryItems[0],
      16,
      413,
      "inventory_button",
      "inventory_hover",
      controller::inventoryOneAction
    );
    Button itemTwoButton = ViewObjects.newButton(
      inventoryItems[1],
      411,
      413,
      "inventory_button",
      "inventory_hover",
      controller::inventoryTwoAction
    );
    Button itemThreeButton = ViewObjects.newButton(
      inventoryItems[2],
      16,
      504,
      "inventory_button",
      "inventory_hover",
      controller::inventoryThreeAction
    );
    Button backButton = ViewObjects.newButton(
      "Back",
      411,
      504,
      "inventory_button",
      "inventory_hover",
      controller::backAction
    );
    StackPane.setAlignment(itemOneButton, Pos.BOTTOM_LEFT);
    StackPane.setMargin(itemOneButton, new Insets(50, 16, 109, 16));
    StackPane.setAlignment(itemTwoButton, Pos.BOTTOM_RIGHT);
    StackPane.setMargin(itemTwoButton, new Insets(50, 16, 109, 16));
    StackPane.setAlignment(itemThreeButton, Pos.BOTTOM_LEFT);
    StackPane.setMargin(itemThreeButton, new Insets(50, 16, 18, 16));
    StackPane.setAlignment(backButton, Pos.BOTTOM_RIGHT);
    StackPane.setMargin(backButton, new Insets(50, 16, 18, 16));
    pane.getChildren().addAll(itemOneButton, itemTwoButton, itemThreeButton, backButton);
  }

  /**
   * Adds the escape buttons to the MiniGamePane.
   *
   * @param pane the MiniGamePane object
   * @throws FileNotFoundException if the file specified is not found
   */
  public static void addEscapeObjects(MiniGamePane pane) throws FileNotFoundException {
    Button continueButton = ViewObjects.newButton(
      "ESCAPE!",
      16,
      413,
      "escape_button",
      "escape_hover",
      controller::escapeTwoAction
    );
    Button backButton = ViewObjects.newButton(
      "Go back",
      411,
      413,
      "escape_button",
      "escape_hover",
      controller::backAction
    );
    Button emptyOneButton = ViewObjects.newButton(
      "",
      16,
      504,
      "escape_button",
      "escape_hover",
      controller::emptyAction
    );
    Button emptyTwoButton = ViewObjects.newButton(
      "",
      411,
      504,
      "escape_button",
      "escape_hover",
      controller::emptyAction
    );
    StackPane.setAlignment(continueButton, Pos.BOTTOM_LEFT);
    StackPane.setMargin(continueButton, new Insets(50, 16, 109, 16));
    StackPane.setAlignment(backButton, Pos.BOTTOM_RIGHT);
    StackPane.setMargin(backButton, new Insets(50, 16, 109, 16));
    StackPane.setAlignment(emptyOneButton, Pos.BOTTOM_LEFT);
    StackPane.setMargin(emptyOneButton, new Insets(50, 16, 18, 16));
    StackPane.setAlignment(emptyTwoButton, Pos.BOTTOM_RIGHT);
    StackPane.setMargin(emptyTwoButton, new Insets(50, 16, 18, 16));
    pane.getChildren().addAll(continueButton, backButton, emptyOneButton, emptyTwoButton);
  }
}
