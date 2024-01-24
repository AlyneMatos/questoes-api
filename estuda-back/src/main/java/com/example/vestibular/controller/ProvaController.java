package com.example.vestibular.controller;

import com.example.vestibular.common.CommonResponse;
import com.example.vestibular.model.Prova;
import com.example.vestibular.service.ProvaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequestUri;

@Controller
@RequestMapping("/api/provas")
@CommonResponse
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "API de Provas ", description = "A API de Provas possui todas as operações para as provas do banco.")
public class ProvaController {
    @Autowired
    private ProvaService provaService;

    @PostMapping
    @Operation(summary = "Salvar Provas", description = "Retorna provas salvas.")
    public ResponseEntity<Prova> save(@RequestBody @Valid Prova prova) {
        return created(fromCurrentRequestUri().path(provaService.save(prova).getId().toString()).build().toUri()).body(prova);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar provas", description = "Altualiza prova da entidade.")
    public ResponseEntity<Prova> updateProva(@PathVariable Long id, @RequestBody @Valid Prova prova){
        provaService.update(prova,id);
        return noContent().build();
    }

    @GetMapping()
    @Operation(summary = "Listar todas as provas", description = "Listar todas as provas da entidade.")
    public ResponseEntity<List<Prova>> getAll(){
        return ok().body(provaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar prova por ID", description = "Listar prova por ID.")
    public ResponseEntity<Prova> getOne(@PathVariable(value = "id") Long id) {
        return ok().body(provaService.findById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar prova por ID", description = "Deletar prova por ID.")
    public ResponseEntity<Prova> deleteQuestao(@PathVariable Long id){
        Prova prova = provaService.findById(id);
        provaService.delete(prova);
        return noContent().build();
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar provas por atributos", description = "Buscar provas por atributos.")
    public ResponseEntity<List<Prova>> buscarProvas(
            @RequestParam String banca,
            @RequestParam String ano) {
        List<Prova> provas = provaService.searchProvas(banca, ano);
        return ResponseEntity.ok(provas);
    }
}
