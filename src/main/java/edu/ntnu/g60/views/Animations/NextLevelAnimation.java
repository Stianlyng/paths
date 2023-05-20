package edu.ntnu.g60.views.Animations;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import edu.ntnu.g60.controllers.AnimationController;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.utils.frontend.FrontendUtils;
import javafx.scene.layout.StackPane;

/**
 * The NextLevelAnimation class extends StackPane and provides a static method for playing
 * the animation when advancing to the next "level" or passage.
 * @author olav sie
 */
public class NextLevelAnimation extends StackPane{

    private static AnimationController controller;

    /**
     * Static block to initialize the AnimationController.
     */    
    static {
        controller = new AnimationController();
    }

    /**
     * Plays the animation when advancing to the next "level" / passage.
     * 
     * @param passage the passage object representing the next "level"
     * @throws MalformedURLException if the passage contains an invalid URL
     * @throws FileNotFoundException if the required resource is not found
     */    
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