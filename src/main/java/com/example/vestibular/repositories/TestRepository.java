package com.example.vestibular.repositories;

import com.example.vestibular.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test,Long> {

    @Query("SELECT test FROM Test test " +
            "JOIN test.institution i " +
            "JOIN test.year y "
            )
    List<Test> getByInstitutionYear(
            @Param("institution") String institution,
            @Param("year") String year);
}
