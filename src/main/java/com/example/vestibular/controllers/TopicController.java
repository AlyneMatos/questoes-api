package com.example.vestibular.controllers;

import com.example.vestibular.common.CommonResponse;
import com.example.vestibular.models.Discipline;
import com.example.vestibular.models.Topic;
import com.example.vestibular.services.TopicService;
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
@RequestMapping("/topics")
@CommonResponse
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Topic API", description = "The Topic API contains all operations for the database.")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    @CommonResponse
    @Operation(summary = "Save Topic", description = "Returns saved Topics.")
    public ResponseEntity<Topic> save(@RequestBody @Valid Topic topic) {
        return ResponseEntity.created(fromCurrentRequestUri().path(topicService.save(topic).getId().toString()).build().toUri()).body(topic);
    }

    @PutMapping("/{id}")
    @CommonResponse
    @Operation(summary = "Update Topic", description = "Updates a Topic entity.")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid Topic topic){
        if(!(id.equals(topic.getId()))){
            return badRequest().body("Id of entity is not equals as param 'id'!");
        }
        topicService.update(topic);
        return noContent().build();
    }

    @GetMapping()
    @CommonResponse
    @Operation(summary = "List all Topics", description = "Lists all Topics of the entity.")
    public ResponseEntity<List<Topic>> getAll(){
        return ok().body(topicService.findAll());
    }

    @GetMapping("/{id}")
    @CommonResponse
    @Operation(summary = "Get Topic by ID", description = "Gets a Topic by ID.")
    public ResponseEntity<Topic> getOne(@PathVariable(value = "id") Long id) {
        return ok().body(topicService.findById(id));
    }

    @DeleteMapping("/{id}")
    @CommonResponse
    @Operation(summary = "Delete Topic by ID", description = "Deletes a Topic by ID.")
    public ResponseEntity<Topic> delete(@PathVariable Long id){
        Topic topic = topicService.findById(id);
        topicService.delete(topic);
        return noContent().build();
    }
}
