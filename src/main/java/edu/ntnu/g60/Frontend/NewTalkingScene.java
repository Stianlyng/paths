package edu.ntnu.g60.frontend;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.function.Supplier;
import edu.ntnu.g60.Passage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class NewTalkingScene {
    static Stage stage = ApplicationFront.getStage();

    public static Scene scene(String type, int lvl) throws FileNotFoundException, MalformedURLException{
        //Group sceneInfo = Passage.getSceneInfo(gameSavelvl); //noe sånt
        //ImageView enemyImage = sceneInfo.enemy
        //ImageView background = sceneInfo.background
        //ImageView playerImage.. hentes direkte og er altid samme
        //Inventory Item... hentes fra inventory som tilsier at inventory må ha bilde param
        MediaPlayer mumble = ApplicationObjects.newSound("mumble");
        String typeNext = Passage.getTypeOfTextAtLineNumber(ApplicationFront.getLineNumber());
        Supplier<Boolean> moreLinesLeft = () -> ApplicationFront.getLineNumber() < ApplicationFront.getAmountOfLines() + 1;
        ApplicationFront.setLineNumber(ApplicationFront.getLineNumber() + 1);

        //hent tekst fra passage
        Text textLineOne = ApplicationObjects.newText("Test tekst", 30, false, 233-193, 470-71);
        Text textLineTwo = ApplicationObjects.newText("Mere av det", 30, false, 233-193, 505-71);
        Text textLineThree = ApplicationObjects.newText("Enda mere av det", 30, false, 233-193, 540-71);
        Text textLineFour = ApplicationObjects.newText("", 30, false, 233-193, 575-71);
        
        ImageView coinIcon = ApplicationObjects.newImage("icons", "coin.png", 209-193, 86-71, 24, 24);
        ImageView healthIcon = ApplicationObjects.newImage("icons", "heart.png", 388-193, 86-71, 24, 24);
        ImageView scoreIcon = ApplicationObjects.newImage("icons", "star.png", 289-193, 86-71, 24, 24);

        String score = "1234";
        String gold = "4321";
        String health = "70%";
        Group root = new Group(ApplicationObjects.newRectangle(203-193, 79-71, 293, 38),
        ApplicationObjects.newText(score, 18, false, 242-193, 105-71),
        ApplicationObjects.newText(gold, 18, false, 322-193, 105-71),
        ApplicationObjects.newText(health, 18, false, 420-193, 105-71),
        textLineOne, textLineTwo, textLineThree, textLineFour,
        coinIcon, healthIcon, scoreIcon);


        int playerWidth = 150;
        int playerHeight = 150;
        int enemyWidth = 150;
        int enemyHeight = 150;
        
        if(type == "{N}"){
            ImageView neutralBubble = ApplicationObjects.newImage("animations", "neutralbubble.png", 227-193, 390-71, 793, 211+511);
            root.getChildren().add(neutralBubble);
            neutralBubble.toBack();
        } else if(type == "{E}"){
            ImageView rightBubble = ApplicationObjects.newImage("animations", "righttalkingbubble.png", 227-193, 390-71,793, 211+511);
            root.getChildren().add(rightBubble);
            rightBubble.toBack();
            playerWidth = 125;
            playerHeight = 125;
            enemyWidth = 175;
            enemyHeight = 175;
        } else if(type == "{P}"){
            ImageView leftBubble = ApplicationObjects.newImage("animations", "lefttalkingbubble.png", 227-193, 390-71, 793, 211+511);
            root.getChildren().add(leftBubble);
            leftBubble.toBack();
            playerWidth = 175;
            playerHeight = 175;
            enemyWidth = 125;
            enemyHeight = 125;
        } else if(type == "{C}"){
            ImageView neutralBubble = ApplicationObjects.newImage("animations", "neutralbubble.png", 227-193, 390-71, 793, 211+511);
            Button choice1 = ApplicationObjects.newButton("choice 1", 264, 283, "talk_button");
            Button choice2 = ApplicationObjects.newButton("choice 2", 613, 283, "talk_button");
            root.getChildren().addAll(neutralBubble, choice1, choice2);
            neutralBubble.toBack();
            //TODO: add diffrent choices under
            choice1.setOnAction(e -> {
                if(moreLinesLeft.get()){
                    try {
                        stage.setScene(scene(typeNext, lvl));
                        mumble.play();
                    } catch (FileNotFoundException | MalformedURLException e1) {
                        e1.printStackTrace();
                    } 
                } else if (Passage.passageHasFightScene() == true){ //lag metode som sier om det eksisterer en fight scene på dette levelet eller ikke
                    try {
                        stage.setScene(NewFightScene.scene(lvl));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        LvlScene.scene(lvl + 1);
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    }
                }
            });

            choice2.setOnAction(e -> {
                if(moreLinesLeft.get()){
                    try {
                        stage.setScene(scene(typeNext, lvl));
                        mumble.play();
                    } catch (FileNotFoundException | MalformedURLException e1) {
                        e1.printStackTrace();
                    }
                } else if (Passage.passageHasFightScene() == true){
                    try {
                        stage.setScene(NewFightScene.scene(lvl));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        LvlScene.scene(lvl + 1);
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }


        //background.toBack
        //root.getChildren().addAll(enemyImage, playerImage);
        root.getStylesheets().add("StyleSheet.css"); 
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        

        scene.setOnMouseClicked(e -> {
            if(moreLinesLeft.get()){
                try {
                    stage.setScene(scene(typeNext, lvl));
                    mumble.play();
                } catch (FileNotFoundException | MalformedURLException e1) {
                    e1.printStackTrace();
                } 
            } else if (Passage.passageHasFightScene() == true){
                try {
                    stage.setScene(NewFightScene.scene(lvl));
                } catch (FileNotFoundException e1){
                    e1.printStackTrace();
                }  
                } else {
                    try {
                        LvlScene.scene(lvl + 1);
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    }
            }});
        
        return scene;
    }

}
