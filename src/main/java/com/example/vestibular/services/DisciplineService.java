package com.example.vestibular.services;

import com.example.vestibular.models.Discipline;
import com.example.vestibular.repositories.DisciplineRepository;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineService {

    @Autowired
    private DisciplineRepository repository;

    @Transactional
    public Discipline save(Discipline discipline){
        return repository.save(discipline);
    }

    @Transactional
    public void update(Discipline discipline){
        repository.save(discipline);
    }

    public List<Discipline> findAll(){
        return repository.findAll();
    }

    public Discipline findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    throw new NoResultException("Not Found entity for this id!");
                });
    }

    @Transactional
    public void delete(Discipline discipline){
        repository.delete(discipline);
    }

}
