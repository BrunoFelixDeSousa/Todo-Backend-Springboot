package com.bruno.felix.todobackendspringboot.controller;

import com.bruno.felix.todobackendspringboot.dto.CreateToDoDto;
import com.bruno.felix.todobackendspringboot.dto.ToDoDto;
import com.bruno.felix.todobackendspringboot.dto.UpadateToDoDto;
import com.bruno.felix.todobackendspringboot.service.ToDoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    private final ToDoService toDoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ToDoController.class);

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }
    @PostMapping("")
    public ResponseEntity<ToDoDto> createToDo(@RequestBody CreateToDoDto newToDo) {

        ToDoDto toDoDto = toDoService.createTodo(newToDo);

        LOGGER.info("Saida do createToDo: " + toDoDto);
        return new ResponseEntity<>(toDoDto, HttpStatus.CREATED);
    }

    @GetMapping("")
    public List<ToDoDto> getToDos(@RequestParam Optional<Boolean> completed) {

        if (completed.isPresent()) {
            return toDoService.getToDos(completed.get());
        }
        return toDoService.getToDos();

//        return completed.map(toDoService::getToDos).orElseGet(toDoService::getToDos);
    }

    @GetMapping("/{id}")
    public ToDoDto getToDoById(@PathVariable Long id) {
        return toDoService.getToDoById(id);
    }

    @PutMapping("/{id}")
    public ToDoDto updateToDo(@PathVariable Long id, @RequestBody UpadateToDoDto upadateToDoDto) {
        return toDoService.updateToDo(id, upadateToDoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ToDoDto> deleteToDo(@PathVariable Long id) {
        toDoService.deleteToDo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
