package edu.ntnu.g60.views;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DialogBoxes {
    //add feedback dialogs and add to all of frontend
    //make these but more of them with diffrent inputs
    public static void alertBox(String title, String header, String content){
        Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
    }
}
