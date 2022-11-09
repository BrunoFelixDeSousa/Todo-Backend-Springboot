package com.bruno.felix.todobackendspringboot.service;

import com.bruno.felix.todobackendspringboot.dto.CreateToDoDto;
import com.bruno.felix.todobackendspringboot.dto.ToDoDto;
import com.bruno.felix.todobackendspringboot.model.ToDo;
import com.bruno.felix.todobackendspringboot.repository.ToDoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
}
