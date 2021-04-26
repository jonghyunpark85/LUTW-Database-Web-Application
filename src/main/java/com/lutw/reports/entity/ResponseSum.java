package com.lutw.reports.entity;

/**
 * Utility class used to create objects from the tuples generated from
 * native queries
 * 
 * @author Justin Heinrichs
 * @version 1.0
 */
public class ResponseSum {

    private final Integer id;
    private final String value;
    private final String description;

    public ResponseSum(int id, String description, String value) {
        this.id = id;
        this.description = description;
        this.value = value;
    }

    public ResponseSum(Integer id, String description, Long value) {
        this(id, description, String.valueOf(value));
    }

    public ResponseSum(Integer id, String description, Double value) {
        this(id, description, String.valueOf(value));
    }

    public Integer getId() {
        return this.id;
    }

    public String getValue() {
        return value;
    }
    
    public String getDescription() {
        return this.description;
    }

}
