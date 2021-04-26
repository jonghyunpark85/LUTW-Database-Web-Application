package com.lutw.reports.controller;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import com.lutw.reports.dto.EntityDto;
import com.lutw.reports.dto.FormDto;
import com.lutw.reports.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for the fefault page and /report endpoing
 * 
 * @author Justin Heinrichs
 * @author Girrard Peter Oquendo
 * @author Dong-In Yun
 */
@Controller
public class ReportController {

    private static final String REPORTS_LIST_VIEW = "report";
    private static final String REPORT_EDIT_VIEW = "viewReport";
    private static final String INVALID_ID_VIEW = "notFound";
    
    Logger logger = LoggerFactory.getLogger(ReportController.class);
    
    @Autowired
    private ReportService service;
    
    /**
     * Request mapping for the home page
     * @param model Model object that holds attributes to be accessed by the view
     * @return name of the view for the home page
     */
    @RequestMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("reports", service.findAll());
        return "report";
    }

    /**
     * Request mapping for the reports listing page
     * @param model Model object that holds the attributes to be accessed by the view
     * @return name of the view for the reports list view
     */
    @GetMapping("/report")
    public String viewAllReports(Model model) {
        model.addAttribute("reports", service.findAll());
        return REPORTS_LIST_VIEW;
    }
    
    /**
     * Get mapping at /new that creates a new report and returns its edit page
     * @param model Model object that holds attributes to be accessed by the view
     * @return name of the report editing view
     */
    @GetMapping("/report/new")
    public String newReport(Model model) {
        model.addAttribute("report", service.createProject());
        return REPORT_EDIT_VIEW;
    }

    /**
     * Get mapping at /report that returns the edit page for a specific report ID
     * @param model Model object that holds attributes to be accessed by the view
     * @param id ID of the report usd for the view
     * @return name of the report editing view or the error page if the id isnt found
     */
    @GetMapping("/report/{id}")
    public String viewReport(Model model, @PathVariable String id) {
        var form = service.getFormById(Integer.parseInt(id));

        if (form.isEmpty()) {
            return INVALID_ID_VIEW;
        } else {
            model.addAttribute("report", form.get());
            return REPORT_EDIT_VIEW;
        }
    }
    
    /**
     * Post mapping at /report that submits and saves edited reports, and redirects
     * back to the main list page
     * @param formDto the form data Spring has mapped from the POST requst
     * @param result a result object holding binding errors from mapping the form object
     * @return a redirect to the reports list view if no binding errors, or the edit view
     * otherwise
     */
    @PostMapping(value="/report/*", params={"submit"})
    public String saveReport(@Valid FormDto formDto, BindingResult result) {
        if (result.hasErrors()) {
            return REPORT_EDIT_VIEW;
        } else {
            service.saveForm(formDto);
            return "redirect:/report/";
        }
    }

    /**
     * Get mapping at /report/delete that deletes a report of the given ID from the database
     * @param id the ID of the report that should be deleted
     * @param model Model object that holds attributes to be accessed by the view
     * @return redirect to the reports view
     */
    @GetMapping("/report/delete/{id}")
    public String deleteReport(@PathVariable String id, Model model) {
        var report = service.findById(Integer.parseInt(id)).get();
        service.delete(report);
        model.addAttribute("reports", service.findAll());
        return "redirect:/report/";
    }
    
    /**
     * Post mapping for the report edit view that adds to the current report's lesson list
     * @param model Model object that holds attributes to be accessed by the view
     * @param report the form data spring has mapped from the post request
     * @return name of the report editing view
     */
    @PostMapping(value="/report/*", params={"addLesson"})
    public String addLesson(Model model, FormDto report) {
        model.addAttribute("report", service.addLesson(report));
        return REPORT_EDIT_VIEW;
    }
    
    /**
     * Post mapping for the report edit view that adds to the current report's story list
     * @param model Model object that holds attributes to be accessed by the view
     * @param report the form data spring has mapped from the post request
     * @return name of the report editing view
     */
    @PostMapping(value="/report/*", params={"addStory"})
    public String addStory(Model model, FormDto report) {
        model.addAttribute("report", service.addStory(report));
        return REPORT_EDIT_VIEW;
    }

    /**
     * Post mapping for the report edit view that adds to the current report's technician list
     * @param model Model object that holds attributes to be accessed by the view
     * @param report the form data spring has mapped from the post request
     * @return name of the report editing view
     */
    @PostMapping(value="/report/*", params={"addTechnician"})
    public String addTechnician(Model model, FormDto report) {
        model.addAttribute("report", service.addTechnician(report));
        return REPORT_EDIT_VIEW;
    }

    /**
     * Post mapping for the report edit view that adds to the current report's component list
     * @param model Model object that holds attributes to be accessed by the view
     * @param report the form data spring has mapped from the post request
     * @return name of the report editing view
     */
    @PostMapping(value="/report/*", params={"addComponent"})
    public String addComponent(Model model, FormDto report) {
        model.addAttribute("report", service.addComponent(report));
        return REPORT_EDIT_VIEW;
    }

    /**
     * Post mapping for the report edit view that adds to the current report's media list
     * @param model Model object that holds attributes to be accessed by the view
     * @param report the form data spring has mapped from the post request
     * @return name of the report editing view
     */
    @PostMapping(value="/report/*", params={"addMedia"})
    public String addMedia(Model model, FormDto report) {
        model.addAttribute("report", service.addMedia(report));
        return REPORT_EDIT_VIEW;
    }

    /**
     * POST mapping for the report edit view that removes an entity from the current report
     * @param entity the mapped entity object that should be removed
     * @return HttpStatus.OK if the entity was deleted
     */
    @PostMapping(value="/report", params={"deleteEntity"})
    public ResponseEntity<String> removeEntity(@RequestBody EntityDto entity) {
        service.removeEntity(entity);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    /**
     * Method that informs the date format the server should receive
     * https://stackoverflow.com/questions/34858989/wrong-date-format-when-submit-spring-form
     * 
     * @param binder Spring Data Binder
     */
    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        //The date format to parse or output your dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        //Create a new CustomDateEditor
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }

}
