package com.lutw.reports.entity.repository;

import com.lutw.reports.entity.Report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>{

    @Query("select count(distinct region) from Report")
    Integer countDistinctRegion();

    @Query("select count(distinct country) from Report")
    Integer countDistinctCountry();

    @Modifying
    @Query("delete from LessonLearned l where l.id=:id")
    int deleteLesson(@Param("id") int id);

    @Modifying
    @Query("delete from Media m where m.id=:id")
    int deleteMedia(@Param("id") int id);

    @Modifying
    @Query("delete from TrainedTechnician t where t.id=:id")
    int deleteTechnician(@Param("id") int id);

    @Modifying
    @Query("delete from BeneficiaryStory b where b.id=:id")
    int deleteBeneficiary(@Param("id") int id);

    @Modifying
    @Query("delete from InstalledComponent c where c.id=:id")
    int deleteComponent(@Param("id") int id);
}