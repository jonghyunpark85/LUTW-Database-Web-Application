package com.lutw.reports.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.lutw.reports.dto.QuestionSummary;
import com.lutw.reports.dto.SimpleSummary;
import com.lutw.reports.entity.ResponseSum;
import com.lutw.reports.entity.repository.ReportRepository;
import com.lutw.reports.entity.repository.ResponseRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for the summary functions of the applicatons that handles
 * bundling the query results in to data transfer objects ffor the controller
 * 
 * @author Justin Heinrichs
 * @version 1.0
 */
@Service
public class SummaryService {

    Logger logger = LoggerFactory.getLogger(SummaryService.class);

    @Autowired
    private ReportRepository reportRepository;
    
    @Autowired
    private ResponseRepository repository;

    /**
     * Gets a list of summary objects from the Report entity
     * @return list of summary objects
     */
    public List<SimpleSummary> getSimpleSummaries() {
        ArrayList<SimpleSummary> sums = new ArrayList<>();
        
        sums.add(new SimpleSummary(reportRepository.count(), "reports total"));
        sums.add(new SimpleSummary(reportRepository.countDistinctCountry(), "countries"));
        sums.add(new SimpleSummary(reportRepository.countDistinctRegion(), "regions"));

        return sums;
    }

    /**
     * Gets a list of summary objects created from questions with a numeric 
     * response type
     * @return list of QuestionSummary objects
     */
    public List<QuestionSummary> getQuestionSummaries() {
        ArrayList<QuestionSummary> sums = new ArrayList<>();

        sums.addAll(summaryListFrom(repository::getFloatSummaries));
        sums.addAll(summaryListFrom(repository::getNumberSummaries));

        return sums;
    }

    /**
     * Gets a list of QuestionSummary objects using a supplier function
     * @param supplier a function that returns a List<ResponseSum>
     * @return list of QuestionSummary mapped from the list of ResponseSum
     */
    private List<QuestionSummary> summaryListFrom(Supplier<List<ResponseSum>> supplier) {
        return supplier.get().stream()
            .map(r -> new QuestionSummary(r.getId(), r.getValue(), r.getDescription()))
            .map(this::addCount)
            .collect(Collectors.toList());
    }

    /**
     * Sets the total amount of responses for a QuestionSummary's question ID to the
     * QuestionSummary's count field
     * @param dto the QuestionSummary with a question ID to find
     * @return the original QuestionSummary with an updated count
     */
    private QuestionSummary addCount(QuestionSummary dto) {
        int count = Math.toIntExact(repository.countByQuestionId(dto.getId()));
        dto.setCount(count);
        return dto;
    }
}
