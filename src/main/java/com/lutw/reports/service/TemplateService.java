package com.lutw.reports.service;

import java.util.List;
import java.util.stream.Collectors;

import com.lutw.reports.dto.QuestionDto;
import com.lutw.reports.dto.QuestionMapper;
import com.lutw.reports.dto.TemplateDto;
import com.lutw.reports.entity.QuestionType;
import com.lutw.reports.entity.repository.TemplateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class that manages the retrieval and updating of the form templates that hold
 * lists of questions
 * 
 * @author Justin Heinrichs
 * @version 1.0
 */
@Service
public class TemplateService {
    
    @Autowired
    private TemplateRepository templateRepository;

    /**
     * Method retrieves the list of valid question types
     * @return list of question types as strings
     */
    public List<String> getQuestionTypes() {
        return List.of(QuestionType.values()).stream()
            .map(QuestionType::toString)
            .collect(Collectors.toList());
    }

    /**
     * Gets the form template from the database - theres only one at this point
     * @return the form template
     * @throws NullPointerException if there is no template in the database
     */
    public TemplateDto getTemplate() throws NullPointerException {
        return new TemplateDto(1, getTemplateQuestions(1));
    }

    /**
     * Retrieves a list of questions assigned to a form template.
     * @param id the id of the form template
     * @return a list of questions assigned to the template with the given ID
     * @throws NullPointerException if the template ID is not found in the database
     */
    public List<QuestionDto> getTemplateQuestions(int id) throws NullPointerException {
        var template = templateRepository.findById(id);

        if (template.isPresent()) {
            return template.get().getQuestions().stream()
                .map(QuestionMapper::toQuestionDto)
                .collect(Collectors.toList());

        } else {
            throw new NullPointerException("No template in database");
        }
    }

    /**
     * Updates a persisted form template's list of questions in the databse using a 
     * templateDto
     * @param dto the TemplateDto with the updated list of questions
     * @throws NullPointerException if there is no template with the same ID as the DTO in the 
     * database
     */
    public void updateTemplate(TemplateDto dto) throws NullPointerException {
        var template = templateRepository.findById(dto.getTemplateId());
        
        // Convert all the template's questions to DTOs
        if (template.isPresent()) {
            var entities = dto.getQuestions().stream()
                .map(QuestionMapper::toQuestionEntity)
                .collect(Collectors.toList());

            // Necessary to update the order? Probably not the best way
            template.get().getQuestions().clear();
            template.get().setQuestions(entities);
            templateRepository.save(template.get());
        } else {
            throw new NullPointerException("No template in database");
        }
    }

}
