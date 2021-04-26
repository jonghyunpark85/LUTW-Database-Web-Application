package com.lutw.reports.dto;

/**
 * Data transfer object for the question summary objects
 * 
 * @authoer Justin Heinrichs
 * @version 1.0
 */
public final class QuestionSummary {
    
    private final int id;
    private final String value; // sum total of responses
    private final String description;
    private int count; // number of responses in the total

    public QuestionSummary(int id, String value, String description) {
        this.id = id;
        this.value = value;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}