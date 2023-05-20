package edu.ntnu.g60.views;

import java.util.List;
import java.util.Optional;

import edu.ntnu.g60.components.BackgroundComponent;
import edu.ntnu.g60.models.actions.Action;
import edu.ntnu.g60.models.game.Game;
import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.player.Player;
import edu.ntnu.g60.utils.SaveFileHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class PlayGame extends StackPane{

    private static final int PADDING = 20;
    private static final int SPACING = 10;
    
    private static final String HEALTH = "Health: ";
    private static final String GOLD = "Gold: ";
    private static final String SCORE = "Score: ";

    private static final String MAIN_MENU = "Main Menu";
    private static final String OPTIONS = "Options";
    private static final String SAVE = "Save";


    public PlayGame(Passage passage) {

        Background background = BackgroundComponent.createBackground(passage.getBackground());

        BorderPane layout = new BorderPane();
        layout.setTop(topBar(optionDropdown(passage)));
        layout.setCenter(passageContent(passage));
        layout.setBottom(chooseLinkButtons(passage));
        layout.setBackground(background);
        this.getChildren().add(layout);
    }
    
    private VBox passageContent(Passage passage) {
    
        VBox passageContent = new VBox();
        passageContent.setAlignment(Pos.CENTER);
        passageContent.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        passageContent.setSpacing(SPACING);
        
        Label titleLabel = new Label(passage.getTitle());
        Label contentLabel = new Label(passage.getContent());
    
        // Set the initial opacity of the labels
        titleLabel.setOpacity(0.0);
        contentLabel.setOpacity(0.0);
        
        // Create FadeTransitions for each label
        FadeTransition fadeTitle = new FadeTransition(Duration.seconds(2), titleLabel);
        fadeTitle.setFromValue(0.0);
        fadeTitle.setToValue(1.0);
    
        FadeTransition fadeContent = new FadeTransition(Duration.seconds(2), contentLabel);
        fadeContent.setFromValue(0.0);
        fadeContent.setToValue(1.0);
    
        // Add labels to the passage content
        passageContent.getChildren().addAll(titleLabel, contentLabel);
    
        // Play the FadeTransitions
        fadeTitle.play();
        fadeContent.play();
    
        return passageContent;
    }
    
    private GridPane chooseLinkButtons(Passage passage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(SPACING);
        gridPane.setPadding(new Insets(0, 0, PADDING, 0));  

        Game game = GameManager.getInstance().getGame();
        List<Link> links = passage.getLinks();

        for (int i = 0; i < links.size(); i++) {
            final int index = i;
            Button linkButton = new Button(links.get(i).getText());
            linkButton.setOnAction(e -> {

                Link selectedLink = links.get(index);

                for (Action action : selectedLink.getActions()) {
                    action.execute(game.getPlayer());
                }

                Passage newPassage = game.go(links.get(index));
                PlayGame newView = new PlayGame(newPassage);
                App.changeRootPane(newView.getLayout());
            });

            gridPane.add(linkButton, i, 0);
        }

        return gridPane;
    }

    private HBox topBar(ComboBox<String> options) {
        HBox topBox = new HBox();
        topBox.setAlignment(Pos.CENTER_RIGHT);
        topBox.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        topBox.setSpacing(SPACING);
        
        Player player = GameManager.getInstance().getGame().getPlayer();
        topBox.getChildren().add(new Label(HEALTH + player.getHealth()));        
        topBox.getChildren().add(new Label(GOLD + player.getGold()));        
        topBox.getChildren().add(new Label(SCORE + player.getScore()));        
        
        topBox.getChildren().add(options);
        return topBox;
    }
    
    private ComboBox<String> optionDropdown(Passage passage){
        ComboBox<String> options = new ComboBox<>();
        options.getItems().addAll(SAVE, MAIN_MENU);
        options.setPromptText(OPTIONS);
    
        options.setOnAction(e -> {
            String selectedOption = options.getSelectionModel().getSelectedItem();
            switch (selectedOption) {
                case SAVE:
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Save Game");
                    dialog.setHeaderText("Enter a name for your save file");
                    dialog.setContentText("Name:");
                    Optional<String> result = dialog.showAndWait();
                    result.ifPresent(name -> saveGame(name, passage.getTitle()));
                    break;
                case MAIN_MENU:
                    String playerName = GameManager.getInstance().getGame().getPlayer().getName();
                    GameManager.getInstance().endGame();
                    MainMenu mainMenu = new MainMenu(playerName);  // Change this line
                    App.changeRootPane(mainMenu.getLayout());  // Add this line
                    break;
            }
        });
        return options;
    }
    
    private void saveGame(String saveName, String currentPassageTitle) {
        Game game = GameManager.getInstance().getGame();
        try {
            SaveFileHandler.saveGameToFile(game, saveName, currentPassageTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public StackPane getLayout() {
        return this;
    }
}
