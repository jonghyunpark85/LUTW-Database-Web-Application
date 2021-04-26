package com.lutw.reports.dto;

import java.util.ArrayList;
import java.util.List;

import com.lutw.reports.entity.Report;

/**
 * Data transfer object that holds a report and a list of fields
 * 
 * @author Justin Heinrichs
 * @version 1.0
 */
public class FormDto {
    
    private Report report;
    private List<FieldDto> fields = new ArrayList<>();

    public FormDto() {}

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public List<FieldDto> getFields() {
        return fields;
    }

    public void setFields(List<FieldDto> fields) {
        this.fields = fields;
    }

    public long getProjectId() {
        return this.report.getId();
    }
}
