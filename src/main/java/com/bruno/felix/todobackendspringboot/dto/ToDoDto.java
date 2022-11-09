package com.bruno.felix.todobackendspringboot.dto;

import com.bruno.felix.todobackendspringboot.model.ToDo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ToDoDto {

    private Long id;
    private String name;
    private boolean completed;

    public ToDoDto(ToDo toDo) {
        this.id = toDo.getId();
        this.name = toDo.getName();
        this.completed = toDo.isCompleted();
    }
}
