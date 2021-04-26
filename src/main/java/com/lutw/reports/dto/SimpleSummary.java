package com.lutw.reports.dto;

/**
 * Data transfer object class for a simple value:description object
 * 
 * @author Justin Heinrichs
 * @version 1.0
 */
public final class SimpleSummary {
    
    private final String value;
    private final String description;

    public SimpleSummary(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public SimpleSummary(long value, String description) {
        this(String.valueOf(value), description);
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

}
