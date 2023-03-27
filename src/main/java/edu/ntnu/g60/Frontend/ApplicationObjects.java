package edu.ntnu.g60.frontend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ApplicationObjects {
    
    public static ImageView newImage(String foldername, String imagename,
     int x, int y, int width, int height) throws FileNotFoundException{
        ImageView imageview = new ImageView();
        Image image = new Image(new FileInputStream("src/main/resources/images/" + foldername + "/" + imagename));
        imageview.setImage(image);
        imageview.setX(x);
        imageview.setY(y);
        imageview.setFitHeight(width);
        imageview.setFitWidth(height);
        imageview.setPreserveRatio(true);
        return imageview;
    }
    //"sounds/" + soundName + ".mp3"
    public static MediaPlayer newSound(String soundName) throws MalformedURLException{
        File mediaFile = new File("src/main/resources/sounds/" + soundName + ".mp3");
        Media media = new Media(mediaFile.toURI().toURL().toString());
        MediaPlayer player = new MediaPlayer(media);
        return player;
    }

    public static ProgressBar newHealthBar(int x, int y, double amount){
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

    public static Rectangle newRectangle(int x, int y, int width, int height){
        Rectangle rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.WHITE);
        return rectangle;
    }

    public static Button newButton(String text, int x, int y, String borderColor,
    String backgroundColor, int width, int height, int fontSize){
        Button button = new Button(text);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setStyle(setStyleString(borderColor, backgroundColor, width, height, fontSize));
        button.setOnMouseEntered(e -> button.setStyle(setStyleString(borderColor, "grey", width, height, fontSize)));
        button.setOnMouseExited(e -> button.setStyle(setStyleString(borderColor, backgroundColor, width, height, fontSize)));
        return button;
    }

    public static TextField newTextField(String promptText, int x, int y, String borderColor,
    String backgroundColor, int width, int height, int fontSize){
        TextField textField = new TextField ();
        textField.setPromptText(promptText);
        textField.setLayoutX(x);
        textField.setLayoutY(y);
        textField.setStyle(setStyleString(borderColor, backgroundColor, width, height, fontSize));
        return textField;
    }

    public static Text newText(String title, int size, boolean underline, int x, int y){
        Text text = new Text(title);
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, size));
        text.setUnderline(underline);
        text.setX(x);
        text.setY(y);
        return text;
    }

    public static ChoiceBox<String> newChoiceBox(String[] choices, String borderColor,
    String backgroundColor, int width, int height, int fontSize, int x, int y){
        ChoiceBox<String> choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll(choices);
        choiceBox.setStyle(setStyleString(borderColor, backgroundColor, width, height, fontSize));
        choiceBox.setLayoutX(x);
        choiceBox.setLayoutY(y);
        return choiceBox;
    }
    
    public static void alertBox(String title, String header, String content){
        Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
    }

    public static String setStyleString(String borderColor,
    String backgroundColor, int width, int height, int fontSize){
        return "-fx-border-color: " + borderColor + ";" + 
        "-fx-background-color: " + backgroundColor + ";" +
        "-fx-pref-width: " + width + ";" +
        "-fx-pref-height: " + height + ";" +
        "-fx-font-size: " + fontSize + "px;";
    }
    

}