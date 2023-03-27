package edu.ntnu.g60.frontend;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import edu.ntnu.g60.Passage;
import javafx.concurrent.Task;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class LvlScene {
    static Stage stage = ApplicationFront.getStage();

    public static void scene(int lvl) throws MalformedURLException{
        MediaPlayer mumble = ApplicationObjects.newSound("mumble");
        try {
            stage.setScene(LoadingScene.scene());
        } catch (FileNotFoundException e1) {
            
            e1.printStackTrace();
        }
        ApplicationFront.setLineNumber(1);
        String type = Passage.getTypeOfTextAtLineNumber(ApplicationFront.getLineNumber());
        ApplicationFront.setAmountOfLines(Passage.getAmountOfTextLines());

        delay(2000, () -> {
            try {
                stage.setScene(FirstScene.scene(lvl));
                delay(2000, () -> {
                    try {
                        stage.setScene(NewTalkingScene.scene(type, lvl));
                        mumble.play();
                    } catch (FileNotFoundException | MalformedURLException e1) {
                        
                        e1.printStackTrace();
                    }
                });
            } catch (FileNotFoundException e1) {
                
                e1.printStackTrace();
            }
        });
    }


    public static void delay(long millis, Runnable continuation){
        Task<Void> sleeper = new Task<Void>(){
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }

}
