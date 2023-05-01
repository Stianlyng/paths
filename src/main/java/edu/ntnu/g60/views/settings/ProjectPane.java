package edu.ntnu.g60.views.settings;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ntnu.g60.controllers.StartMenuController;
import edu.ntnu.g60.views.Values;
import edu.ntnu.g60.views.ViewObjects;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;

public class ProjectPane extends StackPane{
    
    private static StartMenuController controller;


    public ProjectPane() throws FileNotFoundException, IOException {
        ProjectPane.controller = new StartMenuController();
        getChildren().addAll(getProjectObjects());
    }

    private static Group getProjectObjects() throws FileNotFoundException {
        Button backButton = ViewObjects.newButton("Back", 50, 20, Values.BACK_BUTTON_ID, Values.BACK_BUTTON_HOVER_ID, controller::backAction);
        String url = "https://gitlab.stud.idi.ntnu.no/gruppe_60/paths";
        WebView webView = new WebView();
        webView.getEngine().load(url);
        webView.getEngine().setOnStatusChanged(event -> {
            if(event.getData().startsWith("http")) {
                webView.getEngine().load(event.getData());
            }
        });
        return new Group(webView, backButton);
    }
}
