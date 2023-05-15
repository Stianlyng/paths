package edu.ntnu.g60.controllers;

import java.net.MalformedURLException;

import edu.ntnu.g60.views.ViewObjects;
import javafx.scene.media.MediaPlayer;

/**
* The SoundController class manages the playback of sound effects and background music in the application.
* It provides methods to play, stop, and adjust the volume of the sound and music.
*/
public class SoundController {
    
    /**
    * The MediaPlayer object for the sound effect.
    */
    public static MediaPlayer sound;

    /**
    * The MediaPlayer object for the background music.
    */
    public static MediaPlayer music;

    /**
    * The volume level for the sound and music.
    */
    public static double volume;
    
    /**
    * Plays the specified background music.
    * 
    * @param musicName the name of the music file to be played
    * @throws MalformedURLException if the URL of the music file is malformed
    */
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

    /**
    * Sets the background music to the specified MediaPlayer object.
    * 
    * @param newMusic the new MediaPlayer object for the music
    */
    public static void setMusic(MediaPlayer newMusic){
        music = newMusic;
    }

    /**
    * Stops the playback of the background music.
    * 
    * @throws MalformedURLException if the URL of the music file is malformed
    */
    public static void stopMusic() throws MalformedURLException{
        music.stop();
    }

    /**
    * Plays the specified sound effect.
    * 
    * @param soundName the name of the sound effect file to be played
    * @throws MalformedURLException if the URL of the sound effect file is malformed
    */
    public static void playSound(String soundName) throws MalformedURLException{
        setSound(ViewObjects.newSound(soundName));
        sound.setVolume(volume * 5);
        sound.play();
    }

    /**
    * Sets the sound effect to the specified MediaPlayer object.
    * 
    * @param newSound the new MediaPlayer object for the sound effect
    */
    public static void setSound(MediaPlayer newSound){
        sound = newSound;
    }

    /**
    * Stops the playback of the sound effect.
    * 
    * @throws MalformedURLException if the URL of the sound effect file is malformed
    */
    public static void stopSound() throws MalformedURLException{
        sound.stop();
    }

    /**
    * Sets the volume level for the application.
    * 
    * @param newVolume the new volume level
    */
    public static void setApplicationVolume(double newVolume) {
        volume = newVolume;
        music.setVolume(newVolume / 2);
    }

}
