package com.example.vestibular.repositories;

import com.example.vestibular.models.Alternative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlternativeRepository extends JpaRepository<Alternative,Long> {
}
