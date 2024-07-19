package com.example.vestibular.services;

import com.example.vestibular.models.Alternative;
import com.example.vestibular.models.Question;
import com.example.vestibular.models.ResponseQuestion;
import com.example.vestibular.repositories.QuestionRepository;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repository;

    @Transactional
    public Question save(Question question){
        return repository.save(question);
    }

    @Transactional
    public void update(Question question){
        repository.save(question);
    }

    public List<Question> findAll(){
        return repository.findAll();
    }

    public Question findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoResultException("Not Found entity for this id!"));
    }

    @Transactional
    public void delete(Question question){
        repository.delete(question);
    }

    public ResponseQuestion isCorrectItem(Long id, String option) {

        Question question = findById(id);
        List<Alternative> alternatives = question.getAlternatives();
        boolean item = question.getCorrectItem().equals(option);
        String message = item ? "Resposta correta" : "Resposta incorreta. A resposta correta é: " + question.getCorrectItem();

        return new ResponseQuestion(message, alternatives);
    }
}
