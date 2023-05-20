package edu.ntnu.g60.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.views.GameApp;
import edu.ntnu.g60.views.GamePanes.ConversationPane;
import edu.ntnu.g60.views.StartMenu.MainMenuPane;
import edu.ntnu.g60.views.TitlePanes.TxtPane;

/**
 * The AnimationController class is responsible for controlling the
 * animations and transitions between panes.
 */
public class AnimationController {

    /**
     * Begins a passage by setting up the initial conversation pane and changing the root pane to it.
     * 
     *  @param passage The Passage object representing the passage to begin.
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

    /**
     * Shows a pane with the specified text by changing the root pane to a TxtPane with the given text.
     * 
     * @param text The text to display in the pane.
     * @throws FileNotFoundException if the specified text file is not found.
    */
    public void showPaneWithText(String text) throws FileNotFoundException{
        GameApp.changeRootPane(new TxtPane(text));
    }

    /**
     * Goes to the opening main menu pane by changing the root pane to a MainMenuPane.
    */
    public void goToOpening(){
        try {
            GameApp.changeRootPane(new MainMenuPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
