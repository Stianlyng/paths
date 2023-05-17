package edu.ntnu.g60.models.actions;

import java.util.Optional;

import edu.ntnu.g60.entities.ActionEntity;
import edu.ntnu.g60.exceptions.model.actions.InvalidActionTypeException;

/**
 * The ActionFactory class is responsible for creating Action objects.
 * Used when parsing JSON files in the story parser.
 * 
 * @see StoryParser
 * @author Stian Lyng
 */
public class ActionFactory {

    /**
     * Creates an Action object based on the given ActionEntity.
     *
     * @param actionEntity The ActionEntity containing the type and value for the action to be created.
     * @return An instance of an Action subclass corresponding to the type and value provided.
     * @throws InvalidActionTypeException if the type or value is invalid.
     */
    public static Action createAction(ActionEntity actionEntity) {
        ActionTypeEnum type = ActionTypeEnum.fromString(actionEntity.getType());
        Object value = actionEntity.getValue();

        return createTypedAction(type, value).orElseThrow(() ->
            new InvalidActionTypeException("Invalid action type: " + actionEntity.getType() + ", value: " + value));
    }

   /**
    * Creates an Optional Action object based on the given ActionTypeEnum and value.
    *
    * @param type  The ActionTypeEnum representing the type of action to be created.
    * @param value The value associated with the action.
    * @return An Optional containing an instance of an Action subclass if the type and value are valid, empty otherwise.
    */
    private static Optional<Action> createTypedAction(ActionTypeEnum type, Object value) {
        switch (type) {
            case HEALTH:
                if (value instanceof Integer) return Optional.of(
                    new HealthAction((int) value));
                break;
            case GOLD:
                if (value instanceof Integer) return Optional.of(
                    new GoldAction((int) value));
                break;
            case SCORE:
                if (value instanceof Integer) return Optional.of(
                    new ScoreAction((int) value));
                break;
            case INVENTORY:
                if (value instanceof String) return Optional.of(
                    new InventoryAction((String) value));
                break;
            default:
                break;
        }
        return Optional.empty();
    }
}