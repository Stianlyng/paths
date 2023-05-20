package edu.ntnu.g60.utils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DefaultValues {
    
    /**
     * The default paths:
     */
    public static final URL RESOURCE_PATH = DefaultValues.class.getClassLoader().getResource("");
    public static final URL STORY_PATH = DefaultValues.class.getClassLoader().getResource("stories/");
    public static final URL SAVE_PATH = DefaultValues.class.getClassLoader().getResource("saves/");
    
    // Images:
    public static final URL IMAGE_PATH = DefaultValues.class.getClassLoader().getResource("images/");
    
    /**
     * The default passage image names:
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