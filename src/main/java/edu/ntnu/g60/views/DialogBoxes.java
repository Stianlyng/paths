package edu.ntnu.g60.views;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class DialogBoxes {

    public static void alertBox(String title, String header, String content){
        Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
    }

    public static boolean alertBoxChoices(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
    
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
    
        alert.getButtonTypes().setAll(yesButton, noButton);
    
        Optional<ButtonType> result = alert.showAndWait();
        return result.orElse(noButton) == yesButton;
    }
}
