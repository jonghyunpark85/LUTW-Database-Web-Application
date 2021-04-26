package com.lutw.reports.dto;

import com.lutw.reports.entity.Question;
import com.lutw.reports.entity.Report;
import com.lutw.reports.entity.Response;
import com.lutw.reports.entity.repository.ResponseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Utility class that maps Report and Question domain objects into a Field data transfer object
 * 
 * @author Justin Heinrichs
 * @version 1.0
 */
@Component
public class FieldMapper {

    private static ResponseRepository reponseRepository;

    @Autowired
    public FieldMapper(ResponseRepository responseRepository) {
        FieldMapper.reponseRepository = responseRepository;
    }

    /**
     * Creates a Field from a question that doesn't have a response in the current report
     * @param report the field's report
     * @param question the field's question
     * @return a field object for the report and question
     */
    public static FieldDto toFieldDto(Report report, Question question) {
        var response = new Response();
        response.setQuestion(question);
        response.setReport(report);

        return FieldMapper.toFieldDto(question, response);
        // return FieldMapper.toFieldDto(question, reponseRepository.save(response));
    }
    
    /**
     * Creates a Field from a question and response
     * @param question the field's question
     * @param response the field's response
     * @return a field object
     * @throws IllegalArgumentException when the question has an invalid type
     */
    public static FieldDto toFieldDto(Question question, Response response) throws IllegalArgumentException {
        var field = new FieldDto();
        field.setQuestionID(question.getId());
        field.setPrompt(question.getPrompt());
        field.setSubtitle(question.getSubtitle());
        field.setType(question.getType().toString());
        field.setResponseID(response.getId());

        // Get the input depending on the question type, initialize it if its null
        switch(question.getType()) {
        case NUMBER:
            if (response.getNumberInput() != null) {
                field.setNumberInput(response.getNumberInput());
            } else {
                field.setNumberInput(0);
            }
            break;
        case FLOAT:
            if (response.getFloatInput() != null) {
                field.setFloatInput(response.getFloatInput());
            } else {
                field.setFloatInput(0.0);
            }
            break;
        case TEXT:
        case STRING:
            field.setInput(response.getInput()); // Intentional fall through
            break;
        default:
            throw new IllegalArgumentException("Creating a field with an invalid type");
        }

        return field;
    }
}
