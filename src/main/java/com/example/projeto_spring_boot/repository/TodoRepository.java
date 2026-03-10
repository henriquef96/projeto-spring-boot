
package com.example.projeto_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.projeto_spring_boot.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    
}