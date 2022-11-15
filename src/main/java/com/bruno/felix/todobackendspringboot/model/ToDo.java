package com.bruno.felix.todobackendspringboot.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Table(name = "todos")
public class ToDo implements Serializable {

    @Serial
    private static final long serialVersionUID;

    static {
        serialVersionUID = 1L;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private boolean completed;
}
