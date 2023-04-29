package edu.ntnu.g60.frontend;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import edu.ntnu.g60.models.Game;
import edu.ntnu.g60.models.Passage;


public class LvlSwitchAnimation {

    public static void animation(Game game, Passage passage) throws MalformedURLException{
        firstFrame();
        GameApp.delay(2000, () -> {
            secondFrame(passage.getTitle());
            GameApp.delay(2000, () -> {
                thirdFrame(types(passages(passage)), contents(passages(passage)), game, passage);
            });
        });
    }

    public static void firstFrame(){
        try {
            GameApp.switchToScene(LoadingScene.scene());
        } catch (FileNotFoundException e1) {
            
            e1.printStackTrace();
        }
    }

    public static void secondFrame(String passageTitle){
        try {
            GameApp.switchToScene(FirstScene.scene(passageTitle));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public static void thirdFrame(String[] types, String[] contents, Game game, Passage passage){
        try {
            GameApp.setTextLine(0);
            GameApp.switchToScene(NewTalkingScene.scene(types, contents, game, passage));
            
        } catch (FileNotFoundException | MalformedURLException e1) {
            
            e1.printStackTrace();
        }
    }

    //move?
    public static String[] passages(Passage passage){
        String text = passage.getContent();
        int braceIndexx = text.indexOf('{');
        String output = text.substring(braceIndexx);
        String[] passages = output.split("\\n");
        return passages;
    }

    //move?
    public static String[] types(String[] passages){
        String[] types = new String[passages.length];
        for (int i = 0; i < passages.length; i++) {
            int braceIndex = passages[i].indexOf('{');
            if (braceIndex >= 0) {
                types[i] = passages[i].substring(braceIndex, braceIndex + 3);
            }
        }
        return types;
    }

    //move?
    public static String[] contents(String[] passages){
        String[] contents = new String[passages.length];
        for (int i = 0; i < passages.length; i++) {
            int braceIndex = passages[i].indexOf('{');
            if (braceIndex >= 0) {
                contents[i] = passages[i].substring(braceIndex + 3);
            }
        }
        return contents;
    }



}
