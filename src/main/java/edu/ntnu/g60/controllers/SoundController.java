package edu.ntnu.g60.controllers;

import java.net.MalformedURLException;

import edu.ntnu.g60.views.ViewObjects;
import javafx.scene.media.MediaPlayer;

public class SoundController {
    
    public static MediaPlayer sound;
    

    public static void playSound(String soundName) throws MalformedURLException{
        sound = ViewObjects.newSound(soundName);
        sound.play();
    }

    public static void stopSound() throws MalformedURLException{
        sound.stop();
    }

}
