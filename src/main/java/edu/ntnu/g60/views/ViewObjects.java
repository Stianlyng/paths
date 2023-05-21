package edu.ntnu.g60.views;

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

import java.net.MalformedURLException;
import java.net.URL;

import edu.ntnu.g60.controllers.SoundController;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The ViewObjects class provides static methods for creating various JavaFX UI elements.
 * These methods include creating buttons, choice boxes, sliders, images, text fields,
 * text labels, media players, progress bars, and rectangles.
 * @author olav sie
*/
public class ViewObjects {
    
    public static final String SOUND_PATH = "/sounds/";
    public static final String IMAGE_PATH = "/images/";

    /**
     * Creates a new button with the specified text, position, ID, hover style, and action event handler.
     * 
     * @param text    the text to display on the button
     * @param x       the x-coordinate of the button's position
     * @param y       the y-coordinate of the button's position
     * @param id      the ID of the button
     * @param hover   the hover style of the button
     * @param action  the action event handler for the button
     * @return        the created button
    */
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

    /**
     * Creates a new button with the specified text, position, ID, hover style, action event handler,
     * and alternate text for accessibility.
     * 
     * @param text      the text to display on the button
     * @param x         the x-coordinate of the button's position
     * @param y         the y-coordinate of the button's position
     * @param id        the ID of the button
     * @param hover     the hover style of the button
     * @param action    the action event handler for the button
     * @param altText   the alternate text for accessibility
     * @return          the created button
    */
    public static Button newButton(String text, int x, int y, String id, String hover, EventHandler<ActionEvent> action, String altText){
        Button button = newButton(text, x, y, id, hover, action, altText);
        Tooltip tooltip = new Tooltip();
        button.setAccessibleText(altText);
        tooltip.setText(button.getAccessibleText());
        button.setTooltip(tooltip);
        return button;
    }

    /**
     * Creates a new menu button with the specified action event handlers, ID, hover style, position,
     * and choices for the menu items.
     * 
     * @param action1   the action event handler for the first menu item
     * @param action2   the action event handler for the second menu item
     * @param id        the ID of the menu button
     * @param hover     the hover style of the menu button
     * @param x         the x-coordinate of the menu button's position
     * @param y         the y-coordinate of the menu button's position
     * @param choice1   the text for the first choice
     * @param choice2 the text for the second choice
    */
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

    /**
     * Creates a new slider with the specified position.
     * 
     * @param x the x-coordinate of the slider's position
     * @param y the y-coordinate of the slider's position
     * @return the created slider
    */
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

    /**
     * Creates a new choice box with the specified choices, position, and ID.
     * 
     * @param choices the array of choices for the choice box
     * @param x the x-coordinate of the choice box's position
     * @param y the y-coordinate of the choice box's position
     * @param id the ID of the choice box
     * @return the created choice box
    */
    public static ChoiceBox<String> newChoiceBox(String[] choices, int x, int y, String id) {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setId(id);
        choiceBox.getItems().addAll(choices);
        choiceBox.setLayoutX(x);
        choiceBox.setLayoutY(y);
        if (choices.length != 0) {
            choiceBox.setValue(choices[0]);
        }
        return choiceBox;
    }

    /**
     * Creates a new image view with the specified folder name, image name, position, size, and action event handler.
     * This method may throw a FileNotFoundException if the image file is not found.
     * 
     * @param foldername the name of the folder containing the image file
     * @param imagename the name of the image file
     * @param x the x-coordinate of the image view's position
     * @param y the y-coordinate of the image view's position
     * @param width the width of the image view
     * @param height the height of the image view
     * @param action the action event handler for the image view
     * @return the created image view
     * @throws FileNotFoundException if the image file is not found
    */
    public static ImageView newImage(String foldername, String imagename,
    int x, int y, int width, int height, EventHandler<MouseEvent> action) throws FileNotFoundException{
       ImageView imageview = newImage(foldername, imagename, x, y, width, height);
       imageview.setOnMouseClicked(action);
       return imageview;
    }

    /**
     * Creates a new image view with the specified folder name, image name, position, size.
     * This method may throw a FileNotFoundException if the image file is not found.
     * 
     * @param foldername the name of the folder containing the image file
     * @param imagename the name of the image file
     * @param x the x-coordinate of the image view's position
     * @param y the y-coordinate of the image view's position
     * @param width the width of the image view
     * @param height the height of the image view
     * @return the created image view
     * @throws FileNotFoundException if the image file is not found
    */
    public static ImageView newImage(String foldername, String imagename,
    int x, int y, int width, int height) throws FileNotFoundException{
       ImageView imageview = new ImageView();
       String imagePath = IMAGE_PATH + foldername + "/" + imagename;
       Image image = new Image(ViewObjects.class.getResourceAsStream(imagePath));

       imageview.setImage(image);
       imageview.setX(x);
       imageview.setY(y);
       imageview.setFitHeight(width);
       imageview.setFitWidth(height);
       imageview.setPreserveRatio(true);
       return imageview;
    }

    /**
     * Creates a new button with the specified text, position, ID, hover style.
     * @param text the text to display on the button
     * @param x the x-coordinate of the button's position
     * @param y the y-coordinate of the button's position
     * @param id the ID of the button
     * @param hover the hover style of the button
     * @return the created button
    */
    public static Button newBlankButton(String text, int x, int y, String id, String hover){
        Button button = new Button(text);
        button.setId(id);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setOnMouseEntered(e -> button.setId(hover));
        button.setOnMouseExited(e -> button.setId(id));
        return button;
    }

    /**
     * Creates a new text field with the specified prompt text, position, and ID.
     * 
     * @param promptText the prompt text to display in the text field
     * @param x the x-coordinate of the text field's position
     * @param y the y-coordinate of the text field's position
     * @param id the ID of the text field
     * @return the created text field
    */
    public static TextField newTextField(String promptText, int x, int y, String id){
        TextField textField = new TextField ();
        textField.setPromptText(promptText);
        textField.setLayoutX(x);
        textField.setLayoutY(y);
        textField.setId(id);
        return textField;
    }

    /**
     * Creates a new text with the specified title, size, underline style, position, and action event handler.
     * 
     * @param title the text to display
     * @param size the font size of the text
     * @param underline true if the text should be underlined, false otherwise
     * @param x the x-coordinate of the text's position
     * @param y the y-coordinate of the text's position
     * @param action the action event handler for the text
     * @return the created text
    */
    public static Text newText(String title, int size, boolean underline, int x, int y,
        EventHandler<MouseEvent> action){
        Text text = newText(title, size, underline, x, y);
        text.setOnMouseClicked(action);
        return text;
    }

    /**
     * Creates a new text with the specified title, size, underline style, and position.
     * @param title the text to display
     * @param size the font size of the text
     * @param underline true if the text should be underlined, false otherwise
     * @param x the x-coordinate of the text's position
     * @param y the y-coordinate of the text's position
     * @return the created text
    */
    public static Text newText(String title, int size, boolean underline, int x, int y){
        Text text = new Text(title);
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, size));
        text.setUnderline(underline);
        text.setX(x);
        text.setY(y);
        return text;
    }

    /**
     * Creates a new media player for the specified sound name.
     * This method may throw a MalformedURLException if the sound file URL is malformed.
     * @param soundName the name of the sound file (without the file extension)
     * @return the created media player
     * @throws MalformedURLException if the sound file URL is malformed
    */
    public static MediaPlayer newSound(String soundName) throws MalformedURLException{
        URL file = ViewObjects.class.getResource(SOUND_PATH + soundName + ".m4a");
        Media media = new Media(file.toString());
        return new MediaPlayer(media);
    }

    /**
     * Creates a new health bar with the specified position, amount, and ID.
     * @param x the x-coordinate of the health bar's position
     * @param y the y-coordinate of the health bar's position
     * @param amount the initial amount of the health bar (between 0.0 and 1.0)
     * @param id the ID of the health bar
     * @return the created health bar
    */
    public static ProgressBar newHealthBar(int x, int y, float amount, String id){
        ProgressBar health = new ProgressBar();
        health.setLayoutX(x);
        health.setLayoutY(y);
        health.setId(id);
        health.setProgress(amount);
        return health;
    }

    /**
     * Creates a new rectangle with the specified position, width, height, and action event handler.
     * 
     * @param x the x-coordinate of the rectangle's position
     * @param y the y-coordinate of the rectangle's position
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param action the action event handler for the rectangle
     * @return the created rectangle
    */
    public static Rectangle newRectangle(int x, int y, int width, int height,
    EventHandler<MouseEvent> action){
        Rectangle rectangle = newRectangle(x, y, width, height);
        rectangle.setOnMouseClicked(action);
        return rectangle;
    }

    /**
     * Creates a new rectangle with the specified position, width, and height.
     * 
     * @param x the x-coordinate of the rectangle's position
     * @param y the y-coordinate of the rectangle's position
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @return the created rectangle
    */
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
