package edu.ntnu.g60.frontend;

import java.io.FileNotFoundException;
import java.util.List;
import edu.ntnu.g60.Player;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class NewFightScene {
    static Stage stage = ApplicationFront.getStage();
    static Player player = ApplicationFront.getPlayer();

    public static Scene scene(int lvl) throws FileNotFoundException{
        //TODO: add images like done on talking scene
        
        Button fightButton = ApplicationObjects.newButton("Fight", 209-193, 484-71, "fight_button");
        Button healButton = ApplicationObjects.newButton("Heal", 604-193, 484-71, "fight_button");
        Button inventoryButton = ApplicationObjects.newButton("Inventory", 209-193, 575-71, "fight_button");
        Button escapeButton = ApplicationObjects.newButton("Escape", 604-193, 575-71, "fight_button");

        ImageView coinIcon = ApplicationObjects.newImage("icons", "coin.png", 209-193, 86-71, 24, 24);
        ImageView scoreIcon = ApplicationObjects.newImage("icons", "star.png", 289-193, 86-71, 24, 24);

        int score = player.getScore();
        int gold = player.getGold();
        double playerHealth = player.getHealth()/100;
        double enemyHealth = 0; //make health variable
        Group root = new Group(ApplicationObjects.newRectangle(203-193, 79-71, 200, 38),
        ApplicationObjects.newHealthBar(259-193, 306-71, playerHealth, "progress_bar"), 
        ApplicationObjects.newHealthBar(789-193, 137-71, enemyHealth, "progress_bar"),
        ApplicationObjects.newText("" + score, 18, false, 242-193, 105-71),
        ApplicationObjects.newText("" + gold, 18, false, 322-193, 105-71),
        fightButton, healButton, inventoryButton, escapeButton,
        scoreIcon, coinIcon);
        
        //TODO:add functionality and variables to buttons

        fightButton.setOnAction(e -> {
            root.getChildren().removeAll(fightButton, healButton, inventoryButton, escapeButton);
            Button ability1Button = ApplicationObjects.newButton("Ability 1", 209-193, 484-71, "fight_button");
            Button ability2Button = ApplicationObjects.newButton("Ability 2", 604-193, 484-71, "fight_button");
            Button ability3Button = ApplicationObjects.newButton("Ability 3", 209-193, 575-71, "fight_button");
            Button backButton = ApplicationObjects.newButton("Back", 604-193, 575-71, "fight_button");
            backButton.setOnAction(b -> {
                root.getChildren().removeAll(ability1Button, ability2Button, ability3Button, backButton);
                root.getChildren().addAll(fightButton, healButton, inventoryButton, escapeButton);
            });
            root.getChildren().addAll(ability1Button, ability2Button, ability3Button, backButton);
        });

        healButton.setOnAction(e -> {
            root.getChildren().removeAll(fightButton, healButton, inventoryButton, escapeButton);
            Button heal1Button = ApplicationObjects.newButton("Heal 1", 209-193, 484-71, "fight_button");
            Button heal2Button = ApplicationObjects.newButton("Heal 2", 604-193, 484-71, "fight_button");
            Button backButton = ApplicationObjects.newButton("Back", 604-193, 575-71, "fight_button");
            backButton.setOnAction(b -> {
                root.getChildren().removeAll(heal1Button, heal2Button, backButton);
                root.getChildren().addAll(fightButton, healButton, inventoryButton, escapeButton);
            });
            root.getChildren().addAll(heal1Button, heal2Button, backButton);
        });

        inventoryButton.setOnAction(e -> {
            root.getChildren().removeAll(fightButton, healButton, inventoryButton, escapeButton);
            
            List<String> inventoryList = player.getInventory(); //TODO: add til de under
            Button item1Button = ApplicationObjects.newButton("Item 1", 209-193, 484-71, "fight_button");
            Button item2Button = ApplicationObjects.newButton("Item 2", 604-193, 484-71, "fight_button");
            Button item3Button = ApplicationObjects.newButton("Item 3", 209-193, 575-71, "fight_button");
            Button backButton = ApplicationObjects.newButton("Back", 604-193, 575-71, "fight_button");
            backButton.setOnAction(b -> {
                root.getChildren().removeAll(item1Button, item2Button, item3Button, backButton);
                root.getChildren().addAll(fightButton, healButton, inventoryButton, escapeButton);
            });
            root.getChildren().addAll(item1Button, item2Button, item3Button, backButton);
        });

        escapeButton.setOnAction(e -> {
            root.getChildren().removeAll(fightButton, healButton, inventoryButton, escapeButton);
            Button continueButton = ApplicationObjects.newButton("ESCAPE!", 209-193, 484-71, "fight_button");
            Button backButton = ApplicationObjects.newButton("Go back", 604-193, 484-71, "fight_button");
            backButton.setOnAction(b -> {
                root.getChildren().removeAll(continueButton, backButton);
                root.getChildren().addAll(fightButton, healButton, inventoryButton, escapeButton);
            });
            root.getChildren().addAll(continueButton, backButton);
        });

        root.getStylesheets().add("StyleSheet.css"); 
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        return scene;
    }
    
}
