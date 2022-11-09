package com.bruno.felix.todobackendspringboot.dto;

import lombok.Data;

@Data
public class CreateToDoDto {

    private String name;
    private boolean completed;
}
