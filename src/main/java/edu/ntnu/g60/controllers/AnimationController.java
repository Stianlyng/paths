package edu.ntnu.g60.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import edu.ntnu.g60.models.passage.PassageManager;
import edu.ntnu.g60.views.GameApp;
import edu.ntnu.g60.views.GamePanes.ConversationPane;
import edu.ntnu.g60.views.StartMenu.OpeningPane;
import edu.ntnu.g60.views.TitlePanes.AdvancePane;
import edu.ntnu.g60.views.TitlePanes.DeathPane;
import edu.ntnu.g60.views.TitlePanes.LvlPane;

/**
 * The AnimationController class is responsible for controlling the
 * animations and transitions between panes.
 */
public class AnimationController {
    
    /**
    * Loads and displays the first frame of the next level sequence.
    * @throws MalformedURLException if the specified URL is malformed.
    */
    public void firstFrame() throws MalformedURLException{
        try {
            GameApp.changeRootPane(new AdvancePane());
        } catch (FileNotFoundException e1) {
            
            e1.printStackTrace();
        }
    }

    /**
    * Loads and displays the first frame of the death sequence.
    * @throws FileNotFoundException if the specified file is not found.
    */
    public void deathFirstFrame() throws FileNotFoundException{
        GameApp.changeRootPane(new DeathPane());
    }

    /**
    * Loads and displays the second frame of the death sequence.
    */
    public void deathSecondFrame(){
        try {
            GameApp.changeRootPane(new OpeningPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
    * Loads and displays the second frame of the next level sequence.
    */
    public void secondFrame(){
        try {
            GameApp.changeRootPane(new LvlPane(PassageManager.getInstance().getPassage().getTitle())); //todo; dont use singleton
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    /**
    * Loads and displays the third frame of the next level sequence.
    */
    public void thirdFrame(){
        try {
            ConversationPaneController.setConversationPaneNumber(0);
            ConversationPane pane = new ConversationPane();
            ConversationPaneController.setCurrentConversationPane(pane);
            GameApp.changeRootPane(pane);
            
        } catch (FileNotFoundException | MalformedURLException e1) {
            
            e1.printStackTrace();
        }
    }
}
