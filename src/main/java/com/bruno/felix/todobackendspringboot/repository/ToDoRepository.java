package com.bruno.felix.todobackendspringboot.repository;

import com.bruno.felix.todobackendspringboot.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findByCompleted(Boolean completed);
}
