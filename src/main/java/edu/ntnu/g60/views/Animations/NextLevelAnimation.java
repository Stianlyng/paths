package edu.ntnu.g60.views.Animations;

import java.net.MalformedURLException;
import edu.ntnu.g60.controllers.AnimationController;
import edu.ntnu.g60.utils.FrontendUtils;
import javafx.scene.layout.StackPane;


public class NextLevelAnimation extends StackPane{

    private static AnimationController controller;

    static {
        controller = new AnimationController();
    }

    public static void animation() throws MalformedURLException{
        controller.firstFrame();
        FrontendUtils.delay(2000, () -> {
            controller.secondFrame();
            FrontendUtils.delay(2000, () -> {
                controller.thirdFrame();
            });
        });
    }

}