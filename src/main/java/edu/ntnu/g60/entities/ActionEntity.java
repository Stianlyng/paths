package edu.ntnu.g60.entities;

/**
 * This class represents the structure of an action in the JSON file.
 * It is used by the StoryParser to parse the JSON file.
 * 
 * @see StoryParser
 * @author Stian Lyng
 */
public class ActionEntity {
    private String type;
    private Object value; 

    /**
     * Sets the type of the action.
     * @param type a string representing the type of the action.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the value of the action.
     * @param value the value of the action.
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Gets the type of the action.
     * @return a string representing the type of the action.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the value of the action.
     * @return an object with the value of the action, since the value can be of different types.
     */
    public Object getValue() { 
        return value;
    }

}