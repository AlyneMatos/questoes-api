package com.example.vestibular.services;

import com.example.vestibular.models.Test;
import com.example.vestibular.repositories.TestRepository;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository repository;

    @Transactional
    public Test save(Test test){
        return repository.save(test);
    }

    @Transactional
    public void update(Test test){
        repository.save(test);
    }

    public List<Test> findAll(){
        return repository.findAll();
    }

    public Test findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    throw new NoResultException("Not Found entity for this id!");
                });
    }

    @Transactional
    public void delete(Test test){
        repository.delete(test);
    }

    public List<Test> searchTest(String institution, String year){
        return repository.getByInstitutionYear(institution, year);
    }
}
