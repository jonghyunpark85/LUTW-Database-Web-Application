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

@Entity
@Table(name = "beneficiary_story")
public class BeneficiaryStory implements Serializable {

	/**
	 * Report entity object for a projects beneficiaries
     * 
     * @author Girrard Peter Oquendo
     * @version 1.0
	 */
	private static final long serialVersionUID = 4026242466654700854L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    @Column(name = "story")
    private String story;

    @Column(name = "bName")
    private String bName;

    public BeneficiaryStory() {

    }

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

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getBName() {
        return bName;
    }

    public void setBName(String bName) {
        this.bName = bName;
    }
}