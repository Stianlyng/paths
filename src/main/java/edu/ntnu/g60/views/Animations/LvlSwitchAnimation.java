package edu.ntnu.g60.views.Animations;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import edu.ntnu.g60.controllers.ConversationPaneController;
import edu.ntnu.g60.controllers.GameController;
import edu.ntnu.g60.views.TitlePanes.LvlPane;
import edu.ntnu.g60.views.GameApp;
import edu.ntnu.g60.views.GamePanes.ConversationPane;
import edu.ntnu.g60.views.TitlePanes.AdvancePane;

//TODO: passer det å ha animations i views da det egt er mer controller?
public class LvlSwitchAnimation {

    public static void animation() throws MalformedURLException{
        firstFrame();
        GameController.delay(2000, () -> {
            secondFrame();
            GameController.delay(2000, () -> {
                thirdFrame();
            });
        });
    }

    public static void firstFrame(){
        try {
            GameApp.changeRootPane(new AdvancePane());
        } catch (FileNotFoundException e1) {
            
            e1.printStackTrace();
        }
    }

    public static void secondFrame(){
        try {
            GameApp.changeRootPane(new LvlPane(GameController.getCurrentPassage().getTitle()));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public static void thirdFrame(){
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
