package edu.ntnu.g60.Frontend;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

import edu.ntnu.g60.Passage;
import edu.ntnu.g60.Player;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ApplicationFront extends Application {
    
    private Stage stage;
    Player player = new Player("Bjørn", 54, 0, 0); //TODO make variable
    int amountoflines;
    int lineNumber;

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        this.stage = stage;
        stage.setTitle("Half life 3");
        stage.setScene(openingScene());
        stage.setResizable(false);
        stage.show();
    }

    public Scene openingScene() throws FileNotFoundException{
        Button continueButton = ApplicationObjects.newButton("Continue", 514-193, 314-71, "black", "#32a8a2", 158, 51, 24);
        continueButton.setOnAction(e -> {
            try {
                stage.setScene(continueScene());
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        
        Button newGameButton = ApplicationObjects.newButton("New game", 514-193, 396-71, "black", "#32a8a2", 158, 51, 24);
        newGameButton.setOnAction(e -> {
            try {
                stage.setScene(newGameScene());
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        ImageView background = ApplicationObjects.newImage("backgrounds", "background2.jpg", 100 ,-195 ,1003 ,606);
        background.setRotate(270);
        Group root = new Group(background, continueButton, newGameButton);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;

    }

    public Scene continueScene() throws FileNotFoundException {
        //TODO: set name of buttons to variabel names based on savefile names
        Button save1Button = ApplicationObjects.newButton("Save 1", 514-193, 278-71, "black", "#32a8a2", 158, 51, 24);
        save1Button.setOnAction(e -> {
            lvlScene(0); //hent lvl fra save1
        });
        Button save2Button = ApplicationObjects.newButton("Save 2", 514-193, 345-71, "black", "#32a8a2", 158, 51, 24);
        save2Button.setOnAction(e -> {
            lvlScene(0); //hent lvl fra save2
        });
        Button save3Button = ApplicationObjects.newButton("Save 3", 514-193, 412-71, "black", "#32a8a2", 158, 51, 24);
        save3Button.setOnAction(e -> {
            lvlScene(0); //hent lvl fra save3
        });

        Button backButton = ApplicationObjects.newButton("Back", 903-193, 595-71, "black", "#32a8a2", 74, 35, 15);
        backButton.setOnAction(e -> {
            try {
                stage.setScene(openingScene());
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        ImageView background = ApplicationObjects.newImage("backgrounds", "background2.jpg", 100 ,-195 ,1003 ,606);
        background.setRotate(270);

        Group root = new Group(background, save1Button, save2Button, save3Button, backButton);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;

    }

    public Scene loadingScene() throws FileNotFoundException{
        Text loadingText = ApplicationObjects.newText("Loading...", 40, false, 508-193, 349-71);
        ImageView background = ApplicationObjects.newImage("backgrounds", "background2.jpg", 100 ,-195 ,1003 ,606);
        background.setRotate(270);

        Group root = new Group(background, loadingText);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }

    public Scene deathScene() throws FileNotFoundException{
        Text deathText = ApplicationObjects.newText("YOU DIED", 40, false, 508-193, 349-71);

        ImageView background = ApplicationObjects.newImage("backgrounds", "background2.jpg", 100 ,-195 ,1003 ,606);
        background.setRotate(270);
        Group root = new Group(background, deathText);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }

    public Scene firstScene(int lvl) throws FileNotFoundException{
        Text lvlText = ApplicationObjects.newText("LVL " + lvl, 40, false, 541-193, 349-71);
        ImageView background = ApplicationObjects.newImage("backgrounds", "background2.jpg", 100 ,-195 ,1003 ,606);
        background.setRotate(270);

        Group root = new Group(background, lvlText);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }

    public Scene newGameScene() throws FileNotFoundException{
        Button startButton = ApplicationObjects.newButton("Start", 514-193, 370-71, "black", "#32a8a2", 158, 51, 24);
        startButton.setOnAction(e -> {
            //add overwrite om det er for mange saves
            //lag ny save file
            lvlScene(0); //hent lvl fra ny save file
        });    
            //TODO: add samme som over bare til riktig scene
        

        Button backButton = ApplicationObjects.newButton("Back", 903-193, 595-71, "black", "#32a8a2", 74, 35, 15);
        backButton.setOnAction(e -> {
            try {
                stage.setScene(openingScene());
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        TextField saveNameTextField = ApplicationObjects.newTextField("Savename..", 514-193, 327-71, "black", "#32a8a2", 158, 27, 13);
        ImageView background = ApplicationObjects.newImage("backgrounds", "background2.jpg", 100 ,-195 ,1003 ,606);
        background.setRotate(270);

        Group root = new Group(background, backButton, startButton, saveNameTextField);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }

    public Scene newFightScene(int lvl) throws FileNotFoundException{
        //TODO: add images like done on talking scene
        
        Button fightButton = ApplicationObjects.newButton("Fight", 209-193, 484-71, "black", "#e87dd1", 373, 55, 40);
        Button healButton = ApplicationObjects.newButton("Heal", 604-193, 484-71, "black", "#83dea4", 373, 55, 40);
        Button inventoryButton = ApplicationObjects.newButton("Inventory", 209-193, 575-71, "black", "#e0a84c", 373, 55, 40);
        Button escapeButton = ApplicationObjects.newButton("Escape", 604-193, 575-71, "black", "#4c8ce0", 373, 55, 40);

        ImageView coinIcon = ApplicationObjects.newImage("icons", "coin.png", 209-193, 86-71, 24, 24);
        ImageView scoreIcon = ApplicationObjects.newImage("icons", "star.png", 289-193, 86-71, 24, 24);

        int score = player.getScore();
        int gold = player.getGold();
        double playerHealth = player.getHealth()/100;
        double enemyHealth = 0; //make health variable
        Group root = new Group(ApplicationObjects.newRectangle(203-193, 79-71, 200, 38),
        ApplicationObjects.newHealthBar(259-193, 306-71, playerHealth), 
        ApplicationObjects.newHealthBar(789-193, 137-71, enemyHealth),
        ApplicationObjects.newText("" + score, 18, false, 242-193, 105-71),
        ApplicationObjects.newText("" + gold, 18, false, 322-193, 105-71),
        fightButton, healButton, inventoryButton, escapeButton,
        scoreIcon, coinIcon);
        
        //TODO:add functionality and variables to buttons

        fightButton.setOnAction(e -> {
            root.getChildren().removeAll(fightButton, healButton, inventoryButton, escapeButton);
            Button ability1Button = ApplicationObjects.newButton("Ability 1", 209-193, 484-71, "black", "#e87dd1", 373, 55, 40);
            Button ability2Button = ApplicationObjects.newButton("Ability 2", 604-193, 484-71, "black", "#e87dd1", 373, 55, 40);
            Button ability3Button = ApplicationObjects.newButton("Ability 3", 209-193, 575-71, "black", "#e87dd1", 373, 55, 40);
            Button backButton = ApplicationObjects.newButton("Back", 604-193, 575-71, "black", "#e87dd1", 373, 55, 40);
            backButton.setOnAction(b -> {
                root.getChildren().removeAll(ability1Button, ability2Button, ability3Button, backButton);
                root.getChildren().addAll(fightButton, healButton, inventoryButton, escapeButton);
            });
            root.getChildren().addAll(ability1Button, ability2Button, ability3Button, backButton);
        });

        healButton.setOnAction(e -> {
            root.getChildren().removeAll(fightButton, healButton, inventoryButton, escapeButton);
            Button heal1Button = ApplicationObjects.newButton("Heal 1", 209-193, 484-71, "black", "#83dea4", 373, 55, 40);
            Button heal2Button = ApplicationObjects.newButton("Heal 2", 604-193, 484-71, "black", "#83dea4", 373, 55, 40);
            Button backButton = ApplicationObjects.newButton("Back", 604-193, 575-71, "black", "#83dea4", 373, 55, 40);
            backButton.setOnAction(b -> {
                root.getChildren().removeAll(heal1Button, heal2Button, backButton);
                root.getChildren().addAll(fightButton, healButton, inventoryButton, escapeButton);
            });
            root.getChildren().addAll(heal1Button, heal2Button, backButton);
        });

        inventoryButton.setOnAction(e -> {
            root.getChildren().removeAll(fightButton, healButton, inventoryButton, escapeButton);
            
            List<String> inventoryList = player.getInventory(); //TODO: add til de under
            Button item1Button = ApplicationObjects.newButton("Item 1", 209-193, 484-71, "black", "#e0a84c", 373, 55, 40);
            Button item2Button = ApplicationObjects.newButton("Item 2", 604-193, 484-71, "black", "#e0a84c", 373, 55, 40);
            Button item3Button = ApplicationObjects.newButton("Item 3", 209-193, 575-71, "black", "#e0a84c", 373, 55, 40);
            Button backButton = ApplicationObjects.newButton("Back", 604-193, 575-71, "black", "#e0a84c", 373, 55, 40);
            backButton.setOnAction(b -> {
                root.getChildren().removeAll(item1Button, item2Button, item3Button, backButton);
                root.getChildren().addAll(fightButton, healButton, inventoryButton, escapeButton);
            });
            root.getChildren().addAll(item1Button, item2Button, item3Button, backButton);
        });

        escapeButton.setOnAction(e -> {
            root.getChildren().removeAll(fightButton, healButton, inventoryButton, escapeButton);
            Button continueButton = ApplicationObjects.newButton("ESCAPE!", 209-193, 484-71, "black", "#4c8ce0", 373, 55, 40);
            Button backButton = ApplicationObjects.newButton("Go back", 604-193, 484-71, "black", "#4c8ce0", 373, 55, 40);
            backButton.setOnAction(b -> {
                root.getChildren().removeAll(continueButton, backButton);
                root.getChildren().addAll(fightButton, healButton, inventoryButton, escapeButton);
            });
            root.getChildren().addAll(continueButton, backButton);
        });


        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }
    
    public Scene newTalkingScene(String type, int lvl) throws FileNotFoundException{
        //Group sceneInfo = Passage.getSceneInfo(gameSavelvl); //noe sånt
        //ImageView enemyImage = sceneInfo.enemy
        //ImageView background = sceneInfo.background
        //ImageView playerImage.. hentes direkte og er altid samme
        //Inventory Item... hentes fra inventory som tilsier at inventory må ha bilde param
        
        String typeNext = Passage.getTypeOfTextAtLineNumber(lineNumber);
        Supplier<Boolean> moreLinesLeft = () -> lineNumber < amountoflines;

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
            Button choice1 = ApplicationObjects.newButton("choice 1", 264, 283, "black", "white", 306, 85, 22);
            Button choice2 = ApplicationObjects.newButton("choice 2", 613, 283, "black", "white", 306, 85, 22);
            root.getChildren().addAll(neutralBubble, choice1, choice2);
            neutralBubble.toBack();
            //TODO: add diffrent choices under
            choice1.setOnAction(e -> {
                if(moreLinesLeft.get()){
                    try {
                        stage.setScene(newTalkingScene(typeNext, lvl));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } 
                } else if (Passage.passageHasFightScene() == true){ //lag metode som sier om det eksisterer en fight scene på dette levelet eller ikke
                    try {
                        stage.setScene(newFightScene(lvl));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    lvlScene(lvl + 1);
                }
            });

            choice2.setOnAction(e -> {
                if(moreLinesLeft.get()){
                    try {
                        stage.setScene(newTalkingScene(typeNext, lvl));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                } else if (Passage.passageHasFightScene() == true){
                    try {
                        stage.setScene(newFightScene(lvl));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    lvlScene(lvl + 1);
                }
            });
        }


        //background.toBack
        //root.getChildren().addAll(enemyImage, playerImage);

        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        

        scene.setOnMouseClicked(e -> {
            if(moreLinesLeft.get()){
                try {
                    stage.setScene(newTalkingScene(typeNext, lvl));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } 
            } else if (Passage.passageHasFightScene() == true){
                try {
                    stage.setScene(newFightScene(lvl));
                } catch (FileNotFoundException e1){
                    e1.printStackTrace();
                }  
                } else {
                lvlScene(lvl + 1);
            }});

        return scene;
    }

    public void lvlScene(int lvl){
        try {
            stage.setScene(loadingScene());
        } catch (FileNotFoundException e1) {
            
            e1.printStackTrace();
        }
        
        ApplicationObjects.delay(2000, () -> {
            try {
                stage.setScene(firstScene(lvl));
            } catch (FileNotFoundException e1) {
                
                e1.printStackTrace();
            }
        });

        lineNumber = 1;
        String type = Passage.getTypeOfTextAtLineNumber(lineNumber);
        amountoflines = Passage.getAmountOfTextLines();

        ApplicationObjects.delay(2000, () -> {
            try {
                stage.setScene(newTalkingScene(type, lvl));
            } catch (FileNotFoundException e1) {
                
                e1.printStackTrace();
            }
        });
    }

    public static void main(String[] args){launch();}
}
