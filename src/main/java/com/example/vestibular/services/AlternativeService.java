package com.example.vestibular.services;

import com.example.vestibular.models.Alternative;
import com.example.vestibular.repositories.AlternativeRepository;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlternativeService {

    @Autowired
    private AlternativeRepository repository;

    @Transactional
    public Alternative save(Alternative alternative){
        return repository.save(alternative);
    }

    @Transactional
    public void update(Alternative alternative){
        repository.save(alternative);
    }

    public List<Alternative> findAll(){
        return repository.findAll();
    }

    public Alternative findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new NoResultException("Not Found entity for this id!"));
    }

    @Transactional
    public void delete(Alternative alternative){
        repository.delete(alternative);
    }

    public boolean isCorrectItem(Long id) {
        Optional<Alternative> alternative = repository.findById(id);
        if(alternative.get().getIsCorrect()){ return true; }
        return false;
    }
}
