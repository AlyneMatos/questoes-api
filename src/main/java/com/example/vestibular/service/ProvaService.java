package com.example.vestibular.service;

import com.example.vestibular.model.Prova;
import com.example.vestibular.repository.ProvaRepository;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvaService {

    @Autowired
    private ProvaRepository repository;

    @Transactional
    public Prova save(Prova prova){
        return repository.saveAndFlush(prova);
    }

    @Transactional
    public void update(Prova prova, Long id){
        prova.setId(id);
        repository.save(prova);
    }

    public List<Prova> findAll(){
        return repository.findAll();
    }

    public Prova findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    throw new NoResultException("Ops! Not Found entity for this id! :(");
                });
    }

    @Transactional
    public void delete(Prova p){
        repository.delete(p);
    }

    public List<Prova> searchProvas(String banca, String ano){
        return repository.buscarPorBancaAno(banca, ano);
    }
}
