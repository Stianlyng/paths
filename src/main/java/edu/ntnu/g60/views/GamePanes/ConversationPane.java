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


public class ConversationPane extends StackPane{

    private static ConversationPaneController controller;
    public static Game game = ConversationPaneController.game;
    private static Passage passage;

    //change from static in passage and rename
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


    public static void addChoiceObjects(ConversationPane pane) throws FileNotFoundException, MalformedURLException{
        Button choiceOneButton = ViewObjects.newButton(passage.getLinks().get(0).getText(), 71, 212, "talk_button", "talk_hover", controller::choiceOneAction);
        Button choiceTwoButton = ViewObjects.newButton(passage.getLinks().get(1).getText(), 318, 212, "talk_button", "talk_hover", controller::choiceTwoAction);
        StackPane.setAlignment(choiceOneButton, Pos.CENTER_LEFT);
        StackPane.setMargin(choiceOneButton, new Insets(50, 50, 50, 50)); 
        StackPane.setAlignment(choiceTwoButton, Pos.CENTER_RIGHT);
        StackPane.setMargin(choiceTwoButton, new Insets(50, 50, 50, 50)); 
        pane.getChildren().addAll(choiceOneButton, choiceTwoButton);
    }

    public static Group getConversationPaneObjects() throws FileNotFoundException{
        ImageView enemyImage = ViewObjects.newImage("characters", passage.getPlayer(), 150, 200, 150, 150, controller::conversationPaneClickedAction);
        ImageView playerImage = ViewObjects.newImage("characters", passage.getEnemy(), 700, 200, 150, 150, controller::conversationPaneClickedAction);
        ImageView backgroundImage = ViewObjects.newImage("backgrounds", passage.getBackground(), 0, 0, 1650, 1000, controller::conversationPaneClickedAction);
        ImageView coinIcon = ViewObjects.newImage("icons", "coin.png", 309-193, 136-71, 24, 24, controller::conversationPaneClickedAction);
        ImageView healthIcon = ViewObjects.newImage("icons", "heart.png", 488-193, 136-71, 24, 24, controller::conversationPaneClickedAction);
        ImageView scoreIcon = ViewObjects.newImage("icons", "star.png", 389-193, 136-71, 24, 24, controller::conversationPaneClickedAction);

        MenuButton dropDownMenu = ViewObjects.newMenuButton(controller::goToMenuAndSaveAction, controller::exitApplicationAction, "menu_button", "menu_hover", 413, 129-71, "Save and go to main menu", "Exit application");
        Text scoreText = ViewObjects.newText("" + game.getPlayer().getScore(), 18, false, 342-193, 155-71, controller::conversationPaneClickedAction);
        Text goldText = ViewObjects.newText("" + game.getPlayer().getGold(), 18, false, 422-193, 155-71, controller::conversationPaneClickedAction);
        Text healthText = ViewObjects.newText("" + game.getPlayer().getHealth(), 18, false, 520-193, 155-71, controller::conversationPaneClickedAction);
        ChoiceBox inventory = ViewObjects.newChoiceBox(ConversationPaneController.getPlayerInventoryItems(), 724, 129-71, "inventory_choicebox");

        Rectangle infoBoard = ViewObjects.newRectangle(303-193, 129-71, 293, 38, controller::conversationPaneClickedAction);
        String[] textline = ConversationPaneController.getTextLines();
        Text conversationTextLineOne = ViewObjects.newText(textline[0], 24, false, 333-193, 520-71, controller::conversationPaneClickedAction);
        Text conversationTextLineTwo = ViewObjects.newText(textline[1], 24, false, 333-193, 555-71, controller::conversationPaneClickedAction);
        Text conversationTextLineThree = ViewObjects.newText(textline[2], 24, false, 333-193, 590-71, controller::conversationPaneClickedAction);
        Text conversationTextLineFour = ViewObjects.newText(textline[3], 24, false, 333-193, 425-71, controller::conversationPaneClickedAction);

        Group root = new Group(backgroundImage, infoBoard, enemyImage, playerImage, coinIcon,
        healthIcon, scoreIcon, scoreText, goldText, healthText,
        conversationTextLineOne, conversationTextLineTwo, conversationTextLineThree, conversationTextLineFour, dropDownMenu, inventory);

        if(ConversationPaneController.getTypeInCurrentConversationPane().equals("{N}")){
            ImageView neutralBubble = ViewObjects.newImage("animations", "neutralbubble.png", 327-193, 440-71, 793, 211+511, controller::conversationPaneClickedAction);
            root.getChildren().add(neutralBubble);
            neutralBubble.toBack();
        } else if(ConversationPaneController.getTypeInCurrentConversationPane().equals("{E}")){
            ImageView rightBubble = ViewObjects.newImage("animations", "righttalkingbubble.png", 327-193, 440-71,793, 211+511, controller::conversationPaneClickedAction);
            root.getChildren().add(rightBubble);
            rightBubble.toBack();
            playerImage.setFitWidth(175);
            playerImage.setFitHeight(175);
            enemyImage.setFitWidth(125);
            enemyImage.setFitHeight(125);
        } else if(ConversationPaneController.getTypeInCurrentConversationPane().equals("{P}")){
            ImageView leftBubble = ViewObjects.newImage("animations", "lefttalkingbubble.png", 327-193, 440-71, 793, 211+511, controller::conversationPaneClickedAction);
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
