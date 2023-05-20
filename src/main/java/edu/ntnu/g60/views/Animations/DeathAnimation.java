package edu.ntnu.g60.views.Animations;

import java.io.FileNotFoundException;

import edu.ntnu.g60.controllers.AnimationController;
import edu.ntnu.g60.utils.frontend.FrontendUtils;

/**
 * The DeathAnimation class provides a static method for playing the death animation
 * when the game is over.
*/
public class DeathAnimation {

    private static AnimationController controller;

    /**
     * Static block to initialize the AnimationController.
    */
    static {
        controller = new AnimationController();
    }

    /**
     * Plays the death animation when the game is over.
     * 
     *      @throws FileNotFoundException if the required resource is not found
    */
    public static void animation() throws FileNotFoundException{
        controller.showPaneWithText("GAME OVER");
        FrontendUtils.delay(1500, () -> {
            controller.goToOpening();
        });
    }
}
