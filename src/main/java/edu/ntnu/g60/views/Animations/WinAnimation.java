package edu.ntnu.g60.views.Animations;

import java.io.FileNotFoundException;

import edu.ntnu.g60.controllers.AnimationController;
import edu.ntnu.g60.utils.frontend.FrontendUtils;

/**
 * The WinAnimation class provides a static method for playing the win animation
 * when the game is won.
 */
public class WinAnimation {

    private static AnimationController controller;

    /**
     * Static block to initialize the AnimationController.
     */
    static {
        controller = new AnimationController();
    }

    /**
     * Plays the win animation when the game is won.
     * 
     * @throws FileNotFoundException if the required resource is not found
     */
    public static void animation() throws FileNotFoundException{
        controller.showPaneWithText("You Won!");
        FrontendUtils.delay(2000, () -> {
            controller.goToOpening();
        });
    }
}