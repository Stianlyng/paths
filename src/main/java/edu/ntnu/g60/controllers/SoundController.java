package edu.ntnu.g60.controllers;

import edu.ntnu.g60.exceptions.MusicControllerException;
import edu.ntnu.g60.views.ViewObjects;
import java.net.MalformedURLException;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;

/**
 * The SoundController class manages the playback of sound effects and background music in the application.
 * It provides methods to play, stop, and adjust the volume of the sound and music.
 * @author olav sie
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
   * @throws MusicControllerExeption if the URL of the music file is malformed
   */
  public static void playMusic(String musicName) throws MusicControllerException {
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
      throw new MusicControllerException("Malformed URL for music file: " + musicName, e);
    } catch (MediaException e) {
      throw new MusicControllerException("Error creating media player for music: " + musicName, e);
    }
  }

  /**
   * Sets the background music to the specified MediaPlayer object.
   *
   * @param newMusic the new MediaPlayer object for the music
   */
  public static void setMusic(MediaPlayer newMusic) {
    if (music != null) {
      music.dispose();
    }
    music = newMusic;
  }

  /**
   * Stops the playback of the background music.
   *
   * @throws MusicControllerException  if the music cannot be stopped
   */
  public static void stopMusic() throws MusicControllerException {
    if (music != null) {
      try {
        music.stop();
      } catch (Exception e) {
        throw new MusicControllerException("Error while stopping music", e);
      }
    }
  }

  /**
   * Plays the specified sound effect.
   *
   * @param soundName the name of the sound effect file to be played
   * @throws MusicControllerException if the URL of the sound effect file is malformed
   */
  public static void playSound(String soundName) throws MusicControllerException {
    try {
      setSound(ViewObjects.newSound(soundName));
      if (sound != null) {
        sound.setVolume(volume * 5);
        sound.play();
      }
    } catch (MalformedURLException e) {
      throw new MusicControllerException("Malformed URL for sound file: " + soundName, e);
    } catch (MediaException e) {
      throw new MusicControllerException("Error creating media player for music: " + soundName, e);
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
   * @throws MusicControllerExceptioin if there is an error while stopping the sound effect
   */
  public static void stopSound() throws MusicControllerException {
    if (sound != null) {
      try {
        sound.stop();
      } catch (Exception e) {
        throw new MusicControllerException("Error while stopping sound", e);
      }
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
