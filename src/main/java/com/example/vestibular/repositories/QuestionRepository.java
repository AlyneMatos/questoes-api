package com.example.vestibular.repositories;

import com.example.vestibular.models.Discipline;
import com.example.vestibular.models.Question;
import com.example.vestibular.models.Test;
import com.example.vestibular.models.enums.Level;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
}
