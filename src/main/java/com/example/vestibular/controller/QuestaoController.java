package com.example.vestibular.controller;

import com.example.vestibular.common.CommonResponse;
import com.example.vestibular.model.Questao;
import com.example.vestibular.model.enums.Disciplina;
import com.example.vestibular.model.search.SearchFilter;
import com.example.vestibular.service.QuestaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequestUri;

@Controller
@CommonResponse
@RequestMapping("/api/questoes")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "API de Questões ", description = "A API de questões possui todas as operações para as questões do banco.")
public class QuestaoController {

    @Autowired
    private QuestaoService questaoService;

    @PostMapping
    @Operation(summary = "Salvar Questões", description = "Retorna questões salvas.")
    public ResponseEntity<Questao> save(@RequestBody @Valid Questao questao) {
        return created(fromCurrentRequestUri().path(questaoService.save(questao).getId().toString()).build().toUri()).body(questao);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar questões", description = "Altualizar questão da entidade.")
    public ResponseEntity<Questao> updateQuestao(@PathVariable Long id, @RequestBody @Valid Questao questao){
        questaoService.update(questao,id);
        return noContent().build();
    }

    @GetMapping()
    @Operation(summary = "Listar todas as questões", description = "Listar todas as questões da entidade.")
    public ResponseEntity<List<Questao>> getAll(){
        return ok().body(questaoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar questão por ID", description = "Listar questão por ID.")
    public ResponseEntity<Questao> getOne(@PathVariable(value = "id") Long id) {
        return ok().body(questaoService.findById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar questão por ID", description = "Deletar questão por ID.")
    public ResponseEntity<Questao> deleteQuestao(@PathVariable Long id){
        Questao q = questaoService.findById(id);
        questaoService.delete(q);
        return noContent().build();
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar Questões por atributos", description = "Buscar questões por atributos.")
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
    @Operation(summary = "Paginação", description = "Buscar questões por paginação.")
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
    @Operation(summary = "Verificar item correto", description = "Verificar item correto.")
    public ResponseEntity<String> itemCorreto(@PathVariable Long id, @RequestParam String item){
        boolean isCorreto = questaoService.isAlternativaCorreta(id, item);

        if (isCorreto) {
            return ResponseEntity.ok().body("{\"value\": \"A alternativa é correta!\"}");
        } else {
            return ResponseEntity.ok().body("{\"value\": \"A alternativa é incorreta!\"}");
        }
    }
}
