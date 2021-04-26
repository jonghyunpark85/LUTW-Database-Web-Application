package com.lutw.reports.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service class for the settings page functions that handles
 * backing up and deleting the sqlite database files
 * 
 * @author Justin Heinrichs
 * @version 1.0
 */
@Service
public class SettingsService {

    private static final String BACKUP_PATH = "backup"; // folder for backup files

    Logger logger = LoggerFactory.getLogger(SettingsService.class);

    @Value("${spring.datasource.url}")
    private String connectionURL;
    
    /**
     * Method that makes sure to try creating the backup directory first
     */
    public void init() {
        new File(BACKUP_PATH).mkdirs();
    }

    /**
     * Method gets a list of the current backup files
     * @return a list of the .bak files in the backup directory
     * @throws IOException if an exception occurs while attempting to read the folder. Exceptions
     * are allowed in streams?
     */
    public List<String> backupFilesList() throws IOException {
        this.init();
        return Files.walk(Paths.get(BACKUP_PATH))
            .filter(Files::isRegularFile)
            .map(Path::getFileName)
            .map(Path::toString)
            .filter(name -> name.endsWith(".bak"))
            .collect(Collectors.toList());
    }

    /**
     * Method for the settings controller that creates a backup before trying to
     * restore the database from a backup file
     * @param fileName the filename to restore the database from
     * @throws SQLException if an error occurs during the backup or restore process
     */
    public void restoreFromFile(String fileName) throws SQLException {
        this.backup();
        this.restore(fileName);
    }

    /**
     * Method for the settings controller that creates a backup file of the database
     * @throws SQLException if an error occurs during the backup process
     */
    public void backupToFile() throws SQLException {
        this.init();
        this.backup();
    }

    /**
     * Method that creates a backup file for the database in the backup path with the
     * name being the time of the backup
     * @throws SQLException if an error occurs creating the backup
     */
    private void backup() throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HHmmss");
        var dateTime = LocalDateTime.now();
        var sql = String.format("backup to %s/%s.bak", BACKUP_PATH, dateTime.format(formatter));

        Connection conn = DriverManager.getConnection(connectionURL);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    /**
     * Method that restores the database from a previous backup file
     * @param fileName the file name to backup from
     * @throws SQLException if an error occurs during the restoration
     */
    private void restore(String fileName) throws SQLException {
        var sql = String.format("restore from %s", fileName);

        Connection conn = DriverManager.getConnection(connectionURL);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }


}
