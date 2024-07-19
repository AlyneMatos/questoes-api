package com.example.vestibular.services;

import com.example.vestibular.models.Topic;
import com.example.vestibular.repositories.TopicRepository;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository repository;

    @Transactional
    public Topic save(Topic topic){
        return repository.save(topic);
    }

    @Transactional
    public void update(Topic topic){
        repository.save(topic);
    }

    public List<Topic> findAll(){
        return repository.findAll();
    }

    public Topic findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new NoResultException("Not Found entity for this id!"));
    }

    @Transactional
    public void delete(Topic topic){
        repository.delete(topic);
    }
}
