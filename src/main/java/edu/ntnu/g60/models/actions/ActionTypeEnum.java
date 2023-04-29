package edu.ntnu.g60.models.actions;


enum ActionTypeEnum {
    HEALTH("health"),
    GOLD("gold"),
    SCORE("score"),
    INVENTORY("inventory");

    private final String type;

    ActionTypeEnum(String type) {
        this.type = type;
    }

    public static ActionTypeEnum fromString(String type) {
        for (ActionTypeEnum actionType : ActionTypeEnum.values()) {
            if (actionType.type.equalsIgnoreCase(type)) {
                return actionType;
            }
        }
        throw new IllegalArgumentException("Invalid action type: " + type);
    }
}