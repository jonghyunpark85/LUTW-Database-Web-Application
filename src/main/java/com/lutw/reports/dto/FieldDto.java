package com.lutw.reports.dto;

/**
 * Data Transfer object for form fields populated from the database
 * created from question and response objects
 * 
 * @authoer Justin Heinrichs
 * @version 1.0
 */
public class FieldDto {
    
    private int questionID;
    private int responseID;
    private String prompt;
    private String subtitle;
    private String type;
    private String input;
    private int numberInput;
    private double floatInput;

    public FieldDto() {}

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getResponseID() {
        return responseID;
    }

    public void setResponseID(int responseID) {
        this.responseID = responseID;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int getNumberInput() {
        return numberInput;
    }

    public void setNumberInput(int numberInput) {
        this.numberInput = numberInput;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getFloatInput() {
        return floatInput;
    }

    public void setFloatInput(double floatInput) {
        this.floatInput = floatInput;
    }

}
