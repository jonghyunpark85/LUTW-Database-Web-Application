package com.lutw.reports.controller;

import com.lutw.reports.service.SummaryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class for the summary page
 * 
 * @author Justin Heinrichs
 * @version 1.0
 */
@Controller
@RequestMapping("/summary")
public class SummaryController {
    
    @Autowired
    private SummaryService service;

    Logger logger = LoggerFactory.getLogger(SummaryService.class);

    /**
     * Request mapping for /summary that shows report summary information
     * @param model Model object that holds attributes for the view
     * @return the name of the summary view
     */
    @RequestMapping("")
    private String getSummaryView(Model model) {
        model.addAttribute("reportSummaries", service.getSimpleSummaries());
        model.addAttribute("questionSummaries", service.getQuestionSummaries());
        return "summary";
    }
}
