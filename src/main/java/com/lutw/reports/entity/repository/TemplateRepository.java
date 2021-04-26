package com.lutw.reports.entity.repository;

import com.lutw.reports.entity.Template;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Integer> {
    
}
