package com.bruno.felix.todobackendspringboot.service;

import com.bruno.felix.todobackendspringboot.dto.CreateToDoDto;
import com.bruno.felix.todobackendspringboot.dto.ToDoDto;
import com.bruno.felix.todobackendspringboot.dto.UpadateToDoDto;
import com.bruno.felix.todobackendspringboot.exception.ToDoException;
import com.bruno.felix.todobackendspringboot.model.ToDo;
import com.bruno.felix.todobackendspringboot.repository.ToDoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public ToDoDto createTodo(CreateToDoDto createToDoDTO) {

        ToDo newToDo = new ToDo();

        BeanUtils.copyProperties(createToDoDTO, newToDo);

        newToDo = toDoRepository.save(newToDo);

        return new ToDoDto(newToDo);
    }

    public List<ToDoDto> getToDos() {

        List<ToDo> toDos = toDoRepository.findAll();
//        return toDos.stream().map(entity -> new ToDoDto(entity)).toList();
        return toDos.stream().map(ToDoDto::new).toList();
    }

    public List<ToDoDto> getToDos(Boolean completed) {

        List<ToDo> toDos = toDoRepository.findByCompleted(completed);
//        return toDos.stream().map(entity -> new ToDoDto(entity)).toList();
        return toDos.stream().map(ToDoDto::new).toList();
    }

    public ToDoDto getToDoById(Long id) {

        Optional<ToDo> toDo = toDoRepository.findById(id);
        if (toDo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "getToDoById - to do not found");
        } else {
            return new ToDoDto(toDo.get());
        }
    }

    public ToDoDto updateToDo(Long id, UpadateToDoDto upadateToDoDto) {

        Optional<ToDo> toDo = toDoRepository.findById(id);

        if (toDo.isPresent()) {
            toDo.get().setName(upadateToDoDto.getName());
            toDo.get().setCompleted(upadateToDoDto.isCompleted());
            toDoRepository.save(toDo.get());
            return new ToDoDto(toDo.get());
        }
        else {
            throw new ToDoException(404, "updateToDo - to do not found");
        }

    }

    public void deleteToDo(Long id) {
        Optional<ToDo> toDo = toDoRepository.findById(id);
        if (toDo.isPresent()) {
            toDoRepository.delete(toDo.get());
        }
        else {
            throw new RuntimeException("deleteToDo - to do not found");
        }
    }
}
