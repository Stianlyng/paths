package edu.ntnu.g60.utils;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class DefaultValues {

    
    /**
     * PATHS:
     */
    public static final Path RESOURCE_PATH = Paths.get("src/main/resources/");
    public static final Path STORY_PATH = RESOURCE_PATH.resolve("stories/");
    public static final Path SAVE_PATH = RESOURCE_PATH.resolve("saves/");
    // Images:
    public static final Path IMAGE_PATH = RESOURCE_PATH.resolve("images/");
    public static final Path ICON_PATH = IMAGE_PATH.resolve("icons/");
    
    /**
     * PASSAGE:
     */
    public static final String PLAYER_IMAGE = "beer.png"; 
    public static final String ENEMY_IMAGE = "beer.png"; 
    public static final String BACKGROUND_IMAGE = "Background1.png"; 
    
    
    /**
     * The default minimum goals:
     */
    public static final int MINIMUM_GOLD = 0;
    public static final int MINIMUM_HEALTH = 0;
    public static final int MINIMUM_SCORE = 0;
    public static final List<String> MINIMUM_INVENTORY = new ArrayList<>(); 
}