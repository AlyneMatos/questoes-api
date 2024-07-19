package com.example.vestibular.controllers;

import com.example.vestibular.common.CommonResponse;
import com.example.vestibular.models.Question;
import com.example.vestibular.models.ResponseQuestion;
import com.example.vestibular.services.QuestionService;
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
@CommonResponse
@RequestMapping("/questions")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Question API", description = "The Question API contains all operations for the database.")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    @CommonResponse
    @Operation(summary = "Save Question", description = "Returns saved Questions.")
    public ResponseEntity<Question> save(@RequestBody @Valid Question question) {
        return ResponseEntity.created(fromCurrentRequestUri().path(questionService.save(question).getId().toString()).build().toUri()).body(question);
    }

    @PutMapping("/{id}")
    @CommonResponse
    @Operation(summary = "Update Question", description = "Updates a Question entity.")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid Question question){
        if(!(id.equals(question.getId()))){
            return badRequest().body("Id of entity is not equals as param 'id'!");
        }
        questionService.update(question);
        return noContent().build();
    }

    @GetMapping()
    @CommonResponse
    @Operation(summary = "List all Questions", description = "Lists all Questions of the entity.")
    public ResponseEntity<List<Question>> getAll(){
        return ok().body(questionService.findAll());
    }

    @GetMapping("/{id}")
    @CommonResponse
    @Operation(summary = "Get Question by ID", description = "Gets a Question by ID.")
    public ResponseEntity<Question> getOne(@PathVariable(value = "id") Long id) {
        return ok().body(questionService.findById(id));
    }

    @DeleteMapping("/{id}")
    @CommonResponse
    @Operation(summary = "Delete Question by ID", description = "Deletes a Question by ID.")
    public ResponseEntity<Question> delete(@PathVariable Long id){
        Question question = questionService.findById(id);
        questionService.delete(question);
        return noContent().build();
    }

    @GetMapping("/item/{id}")
    @CommonResponse
    @Operation(summary = "Verify correct item", description = "Verify correct item.")
    public ResponseEntity<ResponseQuestion> correctItem(@PathVariable Long id, @RequestParam String item){

        ResponseQuestion isCorrect = questionService.isCorrectItem(id, item);
        return ResponseEntity.ok(isCorrect);
    }
}
