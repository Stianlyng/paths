package edu.ntnu.g60;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

//TODO: make sure the mouse does not start in the textfield, this makes the prompt text unreadable
//TODO: make it so the user can input the email key with spaces or without. take input and remove spaces

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ApplicationFront extends Application {
    
    private Stage stage;
    Player player; //TODO make variable

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        this.stage = stage;
        stage.setTitle("Half life 3");
        stage.setScene(newFightScene(0));
        stage.setResizable(false);
        stage.show();
    }

    public Scene openingScene() throws FileNotFoundException{
        Button continueButton = newButton("Continue", 514-193, 314-71, "black", "#32a8a2", 158, 51, 24);
        continueButton.setOnAction(e -> {
            try {
                stage.setScene(continueScene());
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        
        Button newGameButton = newButton("New game", 514-193, 396-71, "black", "#32a8a2", 158, 51, 24);
        newGameButton.setOnAction(e -> {
            try {
                stage.setScene(newGameScene());
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        ImageView background = newImage("backgrounds", "background2.jpg", 100 ,-195 ,1003 ,606);
        background.setRotate(270);
        Group root = new Group(background, continueButton, newGameButton);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;

    }

    public Scene continueScene() throws FileNotFoundException {
        //TODO: set name of buttons to variabel names based on savefile names
        Button save1Button = newButton("Save 1", 514-193, 278-71, "black", "#32a8a2", 158, 51, 24);
        save1Button.setOnAction(e -> {
            lvlScene(0); //hent lvl fra save1
        });
        Button save2Button = newButton("Save 2", 514-193, 345-71, "black", "#32a8a2", 158, 51, 24);
        save2Button.setOnAction(e -> {
            lvlScene(0); //hent lvl fra save2
        });
        Button save3Button = newButton("Save 3", 514-193, 412-71, "black", "#32a8a2", 158, 51, 24);
        save3Button.setOnAction(e -> {
            lvlScene(0); //hent lvl fra save3
        });

        Button backButton = newButton("Back", 903-193, 595-71, "black", "#32a8a2", 74, 35, 15);
        backButton.setOnAction(e -> {
            try {
                stage.setScene(openingScene());
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        ImageView background = newImage("backgrounds", "background2.jpg", 100 ,-195 ,1003 ,606);
        background.setRotate(270);

        Group root = new Group(background, save1Button, save2Button, save3Button, backButton);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;

    }

    public Scene loadingScene() throws FileNotFoundException{
        Text loadingText = newText("Loading...", 40, false, 508-193, 349-71);
        ImageView background = newImage("backgrounds", "background2.jpg", 100 ,-195 ,1003 ,606);
        background.setRotate(270);

        Group root = new Group(background, loadingText);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }

    public Scene deathScene() throws FileNotFoundException{
        Text deathText = newText("YOU DIED", 40, false, 508-193, 349-71);

        ImageView background = newImage("backgrounds", "background2.jpg", 100 ,-195 ,1003 ,606);
        background.setRotate(270);
        Group root = new Group(background, deathText);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }

    public Scene firstScene(int lvl) throws FileNotFoundException{
        Text lvlText = newText("LVL " + lvl, 40, false, 541-193, 349-71);
        ImageView background = newImage("backgrounds", "background2.jpg", 100 ,-195 ,1003 ,606);
        background.setRotate(270);

        Group root = new Group(background, lvlText);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }

    public Scene newGameScene() throws FileNotFoundException{
        Button startButton = newButton("Start", 514-193, 370-71, "black", "#32a8a2", 158, 51, 24);
        startButton.setOnAction(e -> {
            //add overwrite om det er for mange saves
            //lag ny save file
            lvlScene(0); //hent lvl fra ny save file
        });    
            //TODO: add samme som over bare til riktig scene
        

        Button backButton = newButton("Back", 903-193, 595-71, "black", "#32a8a2", 74, 35, 15);
        backButton.setOnAction(e -> {
            try {
                stage.setScene(openingScene());
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        TextField saveNameTextField = newTextField("Savename..", 514-193, 327-71, "black", "#32a8a2", 158, 27, 13);
        ImageView background = newImage("backgrounds", "background2.jpg", 100 ,-195 ,1003 ,606);
        background.setRotate(270);

        Group root = new Group(background, backButton, startButton, saveNameTextField);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }

    public Scene newFightScene(int lvl) throws FileNotFoundException{
        //TODO: add images like done on talking scene
        
        Button fightButton = newButton("Fight", 209-193, 484-71, "black", "#e87dd1", 373, 55, 40);
        Button healButton = newButton("Heal", 604-193, 484-71, "black", "#83dea4", 373, 55, 40);
        Button inventoryButton = newButton("Inventory", 209-193, 575-71, "black", "#e0a84c", 373, 55, 40);
        Button escapeButton = newButton("Escape", 604-193, 575-71, "black", "#4c8ce0", 373, 55, 40);

        ImageView coinIcon = newImage("icons", "coin.png", 209-193, 86-71, 24, 24);
        ImageView scoreIcon = newImage("icons", "star.png", 289-193, 86-71, 24, 24);

        int score = player.getScore();
        int gold = player.getGold();
        double playerHealth = player.getHealth()/100;
        double enemyHealth = 0; //make health variable
        Group root = new Group(newRectangle(203-193, 79-71, 200, 38),
        newHealthBar(259-193, 306-71, playerHealth), 
        newHealthBar(789-193, 137-71, enemyHealth),
        newText("" + score, 18, false, 242-193, 105-71),
        newText("" + gold, 18, false, 322-193, 105-71),
        fightButton, healButton, inventoryButton, escapeButton,
        scoreIcon, coinIcon);
        
        //TODO:add functionality and variables to buttons

        fightButton.setOnAction(e -> {
            root.getChildren().removeAll(fightButton, healButton, inventoryButton, escapeButton);
            Button ability1Button = newButton("Ability 1", 209-193, 484-71, "black", "#e87dd1", 373, 55, 40);
            Button ability2Button = newButton("Ability 2", 604-193, 484-71, "black", "#e87dd1", 373, 55, 40);
            Button ability3Button = newButton("Ability 3", 209-193, 575-71, "black", "#e87dd1", 373, 55, 40);
            Button backButton = newButton("Back", 604-193, 575-71, "black", "#e87dd1", 373, 55, 40);
            backButton.setOnAction(b -> {
                root.getChildren().removeAll(ability1Button, ability2Button, ability3Button, backButton);
                root.getChildren().addAll(fightButton, healButton, inventoryButton, escapeButton);
            });
            root.getChildren().addAll(ability1Button, ability2Button, ability3Button, backButton);
        });

        healButton.setOnAction(e -> {
            root.getChildren().removeAll(fightButton, healButton, inventoryButton, escapeButton);
            Button heal1Button = newButton("Heal 1", 209-193, 484-71, "black", "#83dea4", 373, 55, 40);
            Button heal2Button = newButton("Heal 2", 604-193, 484-71, "black", "#83dea4", 373, 55, 40);
            Button backButton = newButton("Back", 604-193, 575-71, "black", "#83dea4", 373, 55, 40);
            backButton.setOnAction(b -> {
                root.getChildren().removeAll(heal1Button, heal2Button, backButton);
                root.getChildren().addAll(fightButton, healButton, inventoryButton, escapeButton);
            });
            root.getChildren().addAll(heal1Button, heal2Button, backButton);
        });

        inventoryButton.setOnAction(e -> {
            root.getChildren().removeAll(fightButton, healButton, inventoryButton, escapeButton);
            
            List<String> list = player.getInventory(); //TODO: add til de under
            Button item1Button = newButton("Item 1", 209-193, 484-71, "black", "#e0a84c", 373, 55, 40);
            Button item2Button = newButton("Item 2", 604-193, 484-71, "black", "#e0a84c", 373, 55, 40);
            Button item3Button = newButton("Item 3", 209-193, 575-71, "black", "#e0a84c", 373, 55, 40);
            Button backButton = newButton("Back", 604-193, 575-71, "black", "#e0a84c", 373, 55, 40);
            backButton.setOnAction(b -> {
                root.getChildren().removeAll(item1Button, item2Button, item3Button, backButton);
                root.getChildren().addAll(fightButton, healButton, inventoryButton, escapeButton);
            });
            root.getChildren().addAll(item1Button, item2Button, item3Button, backButton);
        });

        escapeButton.setOnAction(e -> {
            root.getChildren().removeAll(fightButton, healButton, inventoryButton, escapeButton);
            Button continueButton = newButton("ESCAPE!", 209-193, 484-71, "black", "#4c8ce0", 373, 55, 40);
            Button backButton = newButton("Go back", 604-193, 484-71, "black", "#4c8ce0", 373, 55, 40);
            backButton.setOnAction(b -> {
                root.getChildren().removeAll(continueButton, backButton);
                root.getChildren().addAll(fightButton, healButton, inventoryButton, escapeButton);
            });
            root.getChildren().addAll(continueButton, backButton);
        });


        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }
    
    public Scene newTalkingScene(String type) throws FileNotFoundException{
        //Group sceneInfo = Passage.getSceneInfo(gameSavelvl); //noe sånt
        //ImageView enemyImage = sceneInfo.enemy
        //ImageView background = sceneInfo.background
        //ImageView playerImage.. hentes direkte og er altid samme
        //Inventory Item... hentes fra inventory som tilsier at inventory må ha bilde param

        Text textLineOne = newText("Test tekst", 30, false, 233-193, 470-71);
        Text textLineTwo = newText("Mere av det", 30, false, 233-193, 505-71);
        Text textLineThree = newText("Enda mere av det", 30, false, 233-193, 540-71);
        Text textLineFour = newText("", 30, false, 233-193, 575-71);
        
        ImageView coinIcon = newImage("icons", "coin.png", 209-193, 86-71, 24, 24);
        ImageView healthIcon = newImage("icons", "heart.png", 388-193, 86-71, 24, 24);
        ImageView scoreIcon = newImage("icons", "star.png", 289-193, 86-71, 24, 24);

        String score = "1234";
        String gold = "4321";
        String health = "70%";
        Group root = new Group(newRectangle(203-193, 79-71, 293, 38),
        newText(score, 18, false, 242-193, 105-71),
        newText(gold, 18, false, 322-193, 105-71),
        newText(health, 18, false, 420-193, 105-71),
        textLineOne, textLineTwo, textLineThree, textLineFour,
        coinIcon, healthIcon, scoreIcon);


        int playerWidth = 150;
        int playerHeight = 150;
        int enemyWidth = 150;
        int enemyHeight = 150;
        
        if(type == "{N}"){
            ImageView neutralBubble = newImage("animations", "neutralbubble.png", 227-193, 390-71, 793, 211+511);
            root.getChildren().add(neutralBubble);
            neutralBubble.toBack();
        } else if(type == "{E}"){
            ImageView rightBubble = newImage("animations", "righttalkingbubble.png", 227-193, 390-71,793, 211+511);
            root.getChildren().add(rightBubble);
            rightBubble.toBack();
            playerWidth = 125;
            playerHeight = 125;
            enemyWidth = 175;
            enemyHeight = 175;
        } else if(type == "{P}"){
            ImageView leftBubble = newImage("animations", "lefttalkingbubble.png", 227-193, 390-71, 793, 211+511);
            root.getChildren().add(leftBubble);
            leftBubble.toBack();
            playerWidth = 175;
            playerHeight = 175;
            enemyWidth = 125;
            enemyHeight = 125;
        }
        //background.toBack
        //root.getChildren().addAll(enemyImage, playerImage);

        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }

    public void lvlScene(int lvl){
        try {
            stage.setScene(loadingScene());
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        delay(2000, () -> {
            try {
                stage.setScene(firstScene(lvl));
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        delay(2000, () -> {
            



        });
    }

    public ImageView newImage(String foldername, String imagename,
     int x, int y, int width, int height) throws FileNotFoundException{
        ImageView imageview = new ImageView();
        Image image = new Image(new FileInputStream("src/main/java/edu/ntnu/g60/resources/images/" + foldername + "/" + imagename));
        imageview.setImage(image);
        imageview.setX(x);
        imageview.setY(y);
        imageview.setFitHeight(width);
        imageview.setFitWidth(height);
        imageview.setPreserveRatio(true);
        return imageview;
    }

    public ProgressBar newHealthBar(int x, int y, double amount){
        ProgressBar health = new ProgressBar();
        health.setLayoutX(x);
        health.setLayoutY(y);
        health.setStyle("-fx-border-color: black;" + 
        "-fx-background-color: white;" +
        "-fx-pref-width: 100;" +
        "-fx-pref-height: 20;" +
        "-fx-accent: red;");
        health.setProgress(amount);
        return health;
    }

    public Rectangle newRectangle(int x, int y, int width, int height){
        Rectangle rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.WHITE);
        return rectangle;
    }

    public Button newButton(String text, int x, int y, String borderColor,
    String backgroundColor, int width, int height, int fontSize){
        Button button = new Button(text);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setStyle(setStyleString(borderColor, backgroundColor, width, height, fontSize));
        button.setOnMouseEntered(e -> button.setStyle(setStyleString(borderColor, "grey", width, height, fontSize)));
        button.setOnMouseExited(e -> button.setStyle(setStyleString(borderColor, backgroundColor, width, height, fontSize)));
        return button;
    }

    public TextField newTextField(String promptText, int x, int y, String borderColor,
    String backgroundColor, int width, int height, int fontSize){
        TextField textField = new TextField ();
        textField.setPromptText(promptText);
        textField.setLayoutX(x);
        textField.setLayoutY(y);
        textField.setStyle(setStyleString(borderColor, backgroundColor, width, height, fontSize));
        return textField;
    }

    public Text newText(String title, int size, boolean underline, int x, int y){
        Text text = new Text(title);
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, size));
        text.setUnderline(underline);
        text.setX(x);
        text.setY(y);
        return text;
    }

    public ChoiceBox<String> newChoiceBox(String[] choices, String borderColor,
    String backgroundColor, int width, int height, int fontSize, int x, int y){
        ChoiceBox<String> choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll(choices);
        choiceBox.setStyle(setStyleString(borderColor, backgroundColor, width, height, fontSize));
        choiceBox.setLayoutX(x);
        choiceBox.setLayoutY(y);
        return choiceBox;
    }
    
    public void alertBox(String title, String header, String content){
        Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
    }

    public String setStyleString(String borderColor,
    String backgroundColor, int width, int height, int fontSize){
        return "-fx-border-color: " + borderColor + ";" + 
        "-fx-background-color: " + backgroundColor + ";" +
        "-fx-pref-width: " + width + ";" +
        "-fx-pref-height: " + height + ";" +
        "-fx-font-size: " + fontSize + "px;";
    }
    
    public static void delay(long millis, Runnable continuation){
        Task<Void> sleeper = new Task<Void>(){
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }

    public static void main(String[] args){launch();}
}
