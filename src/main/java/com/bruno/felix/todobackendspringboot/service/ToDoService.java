package com.bruno.felix.todobackendspringboot.service;

import com.bruno.felix.todobackendspringboot.dto.CreateToDoDto;
import com.bruno.felix.todobackendspringboot.dto.ToDoDto;
import com.bruno.felix.todobackendspringboot.model.ToDo;
import com.bruno.felix.todobackendspringboot.repository.ToDoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public ToDoDto createTodo(CreateToDoDto createToDoDTO) {

        ToDo newToDo = new ToDo();
//        newToDo.setName(createToDoDTO.getName());
//        newToDo.setCompleted(createToDoDTO.isCompleted());
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
}
