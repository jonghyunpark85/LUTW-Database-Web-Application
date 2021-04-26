package com.lutw.reports.dto;

import com.lutw.reports.entity.Question;
import com.lutw.reports.entity.QuestionType;
import com.lutw.reports.entity.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * QuestionMapper utility class for mapping Question objects to and from the
 * respective QuestionDTO for use in the controller class or to be persisted
 * 
 * @author Justin Heinrichs
 * @version 1
 */
@Component
public class QuestionMapper {

    private static QuestionRepository questionRepository;

    @Autowired
    public QuestionMapper(QuestionRepository questionRepository) {
        QuestionMapper.questionRepository = questionRepository;
    }

    /**
     * Method converts a Question object to a QuestionDTO
     * @param question question to be converted
     * @return DTO from fthe Question
     */
    public static QuestionDto toQuestionDto(Question question) {
        var dto = new QuestionDto();

        dto.setQuestionId(question.getId());
        dto.setPrompt(question.getPrompt());
        dto.setSubtitle(question.getSubtitle());
        dto.setType(question.getType().toString());
        dto.setAggregate(question.getAggregateString());
        
        return dto;
    }

    /**
     * Method that converts QuestionDTOs to persisted Question objects
     * @param dto the object to be persisted
     * @return a persisted Question object
     */
    public static Question toQuestionEntity(QuestionDto dto) {
        var entity = questionRepository.findById(dto.getQuestionId());

        // It entity exists dont change the type cause that would mess up responses
        if (entity.isPresent()) {
            var question = entity.get();
            question.setPrompt(dto.getPrompt());
            question.setSubtitle(dto.getSubtitle());
            question.setAggregateString(dto.getAggregate());
            return questionRepository.save(question);
        } else {
            var question = new Question();
            question.setPrompt(dto.getPrompt());
            question.setSubtitle(dto.getSubtitle());
            question.setType(QuestionType.valueOf(dto.getType()));
            question.setAggregateString(dto.getAggregate());
            return questionRepository.save(question);
        }
    }
}
