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
 * Entity class for lessons learned during a project
 * 
 * @author Justin Heinrichs
 * @version 1.0
 */
@Entity
@Table(name = "lesson_learned")
public class LessonLearned implements Serializable {
    
    private static final long serialVersionUID = -7038131739583322244L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;
    
    @Column(name="lesson")
    private String lesson;
    
    public LessonLearned() {

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

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s", this.id, this.lesson);
    }
    
}
