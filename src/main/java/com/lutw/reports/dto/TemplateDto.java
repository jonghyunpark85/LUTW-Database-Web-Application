package com.lutw.reports.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Data transfer object that holds a list of questions for the 
 * form template editing page
 * 
 * @author Justin Heinrichs
 * @version 1.0
 */
public class TemplateDto {
    
    private int templateId;
    private List<QuestionDto> questions = new ArrayList<QuestionDto>();

    public TemplateDto() {}

    public TemplateDto(int templateId, List<QuestionDto> questions) {
        this.templateId = templateId;
        this.questions = questions;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }
    
    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }
}
