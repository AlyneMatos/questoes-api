package com.example.vestibular.controller;

import com.example.vestibular.model.Prova;
import com.example.vestibular.service.ProvaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequestUri;

@Controller
@RequestMapping("/provas")
public class ProvaController {
    @Autowired
    private ProvaService provaService;

    @PostMapping
    public ResponseEntity<Prova> save(@RequestBody @Valid Prova prova) {
        return created(fromCurrentRequestUri().path(provaService.save(prova).getId().toString()).build().toUri()).body(prova);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prova> updateProva(@PathVariable Long id, @RequestBody @Valid Prova prova){
        provaService.update(prova,id);
        return noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<Prova>> getAll(){
        return ok().body(provaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prova> getOne(@PathVariable(value = "id") Long id) {
        return ok().body(provaService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Prova> deleteQuestao(@PathVariable Long id){
        Prova prova = provaService.findById(id);
        provaService.delete(prova);
        return noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Prova>> buscarProvas(
            @RequestParam String banca,
            @RequestParam String ano) {
        List<Prova> provas = provaService.searchProvas(banca, ano);
        return ResponseEntity.ok(provas);
    }
}
