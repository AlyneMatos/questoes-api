package com.example.vestibular.repositories;

import com.example.vestibular.models.Discipline;
import com.example.vestibular.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {
}
