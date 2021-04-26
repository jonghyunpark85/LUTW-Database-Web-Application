package com.lutw.reports.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entity class for objects representing a specific project and all its
 * related entities
 * 
 * @author Justin Heinrichs
 * @author Girrard Peter Oquendo
 * @version 1.0
 */
@Entity
@Table(name = "report")
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "region")
    private String region;

    @Column(name = "country")
    private String country;

    @Column(name = "gps")
    private String gps;

    @Column(name = "install_start")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date installationStart;

    @Column(name = "install_end")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date installationEnd;

    @Column(name = "date_created")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date dateCreated;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "report")
    private List<LessonLearned> lessonsLearned = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "report")
    private List<BeneficiaryStory> beneficiaryStory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "report")
    private List<TrainedTechnician> trainedTechnician = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "report")
    private List<InstalledComponent> installedComponent = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "report")
    private List<Media> media = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "report")
    @MapKeyJoinColumn(name = "question_id")
    private Map<Question, Response> responses = new HashMap<Question, Response>();

    public Report() {
        super();
    };

    public int getId() {
        return id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getInstallationStart() {
        return installationStart;
    }

    public void setInstallationStart(Date installationStart) {
        this.installationStart = installationStart;
    }

    public Date getInstallationEnd() {
        return installationEnd;
    }

    public void setInstallationEnd(Date installationEnd) {
        this.installationEnd = installationEnd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LessonLearned> getLessonsLearned() {
        return this.lessonsLearned;
    }

    public void setLessonsLearned(List<LessonLearned> lessonsLearned) {
        this.lessonsLearned = lessonsLearned;
    }
  
    public List<BeneficiaryStory> getBeneficiaryStory() {
        return beneficiaryStory;
    }

    public void setBeneficiaryStory(List<BeneficiaryStory> beneficiaryStory) {
        this.beneficiaryStory = beneficiaryStory;
    }

    public List<TrainedTechnician> getTrainedTechnician() {
        return trainedTechnician;
    }

    public void setTrainedTechnician(List<TrainedTechnician> trainedTechnician) {
        this.trainedTechnician = trainedTechnician;
    }

    public List<InstalledComponent> getInstalledComponent() {
        return installedComponent;
    }

    public void setInstalledComponent(List<InstalledComponent> installedComponent) {
        this.installedComponent = installedComponent;
    }

    public Map<Question, Response> getResponses() {
        return responses;
    }

    public void setResponses(Map<Question, Response> responses) {
        this.responses = responses;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

}