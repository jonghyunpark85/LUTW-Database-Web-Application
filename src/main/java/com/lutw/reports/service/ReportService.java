package com.lutw.reports.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import com.lutw.reports.entity.InstalledComponent;
import com.lutw.reports.entity.LessonLearned;
import com.lutw.reports.entity.Media;
import com.lutw.reports.entity.Question;
import com.lutw.reports.entity.Report;
import com.lutw.reports.entity.Response;
import com.lutw.reports.entity.TrainedTechnician;
import com.lutw.reports.dto.EntityDto;
import com.lutw.reports.dto.EntityType;
import com.lutw.reports.dto.FieldDto;
import com.lutw.reports.dto.FormDto;
import com.lutw.reports.dto.FormMapper;
import com.lutw.reports.entity.BeneficiaryStory;
import com.lutw.reports.entity.repository.QuestionRepository;
import com.lutw.reports.entity.repository.ReportRepository;
import com.lutw.reports.entity.repository.ResponseRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for managing the creation, retrieval, updating, and deleting of project
 * entities in the database, including all of a projects related entities and 
 * associated form responses
 * 
 * @author Justin Heinrichs
 * @author Girrard Peter Oquendo
 * @version 1.0
 * 
 */
@Service
public class ReportService {
    
    @Autowired
    private ReportRepository repo;

    @Autowired
    private QuestionRepository questionRepo;

    @Autowired
    private ResponseRepository responseRepo;

    Logger logger = LoggerFactory.getLogger(ReportService.class);
    
    /**
     * Attempts to retrieve a report of a given ID from the database
     * @param id the id of the report to get
     * @return Schrodinger's report
     */
    public Optional<Report> findById(int id) {
        return repo.findById(id);
    }
    
    /**
     * Retrieve the list of reports from the database
     * @return list of reports
     */
    public List<Report> findAll() {
        return repo.findAll();
    }

    /**
     * Deletes a report from the database
     * @param report report to be deleted
     */
    public void delete(Report report) {
        repo.delete(report); 
    }
    
    /**
     * Creates a new project form and sets its creation date
     * @return newly created project form
     */
    public FormDto createProject() {
        var report = new Report();
        report.setDateCreated(new Date(System.currentTimeMillis()));
        return FormMapper.toFormDto(repo.save(report));
    }
    
    /**
     * Adds a lesson entity to a formDTO
     * @param report the form the lesson story will be added to
     * @return the form with the lesson entity added
     */
    public FormDto addLesson(FormDto report) {
        var lesson = new LessonLearned();
        lesson.setReport(report.getReport());
        report.getReport().getLessonsLearned().add(lesson);
        
        return report;
    }
    
    /**
     * Adds a story entity to a formDTO
     * @param report the form the technician story will be added to
     * @return the form with the story entity added
     */
    public FormDto addStory(FormDto report) {
        var story = new BeneficiaryStory();
        story.setReport(report.getReport());
        report.getReport().getBeneficiaryStory().add(story);

        return report;
    }

    /**
     * Adds a technician entity to a formDTO
     * @param report the form the technician entity will be added to
     * @return the form with the technician entity added
     */
    public FormDto addTechnician(FormDto report) {
        var technician = new TrainedTechnician();
        technician.setReport(report.getReport());
        report.getReport().getTrainedTechnician().add(technician);

        return report;
    }

    /**
     * Adds a component entity to a formDTO
     * @param report the form the component entity will be added to
     * @return the form with the component entity added
     */
    public FormDto addComponent(FormDto report) {
        var component = new InstalledComponent();
        component.setReport(report.getReport());
        report.getReport().getInstalledComponent().add(component);

        return report;
    }

    /**
     * Adds a media entity to a formDTO
     * @param report the form the media entity will be added to
     * @return the form with the media entity added
     */
    public FormDto addMedia(FormDto report) {
        var media = new Media();
        media.setReport(report.getReport());
        report.getReport().getMedia().add(media);

        return report;
    }

    /**
     * Gets a FormDto using a report ID
     * @param id the report ID to search for
     * @return an optional containing a FormDto corresponding the report
     */
    public Optional<FormDto> getFormById(int id) {
        return repo.findById(id).map(FormMapper::toFormDto);
    }

    /**
     * Method to persist data from a Form data transfer object
     * @param dto the Form data transfer object
     * @return the original dto
     */
    public FormDto saveForm(FormDto dto) {
        Optional<Report> opt = repo.findById(dto.getReport().getId());

        if (opt.isPresent()) {
            Report persisted = opt.get();
            Report submit = dto.getReport();
            
            persisted.setName(submit.getName());
            persisted.setRegion(submit.getRegion());
            persisted.setCountry(submit.getCountry());
            persisted.setGps(submit.getGps());
            persisted.setInstallationStart(submit.getInstallationStart());
            persisted.setInstallationEnd(submit.getInstallationEnd());

            // Pretty sure this tosses the entire old list and adds the new one
            // but I don't know a better way
            persisted.setLessonsLearned(submit.getLessonsLearned());
            persisted.setBeneficiaryStory(submit.getBeneficiaryStory());
            persisted.setTrainedTechnician(submit.getTrainedTechnician());
            persisted.setMedia(submit.getMedia());
            persisted.setInstalledComponent(submit.getInstalledComponent());

            dto.setReport(updateReportEntities(persisted));

            // Update/create persisted response objects for each question answered
            dto.getFields().forEach(field -> updateResponse(field, persisted));
        }

        return dto;
    }

    /**
     * Method to persist changes in a Report object
     * @param report the report to persist
     * @return the persisted report
     */
    private Report updateReportEntities(Report report) {
        removeAllHolesInEntityLists(report); // Entities are deleted using javascript so the array could have nulls

        report.getLessonsLearned().forEach(lesson -> lesson.setReport(report));
        report.getBeneficiaryStory().forEach(story -> story.setReport(report));
        report.getInstalledComponent().forEach(component -> component.setReport(report));
        report.getTrainedTechnician().forEach(technician -> technician.setReport(report));
        report.getMedia().forEach(media -> media.setReport(report));

        return repo.save(report);
    }

    /**
     * Removes elements from the entity lists that have null values (ie were deleted using javascript)
     * @param report the report
     */
    private void removeAllHolesInEntityLists(Report report) {
        removeHoles(report.getLessonsLearned(), LessonLearned::getLesson);
        removeHoles(report.getBeneficiaryStory(), BeneficiaryStory::getBName);
        removeHoles(report.getInstalledComponent(), InstalledComponent::getClass);
        removeHoles(report.getMedia(), Media::getMedia);
        removeHoles(report.getTrainedTechnician(), TrainedTechnician::getTechnician);
    }

    /**
     * Filters a list by removing elements where the supplied Function returns null. 
     * @param <E> the list element
     * @param thing the list to filter in place
     * @param fn the function whos result will be compared with null
     */
    private <E> void removeHoles(List<E> thing, Function<E, Object> fn) {
        thing.removeIf(i -> fn.apply(i) == null);
    }

    /**
     * Method to update a response in the database from a field data transfer object
     * @param dto the data transfer object with the response ID and input
     * @throws IllegalArgumentException if the response's question has an invalid type
     */
    private void updateResponse(FieldDto dto, Report report) throws IllegalArgumentException {
        Question question = questionRepo
            .findById(dto.getQuestionID())
            .orElseThrow(IllegalArgumentException::new);

        // Set the update method based on the type/if the value is non default
        Consumer<Response> update = null;
        switch(question.getType()) {
            case NUMBER:
                if (dto.getNumberInput() != 0) {
                    update = r -> r.setNumberInput(dto.getNumberInput());
                }
                break;
            case FLOAT:
                if (dto.getFloatInput() != 0.0) {
                    update = r -> r.setFloatInput(dto.getFloatInput());
                }
                break;
            case TEXT:
            case STRING:
                if (dto.getInput() != null && !dto.getInput().equals("")) {
                    update = r -> r.setInput(dto.getInput());
                }
                break;
            default:
                throw new IllegalArgumentException("Saving an invalid question type");
        } 
            
        if (update != null) {
            Response response = responseRepo
                .findById(dto.getResponseID())
                .orElseGet(Response::new);

            response.setReport(report);
            response.setQuestion(question);

            update.accept(response);
            responseRepo.save(response);
        }
        // reponses with default values arent persisted
    }

    /**
     * Method to delete report entities (lessons learned, media, technicians trained..)
     * @param entity the dto holding the id and type of the entity to remove
     */
    @Transactional
    public void removeEntity(EntityDto entity) {
        getEntityRemoveMethod(entity.getType()).apply(entity.getId());
    }

    /**
     * Function to hide a switch statement. Takes an entity type and returns the corresponding
     * delete method in the repository.
     * @param type the type of the entity
     * @return function that takes an integer id and returns the number of rows affected after a deletion
     * @throws IllegalArgumentException default case but this shouldn't happen, its an enum.. I dont think
     * Function.apply can throw exceptions so its fine
     */
    private Function<Integer, Integer> getEntityRemoveMethod(EntityType type) throws IllegalArgumentException {
        switch (type) {
        case COMPONENT:
            return repo::deleteComponent;
        case LESSON:
            return repo::deleteLesson;
        case MEDIA:
            return repo::deleteMedia;
        case STORY:
            return repo::deleteBeneficiary;
        case TECHNICIAN:
            return repo::deleteTechnician;
        default:
            throw new IllegalArgumentException("Invalid entity type");
        }
    }

}
