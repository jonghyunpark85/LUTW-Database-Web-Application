package com.lutw.reports.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Class for persisted Question objects used by form templates
 * 
 * @author Justin Heinrichs
 * @version 1.0
 */
@Entity
@Table(name = "question")
public class Question implements Serializable {

    private static final long serialVersionUID = -2615920962179902961L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Response> responses = new ArrayList<>();
    
    @Column(name = "prompt")
    private String prompt;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @Column(name = "aggregate")
    private String aggregateString = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getAggregateString() {
        return aggregateString;
    }

    public void setAggregateString(String aggregateString) {
        this.aggregateString = aggregateString;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Override
    public String toString() {
        return "[Question " + id + "] prompt : " + prompt + ", type : " + type; 
    }
}
