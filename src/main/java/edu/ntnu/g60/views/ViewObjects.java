package edu.ntnu.g60.views;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ViewObjects {
    public static Button newButton(String text, int x, int y, String id, EventHandler<ActionEvent> action){
        Button button = new Button(text);
        button.setId(id);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setOnAction(action);
        return button;
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

    public static Button newBlankButton(String text, int x, int y, String id){
        Button button = new Button(text);
        button.setId(id);
        button.setLayoutX(x);
        button.setLayoutY(y);
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

    public static Text newText(String title, int size, boolean underline, int x, int y){
        Text text = new Text(title);
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, size));
        text.setUnderline(underline);
        text.setX(x);
        text.setY(y);
        return text;
    }
}
