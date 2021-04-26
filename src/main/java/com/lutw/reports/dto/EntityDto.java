package com.lutw.reports.dto;

/**
 * Data transfer object for the report entity classes (components, beneficiaries
 * lessons learned, media).
 * 
 * @author Justin Heinrichs
 * @version 1.0
 */
public class EntityDto {
    private int id;
    private EntityType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }
}
