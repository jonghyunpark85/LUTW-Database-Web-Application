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
 * Entity class for a response object corresponding to 
 * a question and created during a report form submission
 * 
 * @author Justin Heinrichs
 * @version 1.0
 */
@Entity
@Table(name = "response")
public class Response implements Serializable {
    
    private static final long serialVersionUID = 7753280488232825638L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

    @Column(name = "input")
    private String input;

    @Column(name = "number_input")
    private Integer numberInput;

    @Column(name = "float_input")
    private Double floatInput;

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Integer getNumberInput() {
        return numberInput;
    }

    public void setNumberInput(int numberInput) {
        this.numberInput = numberInput;
    }

    public Double getFloatInput() {
        return floatInput;
    }

    public void setFloatInput(Double floatInput) {
        this.floatInput = floatInput;
    }
}
