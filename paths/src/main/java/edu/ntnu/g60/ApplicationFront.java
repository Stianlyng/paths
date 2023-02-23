package edu.ntnu.g60;

//TODO: make sure the mouse does not start in the textfield, this makes the prompt text unreadable
//TODO: make it so the user can input the email key with spaces or without. take input and remove spaces

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ApplicationFront extends Application {
    
    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        this.stage = stage;
        stage.setScene(firstScene());
        stage.show();
    }

    public Scene firstScene(){
        Group root = new Group();
        Scene scene = new Scene(root, 500, 300, Color.WHITE);
        return scene;

    }

    public Button newButton(String text, int x, int y, String borderColor,
    String backgroundColor, int width, int height, int fontSize){
        Button button = new Button(text);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setStyle("-fx-border-color: " + borderColor + ";" + 
        "-fx-background-color: " + backgroundColor + ";" +
        "-fx-pref-width: " + width + ";" +
        "-fx-pref-height: " + height + ";" +
        "-fx-font-size: " + fontSize + "px;");
        return button;
    }

    public TextField newTextField(String promptText, int x, int y, String borderColor,
    String backgroundColor, int width, int height, int fontSize){
        TextField textField = new TextField ();
        textField.setPromptText(promptText);
        textField.setLayoutX(x);
        textField.setLayoutY(y);
        textField.setStyle("-fx-border-color: " + borderColor + ";" + 
        "-fx-background-color: " + backgroundColor + ";" +
        "-fx-pref-width: " + width + ";" +
        "-fx-pref-height: " + height + ";" +
        "-fx-font-size: " + fontSize + "px;");
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
        choiceBox.setStyle("-fx-border-color: " + borderColor + ";" + 
        "-fx-background-color: " + backgroundColor + ";" +
        "-fx-pref-width: " + width + ";" +
        "-fx-pref-height: " + height + ";" +
        "-fx-font-size: " + fontSize + "px;");
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

    public static void main(String[] args){launch();}
}
