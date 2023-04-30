package edu.ntnu.g60.models.actions;

import edu.ntnu.g60.exceptions.model.actions.InvalidActionTypeException;

enum ActionTypeEnum {
    HEALTH("health"),
    GOLD("gold"),
    SCORE("score"),
    INVENTORY("inventory");

    private final String type;

    ActionTypeEnum(String type) {
        this.type = type;
    }

    public static ActionTypeEnum fromString(String type) throws InvalidActionTypeException {
        for (ActionTypeEnum actionType : ActionTypeEnum.values()) {
            if (actionType.type.equalsIgnoreCase(type)) {
                return actionType;
            }
        }
        throw new InvalidActionTypeException("Invalid action type: " + type);
    }
}