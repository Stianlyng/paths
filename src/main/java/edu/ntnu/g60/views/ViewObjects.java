package edu.ntnu.g60.views;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.io.File;
import java.net.MalformedURLException;

import edu.ntnu.g60.controllers.SoundController;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ViewObjects {

    public static Button newButton(String text, int x, int y, String id, String hover, EventHandler<ActionEvent> action){
        Button button = new Button(text);
        button.setId(id);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setOnAction(action);
        button.setOnMouseEntered(e -> button.setId(hover));
        button.setOnMouseExited(e -> button.setId(id));
        return button;
    }

    public static Button newButton(String text, int x, int y, String id, String hover, EventHandler<ActionEvent> action, String altText){
        Button button = new Button(text);
        button.setAccessibleText(altText);
        button.setId(id);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setOnAction(action);
        button.setOnMouseEntered(e -> button.setId(hover));
        button.setOnMouseExited(e -> button.setId(id));
        Tooltip tooltip = new Tooltip();
        tooltip.setText(button.getAccessibleText());
        button.setTooltip(tooltip);
        return button;
    }

    public static MenuButton newMenuButton(EventHandler<ActionEvent> action1, EventHandler<ActionEvent> action2, String id, String hover,
    int x, int y, String choice1, String choice2){
        MenuButton dropdownButton = new MenuButton();
        dropdownButton.setId(id);
        dropdownButton.setLayoutX(x);
        dropdownButton.setLayoutY(y);
        MenuItem menuItem1 = new MenuItem(choice1);
        menuItem1.setOnAction(action1);
        MenuItem menuItem2 = new MenuItem(choice2);
        menuItem2.setOnAction(action2);
        dropdownButton.getItems().addAll(menuItem1, menuItem2);
        dropdownButton.setOnMouseEntered(e -> dropdownButton.setId(hover));
        dropdownButton.setOnMouseExited(e -> dropdownButton.setId(id));
        return dropdownButton;
    }

    public static Slider newSlider(int x, int y){
        Slider volumeSlider = new Slider(0, 100, 50);
        volumeSlider.setId("slider");
        volumeSlider.setLayoutX(x);
        volumeSlider.setLayoutY(y);
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double newVolume = newValue.doubleValue() / 100.0;
                SoundController.setApplicationVolume(newVolume);
            }
        });
        return volumeSlider;
    }


    public static ChoiceBox<String> newChoiceBox(String[] choices, int x, int y, String id) {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setValue(choices[0]);
        choiceBox.setId(id);
        choiceBox.getItems().addAll(choices);
        choiceBox.setLayoutX(x);
        choiceBox.setLayoutY(y);
        return choiceBox;
    }

    public static ImageView newImage(String foldername, String imagename,
    int x, int y, int width, int height, EventHandler<MouseEvent> action) throws FileNotFoundException{
       ImageView imageview = new ImageView();
       Image image = new Image(new FileInputStream("src/main/resources/images/" + foldername + "/" + imagename));
       imageview.setOnMouseClicked(action);
       imageview.setImage(image);
       imageview.setX(x);
       imageview.setY(y);
       imageview.setFitHeight(width);
       imageview.setFitWidth(height);
       imageview.setPreserveRatio(true);
       return imageview;
    }

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

    public static Button newBlankButton(String text, int x, int y, String id, String hover){
        Button button = new Button(text);
        button.setId(id);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setOnMouseEntered(e -> button.setId(hover));
        button.setOnMouseExited(e -> button.setId(id));
        return button;
    }

    public static TextField newTextField(String promptText, int x, int y, String id){
        TextField textField = new TextField ();
        textField.setPromptText(promptText);
        textField.setLayoutX(x);
        textField.setLayoutY(y);
        textField.setId(id);
        return textField;
    }

    public static Text newText(String title, int size, boolean underline, int x, int y,
        EventHandler<MouseEvent> action){
        Text text = new Text(title);
        text.setOnMouseClicked(action);
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, size));
        text.setUnderline(underline);
        text.setX(x);
        text.setY(y);
        return text;
    }

    public static Text newText(String title, int size, boolean underline, int x, int y){
        Text text = new Text(title);
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, size));
        text.setUnderline(underline);
        text.setX(x);
        text.setY(y);
        return text;
    }

    public static MediaPlayer newSound(String soundName) throws MalformedURLException{
        File mediaFile = new File("src/main/resources/sounds/" + soundName + ".m4a");
        Media media = new Media(mediaFile.toURI().toURL().toString());
        MediaPlayer player = new MediaPlayer(media);
        return player;
    }

    public static ProgressBar newHealthBar(int x, int y, float amount, String id){
        ProgressBar health = new ProgressBar();
        health.setLayoutX(x);
        health.setLayoutY(y);
        health.setId(id);
        health.setProgress(amount);
        return health;
    }

    public static Rectangle newRectangle(int x, int y, int width, int height,
    EventHandler<MouseEvent> action){
        Rectangle rectangle = new Rectangle();
        rectangle.setOnMouseClicked(action);
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.rgb(150, 111, 51));
        rectangle.setOpacity(30);
        return rectangle;
    }

    public static Rectangle newRectangle(int x, int y, int width, int height){
        Rectangle rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.rgb(150, 111, 51));
        rectangle.setOpacity(30);
        return rectangle;
    }
}
