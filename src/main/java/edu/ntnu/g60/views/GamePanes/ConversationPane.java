package edu.ntnu.g60.views.GamePanes;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import edu.ntnu.g60.controllers.ConversationPaneController;
import edu.ntnu.g60.controllers.SoundController;
import edu.ntnu.g60.models.game.Game;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.views.ViewObjects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * The ConversationPane class is responsible for showing the diffrent conversation parts
 * of a passage.
 * The pane has functions to change depending on the type of conversation.
 * @author olav sie
 */
public class ConversationPane extends StackPane{

    private static ConversationPaneController controller;
    public static Game game = ConversationPaneController.game;
    private static Passage passage;

    //TODO: change from static in passage and rename
    /**
     * Constructs a new ConversationPane object.
     * @throws FileNotFoundException if the file specified is not found.
    */
    public ConversationPane(Passage npassage) throws FileNotFoundException, MalformedURLException{
        passage = npassage;
        ConversationPane.controller = new ConversationPaneController(npassage);
        try{
            SoundController.playSound("mumble");
        } catch (Exception e1){
            e1.printStackTrace();
        }

        getChildren().addAll(getConversationPaneObjects());
    }

    /**
     * Adds two choice buttons to the pane
     * 
     * @param pane the current showing conversationpane
     * @throws FileNotFoundException if the specified text file is not found.
     */
    public static void addChoiceObjects(ConversationPane pane) throws FileNotFoundException{
        Button choiceOneButton = ViewObjects.newButton(passage.getLinks().get(0).getText(), 71, 212, "talk_button", "talk_hover", controller::choiceOneAction);
        Button choiceTwoButton = ViewObjects.newButton(passage.getLinks().get(1).getText(), 318, 212, "talk_button", "talk_hover", controller::choiceTwoAction);
        StackPane.setAlignment(choiceOneButton, Pos.CENTER_LEFT);
        StackPane.setMargin(choiceOneButton, new Insets(50, 50, 50, 50)); 
        StackPane.setAlignment(choiceTwoButton, Pos.CENTER_RIGHT);
        StackPane.setMargin(choiceTwoButton, new Insets(50, 50, 50, 50)); 
        pane.getChildren().addAll(choiceOneButton, choiceTwoButton);
    }

    /**
     * Adds default objects to the ConversationPane.
     * 
     * @return objects to be added to the pane
     * @throws FileNotFoundException if the specified text file is not found.
     */
    public static Group getConversationPaneObjects() throws FileNotFoundException{
        ImageView enemyImage = ViewObjects.newImage("characters", passage.getPlayerImage(), 150, 200, 150, 150, controller::conversationPaneClickedAction);
        ImageView playerImage = ViewObjects.newImage("characters", passage.getEnemyImage(), 700, 200, 150, 150, controller::conversationPaneClickedAction);
        ImageView backgroundImage = ViewObjects.newImage("backgrounds", passage.getBackgroundImage(), 0, 0, 1650, 1000, controller::conversationPaneClickedAction);
        ImageView coinIcon = ViewObjects.newImage("icons", "coin.png", 116, 65, 24, 24, controller::conversationPaneClickedAction);
        ImageView healthIcon = ViewObjects.newImage("icons", "heart.png", 295, 65, 24, 24, controller::conversationPaneClickedAction);
        ImageView scoreIcon = ViewObjects.newImage("icons", "star.png", 196, 65, 24, 24, controller::conversationPaneClickedAction);

        MenuButton dropDownMenu = ViewObjects.newMenuButton(controller::goToMenuAndSaveAction, controller::exitApplicationAction, "menu_button", "menu_hover", 413, 58, "Save and go to main menu", "Exit application");
        Text scoreText = ViewObjects.newText("" + game.getPlayer().getScore(), 18, false, 229, 84, controller::conversationPaneClickedAction);
        Text goldText = ViewObjects.newText("" + game.getPlayer().getGold(), 18, false, 149, 84, controller::conversationPaneClickedAction);
        Text healthText = ViewObjects.newText("" + game.getPlayer().getHealth(), 18, false, 327, 84, controller::conversationPaneClickedAction);
        ChoiceBox inventory = ViewObjects.newChoiceBox(ConversationPaneController.getPlayerInventoryItems(), 724, 58, "inventory_choicebox");

        Rectangle infoBoard = ViewObjects.newRectangle(110, 58, 293, 38, controller::conversationPaneClickedAction);
        String[] textline = ConversationPaneController.getTextLines();
        Text conversationTextLineOne = ViewObjects.newText(textline[0], 24, false, 140, 449, controller::conversationPaneClickedAction);
        Text conversationTextLineTwo = ViewObjects.newText(textline[1], 24, false, 140, 484, controller::conversationPaneClickedAction);
        Text conversationTextLineThree = ViewObjects.newText(textline[2], 24, false, 140, 519, controller::conversationPaneClickedAction);
        Text conversationTextLineFour = ViewObjects.newText(textline[3], 24, false, 140, 354, controller::conversationPaneClickedAction);

        Group root = new Group(backgroundImage, infoBoard, enemyImage, playerImage, coinIcon,
        healthIcon, scoreIcon, scoreText, goldText, healthText,
        conversationTextLineOne, conversationTextLineTwo, conversationTextLineThree, conversationTextLineFour, dropDownMenu, inventory);

        if(ConversationPaneController.getTypeInCurrentConversationPane() == null || ConversationPaneController.getTypeInCurrentConversationPane().equals("{N}")){
            ImageView neutralBubble = ViewObjects.newImage("animations", "neutralbubble.png", 134, 369, 793, 722, controller::conversationPaneClickedAction);
            root.getChildren().add(neutralBubble);
            neutralBubble.toBack();
        } else if(ConversationPaneController.getTypeInCurrentConversationPane().equals("{E}")){
            ImageView rightBubble = ViewObjects.newImage("animations", "righttalkingbubble.png", 134, 369,793, 722, controller::conversationPaneClickedAction);
            root.getChildren().add(rightBubble);
            rightBubble.toBack();
            playerImage.setFitWidth(175);
            playerImage.setFitHeight(175);
            enemyImage.setFitWidth(125);
            enemyImage.setFitHeight(125);
        } else if(ConversationPaneController.getTypeInCurrentConversationPane().equals("{P}")){
            ImageView leftBubble = ViewObjects.newImage("animations", "lefttalkingbubble.png", 134, 369, 793, 722, controller::conversationPaneClickedAction);
            root.getChildren().add(leftBubble);
            leftBubble.toBack();
            playerImage.setFitWidth(125);
            playerImage.setFitHeight(125);
            enemyImage.setFitWidth(175);
            enemyImage.setFitHeight(175);
        } 
        backgroundImage.toBack();
        return root;
    }
}
