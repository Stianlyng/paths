package edu.ntnu.g60.controllers;

import java.net.MalformedURLException;

import edu.ntnu.g60.views.ViewObjects;
import javafx.scene.media.MediaPlayer;

public class SoundController {
    
    public static MediaPlayer sound;
    

    public static void playSound(String soundName) throws MalformedURLException{
        setSound(ViewObjects.newSound(soundName));
        sound.play();
    }

    public static void setSound(MediaPlayer newSound){
        sound = newSound;
    }

    public static MediaPlayer getSound(){
        return sound;
    }

    //TODO: make this connected to sound over //no work
    public static void stopSound() throws MalformedURLException{
    getSound().stop();
    }

}
