package edu.ntnu.g60.views.Animations;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import edu.ntnu.g60.controllers.AnimationController;
import edu.ntnu.g60.utils.frontend.FrontendUtils;
import javafx.scene.layout.StackPane;

/**
 * The EndGameAnimation class provides a static method for playing the end game animation
 * when the game has ended.
*/
public class EndGameAnimation extends StackPane{

    private static AnimationController controller;

    /**
     * Static block to initialize the AnimationController.
    */
    static {
        controller = new AnimationController();
    }

    /**
     * Plays the end game animation when the game has ended.
     * 
     * @throws FileNotFoundException if the required resource is not found
    */    
    public static void animation() throws MalformedURLException, FileNotFoundException{
        controller.showPaneWithText("Goals not reached");
        FrontendUtils.delay(1500, () -> {
            try {
                controller.showPaneWithText("Game over");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            FrontendUtils.delay(1000, () -> {
                controller.goToOpening();
            });
        });
    }

}