package edu.ntnu.g60.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.views.GameApp;
import edu.ntnu.g60.views.GamePanes.ConversationPane;
import edu.ntnu.g60.views.StartMenu.OpeningPane;
import edu.ntnu.g60.views.TitlePanes.TxtPane;

/**
 * The AnimationController class is responsible for controlling the
 * animations and transitions between panes.
 */
public class AnimationController {

    /**
    * Loads and displays the second frame of the next level sequence.
    */
    public void showPassageTitle(String passageTitle){
        try {
            GameApp.changeRootPane(new TxtPane(passageTitle)); //todo; dont use singleton
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    /**
    * Loads and displays the third frame of the next level sequence.
    */
    public void beginPassage(Passage passage){
        try {
            ConversationPaneController.setConversationPaneNumber(0);
            ConversationPane pane = new ConversationPane(passage);
            ConversationPaneController.setCurrentConversationPane(pane);
            GameApp.changeRootPane(pane);
            
        } catch (FileNotFoundException | MalformedURLException e1) {
            
            e1.printStackTrace();
        }
    }

    public void showPaneWithText(String text) throws FileNotFoundException{
        GameApp.changeRootPane(new TxtPane(text));
    }

    public void goToOpening(){
        try {
            GameApp.changeRootPane(new OpeningPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
