package edu.ntnu.g60.views.Animations;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import edu.ntnu.g60.controllers.AnimationController;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.utils.frontend.FrontendUtils;
import javafx.scene.layout.StackPane;


public class NextLevelAnimation extends StackPane{

    private static AnimationController controller;

    static {
        controller = new AnimationController();
    }

    //rename methods
    public static void animation(Passage passage) throws MalformedURLException, FileNotFoundException{
        controller.showPaneWithText("Advancing");
        FrontendUtils.delay(1000, () -> {
            try {
                controller.showPaneWithText(passage.getTitle());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            FrontendUtils.delay(1500, () -> {
                controller.beginPassage(passage);
            });
        });
    }

}