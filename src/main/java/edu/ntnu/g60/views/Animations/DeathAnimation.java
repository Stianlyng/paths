package edu.ntnu.g60.views.Animations;

import java.io.FileNotFoundException;
import edu.ntnu.g60.controllers.AnimationController;
import edu.ntnu.g60.controllers.GameController;

public class DeathAnimation {

    private static AnimationController controller;

    public static void animation() throws FileNotFoundException{
        controller.deathFirstFrame();
        GameController.delay(3000, () -> {
            controller.deathSecondFrame();
        });
    }
}
