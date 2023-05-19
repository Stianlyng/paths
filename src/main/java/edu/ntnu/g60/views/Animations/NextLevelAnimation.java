package edu.ntnu.g60.views.Animations;

import java.net.MalformedURLException;
import edu.ntnu.g60.controllers.AnimationController;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.utils.FrontendUtils;
import javafx.scene.layout.StackPane;


public class NextLevelAnimation extends StackPane{

    private static AnimationController controller;

    static {
        controller = new AnimationController();
    }

    //rename methods
    public static void animation(Passage passage) throws MalformedURLException{
        controller.firstFrame();
        FrontendUtils.delay(2000, () -> {
            controller.secondFrame(passage.getTitle());
            FrontendUtils.delay(2000, () -> {
                controller.thirdFrame(passage);
            });
        });
    }

}