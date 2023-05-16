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
    public static void playMusic(String musicName) {
        try {
            setMusic(ViewObjects.newSound(musicName));
            if (music != null) {
                music.setVolume(volume / 2);

                music.setOnEndOfMedia(() -> {
                    music.seek(music.getStartTime());
                    music.play();
                });

                music.play();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
    * Sets the background music to the specified MediaPlayer object.
    * 
    * @param newMusic the new MediaPlayer object for the music
    */
    public static void setMusic(MediaPlayer newMusic){
        if (music != null) {
            music.dispose();
        }
        music = newMusic;
    }

    /**
    * Stops the playback of the background music.
    * 
    * @throws MalformedURLException if the URL of the music file is malformed
    */
    public static void stopMusic() throws MalformedURLException{
        if (music != null) {
            music.stop();
        }
    }

    /**
    * Plays the specified sound effect.
    * 
    * @param soundName the name of the sound effect file to be played
    * @throws MalformedURLException if the URL of the sound effect file is malformed
    */
    public static void playSound(String soundName) {
        try {
            setSound(ViewObjects.newSound(soundName));
            if (sound != null) {
                sound.setVolume(volume * 5);
                sound.play();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
    * Sets the sound effect to the specified MediaPlayer object.
    * 
    * @param newSound the new MediaPlayer object for the sound effect
    */
    public static void setSound(MediaPlayer newSound) {
        if (sound != null) {
            sound.dispose();
        }
        sound = newSound;
    }

    /**
    * Stops the playback of the sound effect.
    * 
    * @throws MalformedURLException if the URL of the sound effect file is malformed
    */
    public static void stopSound() throws MalformedURLException{
        if (sound != null) {
            sound.stop();
        }
    }

    /**
    * Sets the volume level for the application.
    * 
    * @param newVolume the new volume level
    */
    public static void setApplicationVolume(double newVolume) {
        if (newVolume < 0.0 || newVolume > 1.0) {
            throw new IllegalArgumentException("Volume must be between 0.0 and 1.0");
        }
        volume = newVolume;
        if (music != null) {
            music.setVolume(newVolume / 2);
        }
    }

}
