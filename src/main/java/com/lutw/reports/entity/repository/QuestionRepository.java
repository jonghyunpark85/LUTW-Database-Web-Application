package com.lutw.reports.entity.repository;

import com.lutw.reports.entity.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    
}
