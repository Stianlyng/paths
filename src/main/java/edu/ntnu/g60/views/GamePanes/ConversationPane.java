package edu.ntnu.g60.views.GamePanes;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import edu.ntnu.g60.controllers.ConversationPaneController;
import edu.ntnu.g60.controllers.GameController;
import edu.ntnu.g60.controllers.SoundController;
import edu.ntnu.g60.views.ViewObjects;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class ConversationPane extends StackPane{

    private static ConversationPaneController controller;

    public ConversationPane() throws FileNotFoundException, MalformedURLException{
        ConversationPane.controller = new ConversationPaneController();
        SoundController.playSound("mumble");
        getChildren().addAll(getConversationPaneObjects());
    }


    public static void addChoiceObjects(ConversationPane pane) throws FileNotFoundException, MalformedURLException{
        Button choiceOne = ViewObjects.newButton(GameController.getCurrentPassage().getLinks().get(0).getText(), 71, 212, "talk_button", controller::choiceOneAction);
        Button choiceTwo = ViewObjects.newButton(GameController.getCurrentPassage().getLinks().get(1).getText(), 71, 222, "talk_button", controller::choiceTwoAction);
        pane.getChildren().addAll(choiceOne, choiceTwo);
    }

    public static Group getConversationPaneObjects() throws FileNotFoundException{
        ImageView enemyImage = ViewObjects.newImage("characters", GameController.getCurrentPassage().getPlayer(), 50, 150, 150, 150, controller::sceneClickedAction);
        ImageView playerImage = ViewObjects.newImage("characters", GameController.getCurrentPassage().getEnemy(), 600, 150, 150, 150, controller::sceneClickedAction);
        ImageView backgroundImage = ViewObjects.newImage("backgrounds", GameController.getCurrentPassage().getBackground(), 0, 0, 1650, 1000, controller::sceneClickedAction);
        ImageView coinIcon = ViewObjects.newImage("icons", "coin.png", 209-193, 86-71, 24, 24, controller::sceneClickedAction);
        ImageView healthIcon = ViewObjects.newImage("icons", "heart.png", 388-193, 86-71, 24, 24, controller::sceneClickedAction);
        ImageView scoreIcon = ViewObjects.newImage("icons", "star.png", 289-193, 86-71, 24, 24, controller::sceneClickedAction);


        Text scoreText = ViewObjects.newText("" + GameController.getCurrentGame().getPlayer().getScore(), 18, false, 242-193, 105-71, controller::sceneClickedAction);
        Text goldText = ViewObjects.newText("" + GameController.getCurrentGame().getPlayer().getGold(), 18, false, 322-193, 105-71, controller::sceneClickedAction);
        Text healthText = ViewObjects.newText("" + GameController.getCurrentGame().getPlayer().getHealth(), 18, false, 420-193, 105-71, controller::sceneClickedAction);

        Rectangle infoBoard = ViewObjects.newRectangle(203-193, 79-71, 293, 38, controller::sceneClickedAction);
        String[] textline = ConversationPaneController.getTextLines();
        Text textLineOne = ViewObjects.newText(textline[0], 30, false, 233-193, 470-71, controller::sceneClickedAction);
        Text textLineTwo = ViewObjects.newText(textline[1], 30, false, 233-193, 505-71, controller::sceneClickedAction);
        Text textLineThree = ViewObjects.newText(textline[2], 30, false, 233-193, 540-71, controller::sceneClickedAction);
        Text textLineFour = ViewObjects.newText(textline[3], 30, false, 233-193, 575-71, controller::sceneClickedAction);

        Group root = new Group(backgroundImage, enemyImage, playerImage, coinIcon,
        healthIcon, scoreIcon, scoreText, goldText, healthText,
        infoBoard, textLineOne, textLineTwo, textLineThree, textLineFour);

        if(ConversationPaneController.getType().equals("{N}")){
            ImageView neutralBubble = ViewObjects.newImage("animations", "neutralbubble.png", 227-193, 390-71, 793, 211+511, controller::sceneClickedAction);
            root.getChildren().add(neutralBubble);
            neutralBubble.toBack();
        } else if(ConversationPaneController.getType().equals("{E}")){
            ImageView rightBubble = ViewObjects.newImage("animations", "righttalkingbubble.png", 227-193, 390-71,793, 211+511, controller::sceneClickedAction);
            root.getChildren().add(rightBubble);
            rightBubble.toBack();
            playerImage.setFitWidth(175);
            playerImage.setFitHeight(175);
            enemyImage.setFitWidth(125);
            enemyImage.setFitHeight(125);
        } else if(ConversationPaneController.getType().equals("{P}")){
            ImageView leftBubble = ViewObjects.newImage("animations", "lefttalkingbubble.png", 227-193, 390-71, 793, 211+511, controller::sceneClickedAction);
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
