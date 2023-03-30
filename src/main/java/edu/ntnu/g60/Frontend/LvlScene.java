package edu.ntnu.g60.frontend;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.ntnu.g60.Game;
import edu.ntnu.g60.Passage;
import javafx.concurrent.Task;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class LvlScene {
    static Stage stage = ApplicationFront.getStage();

    public static void scene(Game game) throws MalformedURLException{
        MediaPlayer mumble = ApplicationObjects.newSound("mumble");
        try {
            stage.setScene(LoadingScene.scene());
        } catch (FileNotFoundException e1) {
            
            e1.printStackTrace();
        }
        
        String text = game.getCurrentPassage().getContent();
        int braceIndexx = text.indexOf('{');
        String output = text.substring(braceIndexx);

        String[] passages = output.split("\\n");

        String[] types = new String[passages.length];
        String[] contents = new String[passages.length];

        for (int i = 0; i < passages.length; i++) {
            int braceIndex = passages[i].indexOf('{');
            if (braceIndex >= 0) {
                types[i] = passages[i].substring(braceIndex, braceIndex + 3);
                contents[i] = passages[i].substring(braceIndex + 3);
            }
        }

        delay(2000, () -> {
            try {
                stage.setScene(FirstScene.scene(game.getCurrentPassage().getTitle()));
                delay(2000, () -> {
                    try {
                        ApplicationFront.setTextLine(0);
                        stage.setScene(NewTalkingScene.scene(types, contents, game));
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
