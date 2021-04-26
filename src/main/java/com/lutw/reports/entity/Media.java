package com.lutw.reports.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity class for project related media links
 * 
 * @author Girrard Peter Oquendo
 * @version 1.0
 */
@Entity
@Table(name = "media")
public class Media implements Serializable {

    private static final long serialVersionUID = -6332976613964256630L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;
    
    @Column(name="media")
    private String media;
    
    public Media() {

    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }
}
