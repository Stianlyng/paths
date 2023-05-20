package edu.ntnu.g60.views;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class DialogBoxes {

    public static void alertBox(String title, String header, String content){
        Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
    }

    public static boolean alertBoxWithChoices(String title, String header, String content) {
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

    public static String dialogBoxWithTextInput(String title, String header, String content){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);

        dialog.getDialogPane().lookupButton(dialog.getDialogPane().getButtonTypes().get(0)).setDisable(true);
        dialog.getEditor().textProperty().addListener((observable, oldValue, newValue) ->
                dialog.getDialogPane().lookupButton(dialog.getDialogPane().getButtonTypes().get(0)).setDisable(newValue.trim().isEmpty())
        );
        
        Optional<String> result = dialog.showAndWait();
        return result.orElse("");
    }

}
