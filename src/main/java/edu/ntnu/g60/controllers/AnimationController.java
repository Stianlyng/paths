package edu.ntnu.g60.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.views.GameApp;
import edu.ntnu.g60.views.GamePanes.ConversationPane;
import edu.ntnu.g60.views.StartMenu.OpeningPane;
import edu.ntnu.g60.views.TitlePanes.AdvancePane;
import edu.ntnu.g60.views.TitlePanes.DeathPane;
import edu.ntnu.g60.views.TitlePanes.LvlPane;

public class AnimationController {
    
    public void firstFrame() throws MalformedURLException{
        try {
            GameApp.changeRootPane(new AdvancePane());
        } catch (FileNotFoundException e1) {
            
            e1.printStackTrace();
        }
    }

    public void deathFirstFrame() throws FileNotFoundException{
        GameApp.changeRootPane(new DeathPane());
    }

    public void deathSecondFrame(){
        try {
            GameApp.changeRootPane(new OpeningPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void secondFrame(){
        try {
            GameApp.changeRootPane(new LvlPane(GameManager.getInstance().getGame().getCurrentPassage().getTitle()));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

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
