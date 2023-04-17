package edu.ntnu.g60.frontend;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import edu.ntnu.g60.Game;
import edu.ntnu.g60.Passage;
import javafx.concurrent.Task;
import javafx.scene.media.MediaPlayer;


//DOES STUFF in between lvl's
public class LvlScene {

    public static void scene(Game game, Passage passage) throws MalformedURLException{
        MediaPlayer mumble = ApplicationObjects.newSound("mumble");
        try {
            ApplicationFront.switchToScene(LoadingScene.scene());
        } catch (FileNotFoundException e1) {
            
            e1.printStackTrace();
        }
        
        String text = passage.getContent();
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
                ApplicationFront.switchToScene(FirstScene.scene(passage.getTitle()));
                delay(2000, () -> {
                    try {
                        ApplicationFront.setTextLine(0);
                        ApplicationFront.switchToScene(NewTalkingScene.scene(types, contents, game, passage));
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
                    e.printStackTrace();
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }

}
