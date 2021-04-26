package com.lutw.reports.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lutw.reports.entity.Question;
import com.lutw.reports.entity.Report;
import com.lutw.reports.entity.Response;
import com.lutw.reports.entity.Template;
import com.lutw.reports.entity.repository.TemplateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Utility class for mapping Form data transfer objects from Report domain objects
 * 
 * @author Justin Heinrichs
 * @version 1.0
 */
@Component
public class FormMapper {

    private static TemplateRepository templateRepository;

    @Autowired
    public FormMapper(TemplateRepository templateRepository) {
        FormMapper.templateRepository = templateRepository;
    }
    
    /**
     * Maps a form data transfer object from a persisted report
     * 
     * @param report the form's report
     * @return a FormDto
     * @throws NullPointerException thrown when there is no template in the databse
     */
    public static FormDto toFormDto(Report report) throws NullPointerException {   
        
        // Looking for the first id because the plan was to have more than one
        Template template = templateRepository.findById(1) 
            .orElseThrow(() -> new NullPointerException("No form template in database"));

        Map<Question, Response> responses = report.getResponses();
        List<Question> questions = template.getQuestions();

        // Create a field from every question using the report's response if it exists
        ArrayList<FieldDto> fields = new ArrayList<>();
        for (Question question : questions) {
            if (responses.containsKey(question)) {
                fields.add(FieldMapper.toFieldDto(question, responses.get(question)));
            } else {
                fields.add(FieldMapper.toFieldDto(report, question));
            }
        }

        var form = new FormDto();
        form.setReport(report);
        form.setFields(fields);

        return form;
    }
}
