package com.example.vestibular.controllers;

import com.example.vestibular.common.CommonResponse;
import com.example.vestibular.models.Alternative;
import com.example.vestibular.services.AlternativeService;
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
@RequestMapping("/alternatives")
@CommonResponse
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Alternatives API", description = "The Alternatives API contains all operations for the database.")
public class AlternativeController {

    @Autowired
    private AlternativeService alternativeService;

    @PostMapping
    @CommonResponse
    @Operation(summary = "Save Alternative", description = "Returns saved Alternatives.")
    public ResponseEntity<Alternative> save(@RequestBody @Valid Alternative alternative) {
        return created(fromCurrentRequestUri().path(alternativeService.save(alternative).getId().toString()).build().toUri()).body(alternative);
    }

    @PutMapping("/{id}")
    @CommonResponse
    @Operation(summary = "Update Alternative", description = "Updates an Alternative entity.")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid Alternative alternative){
        if(!(id.equals(alternative.getId()))){
            return badRequest().body("Id of entity is not equals as param 'id'!");
        }
        alternativeService.update(alternative);
        return noContent().build();
    }

    @GetMapping()
    @CommonResponse
    @Operation(summary = "List all Alternatives", description = "Lists all Alternatives of the entity.")
    public ResponseEntity<List<Alternative>> getAll(){
        return ok().body(alternativeService.findAll());
    }

    @GetMapping("/{id}")
    @CommonResponse
    @Operation(summary = "Get Alternative by ID", description = "Gets an Alternative by ID.")
    public ResponseEntity<Alternative> getOne(@PathVariable(value = "id") Long id) {
        return ok().body(alternativeService.findById(id));
    }

    @DeleteMapping("/{id}")
    @CommonResponse
    @Operation(summary = "Delete Alternative by ID", description = "Deletes an Alternative by ID.")
    public ResponseEntity<Alternative> delete(@PathVariable Long id){
        Alternative alternative = alternativeService.findById(id);
        alternativeService.delete(alternative);
        return noContent().build();
    }
}
