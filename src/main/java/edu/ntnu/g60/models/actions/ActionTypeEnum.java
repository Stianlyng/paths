package edu.ntnu.g60.models.actions;

import edu.ntnu.g60.exceptions.model.actions.InvalidActionTypeException;

/**
 * The enum is used by ActionFactory to create the corresponding action object based on the type and value in the JSON file.
 * In this case, the string is fetched from the JSON file in the story parser.
 *
 * @see ActionFactory and StoryParser
 * @author Stian Lyng
 */
enum ActionTypeEnum {
    HEALTH("health"),
    GOLD("gold"),
    SCORE("score"),
    INVENTORY("inventory");

    private final String type;

    /**
     * Creates a new ActionTypeEnum.
     * 
     * @param type The type of action.
     */
    ActionTypeEnum(String type) {
        this.type = type;
    }

    /**
     * Returns the ActionTypeEnum that corresponds to the specified string.
     * 
     * @param type The type of the action.
     * @return The ActionTypeEnum that corresponds to the specified string.
     * @throws InvalidActionTypeException if the specified string does not correspond to a valid ActionTypeEnum.
     */
    public static ActionTypeEnum fromString(String type) throws InvalidActionTypeException {
        for (ActionTypeEnum actionType : ActionTypeEnum.values()) {
            if (actionType.type.equalsIgnoreCase(type)) {
                return actionType;
            }
        }
        throw new InvalidActionTypeException("Invalid action type: " + type);
    }
}