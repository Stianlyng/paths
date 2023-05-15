package edu.ntnu.g60.views.Animations;

import java.net.MalformedURLException;
import edu.ntnu.g60.controllers.AnimationController;
import edu.ntnu.g60.controllers.GameController;
import javafx.scene.layout.StackPane;


public class NextLevelAnimation extends StackPane{

    private static AnimationController controller;

    static {
        controller = new AnimationController();
    }

    public static void animation() throws MalformedURLException{
        controller.firstFrame();
        GameController.delay(2000, () -> {
            controller.secondFrame();
            GameController.delay(2000, () -> {
                controller.thirdFrame();
            });
        });
    }

}
