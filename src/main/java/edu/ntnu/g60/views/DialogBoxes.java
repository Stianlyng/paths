package edu.ntnu.g60.views;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;


/**
 * The DialogBoxes class provides utility methods for displaying dialog boxes.
 * It includes methods for showing alert boxes, alert boxes with choices, and dialog boxes with text input.
*/
public class DialogBoxes {

    /**
     * Displays an alert box with the specified title, header, and content.
     * 
     * @param title   the title of the alert box
     * @param header  the header text of the alert box
     * @param content the content text of the alert box
    */
    public static void alertBox(String title, String header, String content){
        Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
    }

    /**
     * Displays an alert box with choices and returns true if the user selects the "Yes" button,
     * or false if the user selects the "No" button or closes the dialog.
     * 
     * @param title   the title of the alert box
     * @param header  the header text of the alert box
     * @param content the content text of the alert box
     * @return true if the user selects the "Yes" button, false otherwise
    */
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

    /**
     * Displays a dialog box with a text input field and returns the input text entered by the user.
     * If the user cancels the dialog, an empty string is returned.
     * 
     * @param title   the title of the dialog box
     * @param header  the header text of the dialog box
     * @param content the content text of the dialog box
     * @return the input text entered by the user, or an empty string if canceled
    */
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
