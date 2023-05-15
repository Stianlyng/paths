package edu.ntnu.g60.controllers;

import java.net.MalformedURLException;

import edu.ntnu.g60.views.ViewObjects;
import javafx.scene.media.MediaPlayer;

public class SoundController {
    
    public static MediaPlayer sound;
    public static MediaPlayer music;
    public static double volume;
    

    public static void playMusic(String musicName) throws MalformedURLException{
        setMusic(ViewObjects.newSound(musicName));
        music.setVolume(volume / 2);

        music.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                music.seek(music.getStartTime());
                music.play();
            }
        });

        music.play();
    }

    public static void setMusic(MediaPlayer newMusic){
        music = newMusic;
    }

    public static void stopMusic() throws MalformedURLException{
        music.stop();
    }

    public static void playSound(String soundName) throws MalformedURLException{
        setSound(ViewObjects.newSound(soundName));
        sound.setVolume(volume * 5);
        sound.play();
    }

    public static void setSound(MediaPlayer newSound){
        sound = newSound;
    }

    public static void stopSound() throws MalformedURLException{
        sound.stop();
    }

    public static void setApplicationVolume(double newVolume) {
        volume = newVolume;
        music.setVolume(newVolume / 2);
    }

}
