package edu.ntnu.g60.models.actions;

import edu.ntnu.g60.entities.ActionEntity;

public class ActionFactory {

    public static Action createAction(ActionEntity actionEntity) {
        String type = actionEntity.getType();
        Object value = actionEntity.getValue();

        switch (type.toLowerCase()) {
            case "health":
                if (value instanceof Integer) {
                    return new HealthAction((int) value);
                }
                break;
            case "gold":
                if (value instanceof Integer) {
                    return new GoldAction((int) value);
                }
                break;
            case "score":
                if (value instanceof Integer) {
                    return new ScoreAction((int) value);
                }
                break;
            case "inventory":
                if (value instanceof String) {
                    return new InventoryAction((String) value);
                }
                break;
            default:
                break;
        }
        throw new IllegalArgumentException("Invalid action type or value: " + type + ", " + value);
    }
}