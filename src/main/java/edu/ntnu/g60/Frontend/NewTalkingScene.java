package edu.ntnu.g60.frontend;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import edu.ntnu.g60.Game;
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

    public static Scene scene(String[] types, String[] passageContent, Game game) throws FileNotFoundException, MalformedURLException{
        //Group sceneInfo = Passage.getSceneInfo(gameSavelvl); //noe sånt
        //ImageView enemyImage = sceneInfo.enemy
        //ImageView background = sceneInfo.background
        //ImageView playerImage.. hentes direkte og er altid samme
        //Inventory Item... hentes fra inventory som tilsier at inventory må ha bilde param
        MediaPlayer mumble = ApplicationObjects.newSound("mumble");
        boolean moreLinesLeft = (ApplicationFront.getTextLine() + 1 == types.length) ? false : true;

        String type = types[ApplicationFront.getTextLine()];
        String line = passageContent[ApplicationFront.getTextLine()];
        
        String[] words = line.split("\\s+");
        int numGroups = (int) Math.ceil(words.length / 10.0);
        List<String[]> wordGroups = new ArrayList<>();
        for (int i = 0; i < numGroups; i++) {
            int startIndex = i * 10;
            int endIndex = Math.min(startIndex + 10, words.length);
            if (startIndex < words.length) { 
                String[] group = Arrays.copyOfRange(words, startIndex, endIndex);
                if (group.length < 10) {
                    String[] paddedGroup = new String[10];
                    Arrays.fill(paddedGroup, "");
                    System.arraycopy(group, 0, paddedGroup, 0, group.length);
                    group = paddedGroup;
                }
                wordGroups.add(group);
            } else { 
                String[] paddedGroup = new String[10];
                Arrays.fill(paddedGroup, "");
                wordGroups.add(paddedGroup);
            }
        }

        String[] result = new String[4];
        if (numGroups == 0) {
            Arrays.fill(result, "");
        } else {
            int i = 0;
            for (; i < numGroups && i < 4; i++) {
                StringBuilder sb = new StringBuilder();
                for (String word : wordGroups.get(i)) {
                    sb.append(word).append(" ");
                }
                result[i] = sb.toString().trim();
            }
            for (; i < 4; i++) {
                result[i] = "";
            }
        }


		
        

        //hent tekst fra passage
        Text textLineOne = ApplicationObjects.newText(result[0], 30, false, 233-193, 470-71);
        Text textLineTwo = ApplicationObjects.newText(result[1], 30, false, 233-193, 505-71);
        Text textLineThree = ApplicationObjects.newText(result[2], 30, false, 233-193, 540-71);
        Text textLineFour = ApplicationObjects.newText(result[3], 30, false, 233-193, 575-71);
        
        ImageView coinIcon = ApplicationObjects.newImage("icons", "coin.png", 209-193, 86-71, 24, 24);
        ImageView healthIcon = ApplicationObjects.newImage("icons", "heart.png", 388-193, 86-71, 24, 24);
        ImageView scoreIcon = ApplicationObjects.newImage("icons", "star.png", 289-193, 86-71, 24, 24);

        String score = "" + game.getPlayer().getScore();
        String gold = "" + game.getPlayer().getGold();
        String health = "" + game.getPlayer().getHealth();
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


        ApplicationFront.setTextLine(ApplicationFront.getTextLine() + 1);
        if(type.equals("{N}")){
            ImageView neutralBubble = ApplicationObjects.newImage("animations", "neutralbubble.png", 227-193, 390-71, 793, 211+511);
            root.getChildren().add(neutralBubble);
            neutralBubble.toBack();
        } else if(type.equals("{E}")){
            ImageView rightBubble = ApplicationObjects.newImage("animations", "righttalkingbubble.png", 227-193, 390-71,793, 211+511);
            root.getChildren().add(rightBubble);
            rightBubble.toBack();
            playerWidth = 125;
            playerHeight = 125;
            enemyWidth = 175;
            enemyHeight = 175;
        } else if(type.equals("{P}")){
            ImageView leftBubble = ApplicationObjects.newImage("animations", "lefttalkingbubble.png", 227-193, 390-71, 793, 211+511);
            root.getChildren().add(leftBubble);
            leftBubble.toBack();
            playerWidth = 175;
            playerHeight = 175;
            enemyWidth = 125;
            enemyHeight = 125;
        } else if(type.equals("{C}")){
            ImageView neutralBubble = ApplicationObjects.newImage("animations", "neutralbubble.png", 227-193, 390-71, 793, 211+511);
            Button choice1 = ApplicationObjects.newButton("choice 1", 264, 283, "talk_button");
            Button choice2 = ApplicationObjects.newButton("choice 2", 613, 283, "talk_button");
            root.getChildren().addAll(neutralBubble, choice1, choice2);
            neutralBubble.toBack();
            //TODO: add diffrent choices under
            choice1.setOnAction(e -> {
                if(moreLinesLeft){
                    try {
                        stage.setScene(scene(types, passageContent, game));
                        mumble.play();
                    } catch (FileNotFoundException | MalformedURLException e1) {
                        e1.printStackTrace();
                    } 
                } else if (moreLinesLeft){ //Passage.passageHasFightScene() == true
                    try {
                        stage.setScene(NewFightScene.scene(game));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        LvlScene.scene(game);
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    }
                }
            });

            choice2.setOnAction(e -> {
                if(moreLinesLeft){
                    try {
                        stage.setScene(scene(types, passageContent, game));
                        mumble.play();
                    } catch (FileNotFoundException | MalformedURLException e1) {
                        e1.printStackTrace();
                    }
                } else if (moreLinesLeft){ //Passage.passageHasFightScene() == true
                    try {
                        stage.setScene(NewFightScene.scene(game));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        LvlScene.scene(game);
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
            if(moreLinesLeft){
                try {
                    stage.setScene(scene(types, passageContent, game));
                    mumble.play();
                } catch (FileNotFoundException | MalformedURLException e1) {
                    e1.printStackTrace();
                } 
            } else if (moreLinesLeft){ //Passage.passageHasFightScene() == true
                try {
                    stage.setScene(NewFightScene.scene(game));
                } catch (FileNotFoundException e1){
                    e1.printStackTrace();
                }  
                } else {
                    try {
                        LvlScene.scene(game);
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    }
            }});
        
        return scene;
    }

}
