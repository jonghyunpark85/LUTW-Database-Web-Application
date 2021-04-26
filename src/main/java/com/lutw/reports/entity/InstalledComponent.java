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
 * Entity class for components installed during a project
 * 
 * @author Girrard Peter Oquendo
 * @version 1.0
 */
@Entity
@Table(name = "installed_component")
public class InstalledComponent implements Serializable{

    private static final long serialVersionUID = -2047534265924896698L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;
    
    @Column(name="component")
    private String component;

    @Column(name="installer")
    private String installer;

    @Column(name="location")
    private String location;

    @Column(name="size")
    private String size;

    @Column(name="cost")
    private Double cost;

    @Column(name="contribution")
    private Double contribution;
    
    public InstalledComponent() {

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

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }
    
    public String getInstaller() {
        return installer;
    }

    public void setInstaller(String installer) {
        this.installer = installer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getContribution() {
        return contribution;
    }

    public void setContribution(Double c) {
        contribution = c;
    }
}
