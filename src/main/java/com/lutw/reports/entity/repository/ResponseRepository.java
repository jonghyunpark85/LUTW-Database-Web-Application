package com.lutw.reports.entity.repository;

import java.util.List;

import com.lutw.reports.entity.Response;
import com.lutw.reports.entity.ResponseSum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Integer> {

    @Query("select new com.lutw.reports.entity.ResponseSum(r.question.id, r.question.aggregateString, SUM(r.numberInput))" + 
        "from Response r " + 
        "group by r.question " + 
        "having r.question.type = 'NUMBER'")
    List<ResponseSum> getNumberSummaries();

    @Query("select new com.lutw.reports.entity.ResponseSum(r.question.id, r.question.aggregateString, SUM(r.floatInput))" + 
        "from Response r " + 
        "group by r.question " + 
        "having r.question.type = 'FLOAT'")
    List<ResponseSum> getFloatSummaries();

    Long countByQuestionId(int id);
}
