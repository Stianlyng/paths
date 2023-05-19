package edu.ntnu.g60.views;

import edu.ntnu.g60.components.BackgroundComponent;
import edu.ntnu.g60.utils.SaveFileHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class SelectPlayer extends StackPane {
    private static final int PADDING = 20;

    public SelectPlayer() {

       
        Background background = BackgroundComponent.createBackground("selectPlayerScene.png");
        ComboBox<String> playerSelection = playerSelection();
        TextField playerName = setPlayerName(playerSelection);
        Button continueButton = continueButton(playerName);

        
        // make button width same as text field
        
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        layout.add(playerSelection, 0, 0);
        layout.add(playerName, 0, 1);
        layout.add(continueButton, 0, 2);
        layout.setBackground(background);
        this.getChildren().add(layout);
    }

    private ComboBox<String> playerSelection(){
        ComboBox<String> playerSelection = new ComboBox<>();
        playerSelection.setPromptText("Select a player");
        playerSelection.getItems().addAll(SaveFileHandler.getAvailablePlayers());
        return playerSelection;
    }
    
    private TextField setPlayerName(ComboBox<String> playerSelection){
        TextField playerName = new TextField();
        playerName.setPromptText("Enter a new player name");
        playerSelection.setOnAction(e -> playerName.setText(playerSelection.getValue()));
        return playerName;
    }
    
    private Button continueButton(TextField playerName){
        Button continueButton = new Button("Continue");
        continueButton.prefWidthProperty().bind(playerName.widthProperty());
        continueButton.setOnAction(e -> {
            String enteredPlayer = playerName.getText();
            switchToMainMenu(enteredPlayer);
        });
        return continueButton;
    }

    public StackPane getLayout() {
        return this;
    }

    private void switchToMainMenu(String playerName) {
        MainMenu mainMenu = new MainMenu(playerName);
        App.changeRootPane(mainMenu.getLayout());
    }
}