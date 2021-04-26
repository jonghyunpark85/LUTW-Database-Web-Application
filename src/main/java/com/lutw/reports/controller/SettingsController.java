package com.lutw.reports.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.lutw.reports.service.SettingsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller class for the settings page that manages the database
 * backup and restore requests
 * 
 * @author Justin Heinrichs
 * @version 1.0
 */
@Controller
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private SettingsService service;

    Logger logger = LoggerFactory.getLogger(SettingsController.class);

    /**
     * Method that automatically adds a list of the current database backup
     * files to any request
     * @param model Model object accessed by the view
     * @throws IOException if the backup folder can't be accessed
     */
    @ModelAttribute
    public void addCurrentBackupFiles(Model model) throws IOException {
        model.addAttribute("currentBackups", service.backupFilesList());
    }

    /**
     * Request mapping for the settings page
     * @return the name of the settings view
     */
    @RequestMapping("")
    public String settingsView() {
        return "settings";
    }
    

    /**
     * Post mapping to the settings page that creates a database backup
     * @param model Model object accessed by the view
     * @param backup placeholder for desired filename
     * @param redirectAttributes object that holds view data in redirects
     * @return a redirect to the settings page with a response attribute
     * @throws SQLException when an error occurs creating the backup
     */
    @PostMapping("/backup")
    public String backup(Model model, 
        @RequestParam String backup,
        RedirectAttributes redirectAttributes) throws SQLException {

        service.backupToFile();
        redirectAttributes.addFlashAttribute("message", "Database backed up successfully");
        return "redirect:/settings";  
    }

    /**
     * Post mapping to the settings page that restores the database from a backup file
     * @param model Model object that holds attributes to be accessed by the view
     * @param filePath the file to attempt to restore from
     * @param redirectAttributes object that holds attributes sent on redirect
     * @return a redirect to the settings page
     * @throws SQLException when an error occurs attempting to restore the database
     */
    @PostMapping("/restore")
    public String restore(Model model, 
        @RequestParam String filePath,
        RedirectAttributes redirectAttributes) throws SQLException {

        service.restoreFromFile(filePath);
        redirectAttributes.addFlashAttribute("message", "Database restored from file");
        return "redirect:/settings";
    }

    /**
     * Exception handler for an SQLException in SettingsController
     * @param req the request the exception ooccured during
     * @param ex the exception thrown
     * @return a model and view object for the settings page with an error message
     */
    @ExceptionHandler(SQLException.class)
    public ModelAndView handleSqlException(HttpServletRequest req, Exception ex) {
        logger.error(ex.getMessage());

        var mav = new ModelAndView();
        mav.addObject("error", "Error backing up the database");
        mav.setViewName("settings");

        return mav;
    }

    /**
     * Exception handler for an IOException in SettingsController
     * @param req the request the exception occured during
     * @param ex the exception thrown
     * @return a model and view object for the settings page with an error message
     */
    @ExceptionHandler(IOException.class)
    public ModelAndView handleException(HttpServletRequest req, Exception ex) {
        logger.error(ex.getMessage());

        var mav = new ModelAndView();
        mav.addObject("error", "Error getting previous backups");
        mav.setViewName("settings");

        return mav;
    }

}
