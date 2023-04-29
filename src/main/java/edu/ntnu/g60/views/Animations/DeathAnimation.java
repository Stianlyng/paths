package edu.ntnu.g60.views.Animations;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ntnu.g60.views.GameApp;
import edu.ntnu.g60.views.StartMenu.OpeningPane;
import edu.ntnu.g60.views.TitlePanes.DeathPane;

public class DeathAnimation {
    public static void animation() throws FileNotFoundException{
        GameApp.changeRootPane(new DeathPane());
        GameApp.delay(3000, () -> {
            try {
                GameApp.changeRootPane(new OpeningPane());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }
}
