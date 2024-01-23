package com.example.vestibular.service;

import com.example.vestibular.model.enums.Disciplina;
import com.example.vestibular.model.Questao;
import com.example.vestibular.model.search.SearchFilter;
import com.example.vestibular.repository.QuestaoRepository;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestaoService {

    @Autowired
    private QuestaoRepository repository;

    @Transactional
    public Questao save(Questao questao){
        return repository.saveAndFlush(questao);
    }

    @Transactional
    public void update(Questao questao, Long id){
        questao.setId(id);
        repository.save(questao);
    }

    public List<Questao> findAll(){
        return repository.findAll();
    }

    public Questao findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> {
            throw new NoResultException("Ops! Not Found entity for this id! :(");
        });
    }

    @Transactional
    public void delete(Questao q){
        repository.delete(q);
    }

    public List<Questao> searchQuestions(Disciplina disciplina, String assunto, String dificuldade, String banca, String ano){
        return repository.buscarPorDisciplinaAssuntoDificuldadeBancaAno(disciplina, assunto, dificuldade, banca, ano);
    }

    public Page<Questao> search(SearchFilter searchTerm){
        return repository.searchPage(searchTerm.getFilter(),
                PageRequest.of(searchTerm.getPage(), searchTerm.getSize(), searchTerm.getDirection(), searchTerm.getOrder()));
    }

    public boolean isAlternativaCorreta(Long id, String opcao) {
        Optional<Questao> questaoOptional = repository.findById(id);

        if (questaoOptional.isPresent()) {
            Questao questao = questaoOptional.get();
            return questao.getItemCorreto() != null && questao.getItemCorreto().equals(opcao);
        }

        return false;
    }
}
