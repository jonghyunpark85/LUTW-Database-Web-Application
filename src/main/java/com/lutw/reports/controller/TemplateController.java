package com.lutw.reports.controller;

import com.lutw.reports.dto.TemplateDto;
import com.lutw.reports.service.TemplateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller class for the form template page that manages requests
 * related to creating and updating form template questions
 * 
 * @author Justin Heinrichs
 * @version 1.0
 */
@Controller
public class TemplateController {

    @Autowired
    private TemplateService service;

    /**
     * Method that adds the list of question types to the model on each request
     * @param model Model object that holds attributes for the view
     */
    @ModelAttribute
    public void addQuestionTypes(Model model) {
        model.addAttribute("questionTypes", service.getQuestionTypes());
    }
    
    /**
     * Get mapping for the templates endpoing that returns the view for the Form
     * template viewer/editor
     * @param model Model object that holds attributes for the view
     * @return name of the template viewer/editor view
     */
    @GetMapping("/templates")
    public String getTemplateView(Model model) {
        model.addAttribute("template", service.getTemplate());
        return "templates";
    }

    /** Post mapping for the templates endpoints that overwrites the current form
     * template from a mapped form submission
     * 
     * @param model Model object that holds attributes for the view
     * @param template the mapped form submission holding the new questions
     * @return redirect to the template viewer/editor view
     */
    @PostMapping("/templates")
    public String saveTemplate(Model model, TemplateDto template) {
        service.updateTemplate(template);
        return "redirect:/templates";
    }

    /**
     * Get mapping at api/questions that returns a form template object holding a list of
     * all questions assigned to that template
     * @return a form template object holding a list of questions in JSON
     */
    @GetMapping("/api/questions")
    public @ResponseBody TemplateDto getQuestions() {
        return service.getTemplate();
    }

    /**
     * Post mapping at api/questions that accepts a template holding a list of questions and 
     * updates that template in the database
     * @param template the template object holding the updated questions
     * @return HttpStatus.OK if the update was successful
     */
    @RequestMapping(value="/api/questions", method = RequestMethod.POST, consumes = "application/json" )
    public @ResponseBody ResponseEntity<String> postQuestions(@RequestBody TemplateDto template) {
        service.updateTemplate(template);
        return new ResponseEntity<>("Template Saved", HttpStatus.OK);
    }
}
