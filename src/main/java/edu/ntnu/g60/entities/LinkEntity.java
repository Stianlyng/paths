package edu.ntnu.g60.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LinkEntity {

    private String text;
    
    private String reference;
    
    @JsonProperty("actions") 
    private List<ActionEntity> actions;

    // Getters and setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
    
    public List<ActionEntity> getActions() {
        return actions == null ? new ArrayList<>() : actions;
    }

    public void setActions(List<ActionEntity> actions){
        this.actions = actions;
    }
}