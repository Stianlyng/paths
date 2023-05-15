package edu.ntnu.g60.controllers;

/**
 * The ControllerValues class represents a set of controller values for the game.
 * It provides methods to set and get the game file.
 */
public class ControllerValues {
    
    /**
    * The game file path.
    */
    public static String GAME_FILE;

    /**
    * Sets the game file path.
    * 
    * @param file the path of the game file
    */
    public static void setGameFile(String file){
        GAME_FILE = file;
    }

    /**
    * Retrieves the game file path.
    * 
    * @return the path of the game file
    */
    public static String getGameFile(){
        return GAME_FILE;
    }

}
