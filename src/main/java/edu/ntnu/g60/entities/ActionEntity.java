package edu.ntnu.g60.entities;

public class ActionEntity {
    private String type;
    private Object value; // Change the field type to Object

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() { // Update the return type to Object
        return value;
    }

    public void setValue(Object value) { // Update the parameter type to Object
        this.value = value;
    }
}