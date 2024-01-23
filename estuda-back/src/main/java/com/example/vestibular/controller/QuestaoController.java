package com.example.vestibular.controller;

import com.example.vestibular.model.enums.Disciplina;
import com.example.vestibular.model.Questao;
import com.example.vestibular.model.search.SearchFilter;
import com.example.vestibular.service.QuestaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequestUri;

@Controller
@RequestMapping("/api/questoes")
public class QuestaoController {

    @Autowired
    private QuestaoService questaoService;

    @PostMapping
    public ResponseEntity<Questao> save(@RequestBody @Valid Questao questao) {
        return created(fromCurrentRequestUri().path(questaoService.save(questao).getId().toString()).build().toUri()).body(questao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Questao> updateQuestao(@PathVariable Long id, @RequestBody @Valid Questao questao){
        questaoService.update(questao,id);
        return noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<Questao>> getAll(){
        return ok().body(questaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Questao> getOne(@PathVariable(value = "id") Long id) {
        return ok().body(questaoService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Questao> deleteQuestao(@PathVariable Long id){
        Questao q = questaoService.findById(id);
        questaoService.delete(q);
        return noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Questao>> buscarQuestoes(
            @RequestParam Disciplina disciplina,
            @RequestParam String assunto,
            @RequestParam String dificuldade,
            @RequestParam String banca,
            @RequestParam String ano) {
        List<Questao> questoes = questaoService.searchQuestions(disciplina, assunto, dificuldade, banca, ano);
        return ResponseEntity.ok(questoes);
    }

    @GetMapping("search")
    public ResponseEntity<Page<Questao>> search(@RequestParam("searchTerm") String searchTerm,
                                              @RequestParam(value = "order", required = false, defaultValue = "enunciado") String order,
                                              @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                              @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        String finalOrder = order.startsWith("-") ? order.substring(1) : order;

        if (!("enunciado".equalsIgnoreCase(finalOrder))) {
            throw new IllegalArgumentException("O campo de ordenação enviado é inválido.");
        }

        SearchFilter searchFilter = new SearchFilter(searchTerm, finalOrder.toLowerCase(), page, size);
        Page<Questao> result = questaoService.search(searchFilter);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<String> itemCorreto(@PathVariable Long id, @RequestParam String item){
        boolean isCorreto = questaoService.isAlternativaCorreta(id, item);

        if (isCorreto) {
            return ResponseEntity.ok().body("A alternativa é correta!");
        } else {
            return ResponseEntity.ok().body("A alternativa não é correta.");
        }
    }
}
