package com.example.vestibular.controllers;

import com.example.vestibular.common.CommonResponse;
import com.example.vestibular.models.Discipline;
import com.example.vestibular.services.DisciplineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequestUri;

@RestController
@RequestMapping("/disciplines")
@CommonResponse
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Discipline API", description = "The Discipline API contains all operations for the database.")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @PostMapping
    @CommonResponse
    @Operation(summary = "Save Discipline", description = "Returns saved Disciplines.")
    public ResponseEntity<Discipline> save(@RequestBody @Valid Discipline discipline) {
        return created(fromCurrentRequestUri().path(disciplineService.save(discipline).getId().toString()).build().toUri()).body(discipline);
    }

    @PutMapping("/{id}")
    @CommonResponse
    @Operation(summary = "Update Discipline", description = "Updates a Discipline entity.")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid Discipline discipline){
        if(!(id.equals(discipline.getId()))){
            return badRequest().body("Id of entity is not equals as param 'id'!");
        }
        disciplineService.update(discipline);
        return noContent().build();
    }

    @GetMapping()
    @CommonResponse
    @Operation(summary = "List all Disciplines", description = "Lists all Disciplines of the entity.")
    public ResponseEntity<List<Discipline>> getAll(){
        return ok().body(disciplineService.findAll());
    }

    @GetMapping("/{id}")
    @CommonResponse
    @Operation(summary = "Get Discipline by ID", description = "Gets a Discipline by ID.")
    public ResponseEntity<Discipline> getOne(@PathVariable(value = "id") Long id) {
        return ok().body(disciplineService.findById(id));
    }

    @DeleteMapping("/{id}")
    @CommonResponse
    @Operation(summary = "Delete Discipline by ID", description = "Deletes a Discipline by ID.")
    public ResponseEntity<Discipline> delete(@PathVariable Long id){
        Discipline discipline = disciplineService.findById(id);
        disciplineService.delete(discipline);
        return noContent().build();
    }
}
