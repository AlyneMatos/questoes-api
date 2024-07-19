package com.example.vestibular.controllers;

import com.example.vestibular.common.CommonResponse;
import com.example.vestibular.models.Test;
import com.example.vestibular.services.TestService;
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

@RestController
@RequestMapping("/tests")
@CommonResponse
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Test API", description = "The Test API contains all operations for the database.")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping
    @CommonResponse
    @Operation(summary = "Save Test", description = "Returns saved Tests.")
    public ResponseEntity<Test> save(@RequestBody @Valid Test test) {
        return created(fromCurrentRequestUri().path(testService.save(test).getId().toString()).build().toUri()).body(test);
    }

    @PutMapping("/{id}")
    @CommonResponse
    @Operation(summary = "Update Test", description = "Updates a Test entity.")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid Test test){
        if(!(id.equals(test.getId()))){
            return badRequest().body("Id of entity is not equals as param 'id'!");
        }
        testService.update(test);
        return noContent().build();
    }

    @GetMapping()
    @CommonResponse
    @Operation(summary = "List all Tests", description = "Lists all Tests of the entity.")
    public ResponseEntity<List<Test>> getAll(){
        return ok().body(testService.findAll());
    }

    @GetMapping("/{id}")
    @CommonResponse
    @Operation(summary = "Get Test by ID", description = "Gets a Test by ID.")
    public ResponseEntity<Test> getOne(@PathVariable(value = "id") Long id) {
        return ok().body(testService.findById(id));
    }

    @DeleteMapping("/{id}")
    @CommonResponse
    @Operation(summary = "Delete Test by ID", description = "Deletes a Test by ID.")
    public ResponseEntity<Test> delete(@PathVariable Long id){
        Test test = testService.findById(id);
        testService.delete(test);
        return noContent().build();
    }

    @GetMapping("/buscar")
    @CommonResponse
    @Operation(summary = "Search tests by attributes", description = "Search tests by attributes.")
    public ResponseEntity<List<Test>> search(
            @RequestParam String institution,
            @RequestParam String year) {
        List<Test> tests = testService.searchTest(institution, year);
        return ResponseEntity.ok(tests);
    }
}
