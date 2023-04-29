package edu.ntnu.g60.models.actions;

import java.util.Optional;

import edu.ntnu.g60.entities.ActionEntity;

public class ActionFactory {

    public static Action createAction(ActionEntity actionEntity) {
        ActionTypeEnum type = ActionTypeEnum.fromString(actionEntity.getType());
        Object value = actionEntity.getValue();

        return createTypedAction(type, value).orElseThrow(() ->
                new IllegalArgumentException("Invalid action type or value: " + actionEntity.getType() + ", " + value));
    }

    private static Optional<Action> createTypedAction(ActionTypeEnum type, Object value) {
        switch (type) {
            case HEALTH:
                if (value instanceof Integer) {
                    return Optional.of(new HealthAction((int) value));
                }
                break;
            case GOLD:
                if (value instanceof Integer) {
                    return Optional.of(new GoldAction((int) value));
                }
                break;
            case SCORE:
                if (value instanceof Integer) {
                    return Optional.of(new ScoreAction((int) value));
                }
                break;
            case INVENTORY:
                if (value instanceof String) {
                    return Optional.of(new InventoryAction((String) value));
                }
                break;
            default:
                break;
        }
        return Optional.empty();
    }
}
