package edu.ntnu.g60.views.Animations;

import java.io.FileNotFoundException;

import edu.ntnu.g60.controllers.AnimationController;
import edu.ntnu.g60.utils.frontend.FrontendUtils;

public class WinAnimation {

    private static AnimationController controller;

    static {
        controller = new AnimationController();
    }

    public static void animation() throws FileNotFoundException{
        controller.showPaneWithText("You Won!");
        FrontendUtils.delay(2000, () -> {
            controller.goToOpening();
        });
    }
}