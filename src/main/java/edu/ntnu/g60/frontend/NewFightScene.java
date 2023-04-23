package edu.ntnu.g60.frontend;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import edu.ntnu.g60.models.Game;
import edu.ntnu.g60.models.Link;
import edu.ntnu.g60.models.Passage;
import edu.ntnu.g60.models.Player;
import edu.ntnu.g60.models.Story;
import edu.ntnu.g60.utils.Save;
import edu.ntnu.g60.utils.SaveRegister;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

//fix health amounts

public class NewFightScene {
    static float enemyHealth;
    static float playerHealth;
    static ProgressBar playerBar =  ApplicationObjects.newHealthBar(259-193, 306-71, playerHealth, "progress_bar"); 
    static ProgressBar enemyBar = ApplicationObjects.newHealthBar(789-193, 137-71, enemyHealth, "progress_bar");
    
    public static Scene scene(Game game, Passage passage) throws FileNotFoundException{
        ImageView enemyImage = ApplicationObjects.newImage("characters", passage.getPlayer(), 50, 150, 150, 150);
        ImageView playerImage = ApplicationObjects.newImage("characters", passage.getEnemy(), 600, 150, 150, 150);
        ImageView backgroundImage = ApplicationObjects.newImage("backgrounds", passage.getBackground(), 0, 0, 1650, 1000);

        Player player = game.getPlayer();
        Button fightButton = ApplicationObjects.newButton("Fight", 209-193, 484-71, "fight_button");
        Button healButton = ApplicationObjects.newButton("Heal", 604-193, 484-71, "fight_button");
        Button inventoryButton = ApplicationObjects.newButton("Inventory", 209-193, 575-71, "fight_button");
        Button escapeButton = ApplicationObjects.newButton("Escape", 604-193, 575-71, "fight_button");

        ImageView coinIcon = ApplicationObjects.newImage("icons", "coin.png", 209-193, 86-71, 24, 24);
        ImageView scoreIcon = ApplicationObjects.newImage("icons", "star.png", 289-193, 86-71, 24, 24);

        int score = player.getScore();
        int gold = player.getGold();
        playerHealth = (player.getHealth()/100);
        enemyHealth = 1.00F;
        playerBar.setProgress(playerHealth);
        enemyBar.setProgress(enemyHealth);

    
        Group root = new Group(backgroundImage, enemyImage, playerImage, ApplicationObjects.newRectangle(203-193, 79-71, 200, 38),
        ApplicationObjects.newText("" + score, 18, false, 242-193, 105-71),
        ApplicationObjects.newText("" + gold, 18, false, 322-193, 105-71),
        playerBar, enemyBar,
        fightButton, healButton, inventoryButton, escapeButton,
        scoreIcon, coinIcon);
        //TODO:add functionality to inventory buttons

        fightButton.setOnAction(e -> {
            root.getChildren().removeAll(fightButton, healButton, inventoryButton, escapeButton);
            Button ability1Button = ApplicationObjects.newButton("Insult", 209-193, 484-71, "fight_button");
            ability1Button.setOnAction(h ->{
                playerAction(0.15F, 0, game, passage);
                enemyAction(0.4F, 0, game, passage);
            });
            Button ability2Button = ApplicationObjects.newButton("Shame", 604-193, 484-71, "fight_button");
            ability2Button.setOnAction(h ->{
                playerAction(0.1F, 0, game, passage);
                enemyAction(0.2F, 0, game, passage);
            });
            Button ability3Button = ApplicationObjects.newButton("Hit", 209-193, 575-71, "fight_button");
            ability3Button.setOnAction(h ->{
                playerAction(0.65F, 0, game, passage);
                enemyAction(0.25F, 0, game, passage);
            });
            Button backButton = ApplicationObjects.newButton("Back", 604-193, 575-71, "fight_button");
            backButton.setOnAction(b -> {
                root.getChildren().removeAll(ability1Button, ability2Button, ability3Button, backButton);
                root.getChildren().addAll(fightButton, healButton, inventoryButton, escapeButton);
            });
            root.getChildren().addAll(ability1Button, ability2Button, ability3Button, backButton);
        });

        healButton.setOnAction(e -> {
            root.getChildren().removeAll(fightButton, healButton, inventoryButton, escapeButton);
            Button heal1Button = ApplicationObjects.newButton("Lick wound", 209-193, 484-71, "fight_button");
            heal1Button.setOnAction(h ->{
                playerAction(0.0F, 0.01F, game, passage);
                enemyAction(0.2F, 0, game, passage);
            });
            Button heal2Button = ApplicationObjects.newButton("Ignore pain", 604-193, 484-71, "fight_button");
            heal2Button.setOnAction(h ->{
                playerAction(0.0F, (1.0F - playerHealth), game, passage);
                enemyAction(0.1F, 0, game, passage);
            });
            Button backButton = ApplicationObjects.newButton("Back", 604-193, 575-71, "fight_button");
            backButton.setOnAction(b -> {
                root.getChildren().removeAll(heal1Button, heal2Button, backButton);
                root.getChildren().addAll(fightButton, healButton, inventoryButton, escapeButton);
            });
            root.getChildren().addAll(heal1Button, heal2Button, backButton);
        });

        inventoryButton.setOnAction(e -> {
            root.getChildren().removeAll(fightButton, healButton, inventoryButton, escapeButton);
            
            List<String> inventoryList = player.getInventory();
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
            continueButton.setOnAction(b -> {
                try {
                    looseFight(game, passage);
                } catch (MalformedURLException | FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            });
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

    public static void winFight(Game game, Passage passage) throws MalformedURLException, FileNotFoundException{
        Link link2 = passage.getLinks().get(1);
        if(link2.getReference().equals("game over")){
            ApplicationFront.switchToScene(DeathScene.scene());
            LvlScene.delay(3000, () -> {
                try {
                    ApplicationFront.switchToScene(OpeningScene.scene());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        } else { 
            LvlScene.scene(game, game.go(link2));
            try {
                SaveRegister.setSave(new Save(game.go(link2), Story.getCurrentSave().getSaveName(),
                Story.getCurrentSave().getSaveNumber()), Story.getCurrentSave().getSaveNumber());
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void looseFight(Game game, Passage passage) throws MalformedURLException, FileNotFoundException{
        Link link1 = passage.getLinks().get(0);
        if(link1.getReference().equals("game over")){
            ApplicationFront.switchToScene(DeathScene.scene());
            LvlScene.delay(3000, () -> {
                try {
                    ApplicationFront.switchToScene(OpeningScene.scene());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        } else { 
            LvlScene.scene(game, game.go(link1));
            try {
                SaveRegister.setSave(new Save(game.go(link1), Story.getCurrentSave().getSaveName(),
                Story.getCurrentSave().getSaveNumber()), Story.getCurrentSave().getSaveNumber());
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void enemyAction(float damageAmount, float healAmount, Game game, Passage passage){
        LvlScene.delay(2000, () -> {
            playerHealth = playerHealth - damageAmount;
            enemyHealth = enemyHealth + healAmount;
            playerBar.setProgress(playerHealth);
            enemyBar.setProgress(enemyHealth);
            if(enemyHealth < 0.00F){
                try {
                    winFight(game, passage);
                } catch (MalformedURLException | FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if(playerHealth < 0.00F){
                try {
                    looseFight(game, passage);
                } catch (MalformedURLException | FileNotFoundException e) {
                    e.printStackTrace();
                }
            }   
        });
    }

    public static void playerAction(float damageAmount, float healAmount, Game game, Passage passage){
        
        enemyHealth = enemyHealth - damageAmount;
        playerHealth = playerHealth + healAmount;
        playerBar.setProgress(playerHealth);
        enemyBar.setProgress(enemyHealth);
        if(enemyHealth < 0.00F){
            try {
                winFight(game, passage);
            } catch (MalformedURLException | FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(playerHealth < 0.00F){
            try {
                looseFight(game, passage);
            } catch (MalformedURLException | FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
