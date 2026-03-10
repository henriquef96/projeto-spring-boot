package com.example.projeto_spring_boot.controller;

import com.example.projeto_spring_boot.service.TodoService;
import com.example.projeto_spring_boot.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        return todoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.save(todo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        return todoService.findById(id)
                .map(existing -> {
                    todo.setId(id);
                    return ResponseEntity.ok(todoService.save(todo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}