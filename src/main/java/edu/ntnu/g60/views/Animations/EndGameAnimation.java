package edu.ntnu.g60.views.Animations;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import edu.ntnu.g60.controllers.AnimationController;
import edu.ntnu.g60.utils.frontend.FrontendUtils;
import javafx.scene.layout.StackPane;


public class EndGameAnimation extends StackPane{

    private static AnimationController controller;

    static {
        controller = new AnimationController();
    }

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