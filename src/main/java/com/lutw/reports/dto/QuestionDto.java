package com.lutw.reports.dto;

/**
 * Data transfer objects from Question domain objects. Used in the 
 * form template editing page
 * 
 * @author Justin Heinrichs
 * @version 1.0
 */
public class QuestionDto {
    
    private int questionId;
    private String prompt;
    private String subtitle;
    private String type;
    private String aggregate;

    public QuestionDto() {}

    public int getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAggregate() {
        return aggregate;
    }

    public void setAggregate(String aggregate) {
        this.aggregate = aggregate;
    }

    @Override
    public String toString() {
        return "[Question " + questionId + "] prompt : " + prompt + ", type : " + type; 
    }

}
