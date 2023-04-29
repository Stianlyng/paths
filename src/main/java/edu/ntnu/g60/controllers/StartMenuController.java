package edu.ntnu.g60.controllers;

import java.io.IOException;
import java.net.MalformedURLException;

import edu.ntnu.g60.frontend.GameApp;
import edu.ntnu.g60.frontend.LvlSwitchAnimation;
import edu.ntnu.g60.models.Game;
import edu.ntnu.g60.models.Story;
import edu.ntnu.g60.utils.Save;
import edu.ntnu.g60.utils.SaveRegister;
import edu.ntnu.g60.views.StartMenu.ContinuePane;
import edu.ntnu.g60.views.StartMenu.NewGamePane;
import edu.ntnu.g60.views.StartMenu.OpeningPane;
import javafx.event.ActionEvent;

public class StartMenuController {
    

    public void continueAction(ActionEvent event){
        try {
            GameApp.changeRootPane(new ContinuePane());
        } catch (ClassNotFoundException | IOException e1) {
            e1.printStackTrace();
        }
    }

    public void newGameAction(ActionEvent event){
        try {
            GameApp.changeRootPane(new NewGamePane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void saveAction(ActionEvent event, int buttonNumber){
        try {
            LvlSwitchAnimation.animation(GameApp.getGame(), SaveRegister.getSave(buttonNumber).getPassage());
        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public void backAction(ActionEvent event){
        try {
            GameApp.changeRootPane(new OpeningPane());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void startAction(ActionEvent event){
        if(NewGamePane.saveName != null && !NewGamePane.saveName.equals("")){
            try {
                Game game = GameApp.getGame();
                
                int saveNumber = 1;
                if (SaveRegister.saveExists(1)) {
                    saveNumber = 2;
                    if (SaveRegister.saveExists(2)) {
                        saveNumber = 3;
                    }
                }
                
                Save save = new Save(game.getStory().getOpeningPassage(), NewGamePane.saveName, saveNumber);
                SaveRegister.setSave(save, saveNumber);
                Story.setCurrentSave(save);
                
                try {
                    LvlSwitchAnimation.animation(game, game.begin());
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException | ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        } 
    }
}
